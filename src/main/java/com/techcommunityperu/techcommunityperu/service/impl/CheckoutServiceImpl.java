package com.techcommunityperu.techcommunityperu.service.impl;
import com.techcommunityperu.techcommunityperu.integration.email.dto.EmailDTO;
import com.techcommunityperu.techcommunityperu.mapper.InscripcionMapper;
import com.techcommunityperu.techcommunityperu.dto.InscripcionDTO;
import com.techcommunityperu.techcommunityperu.dto.PaymentCaptureResponse;
import com.techcommunityperu.techcommunityperu.dto.PaymentOrderResponse;
import com.techcommunityperu.techcommunityperu.integration.payment.paypal.dto.OrderCaptureResponse;
import com.techcommunityperu.techcommunityperu.integration.payment.paypal.dto.OrderResponse;
import com.techcommunityperu.techcommunityperu.integration.payment.paypal.service.PayPalService;
import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;
import com.techcommunityperu.techcommunityperu.repository.CronogramaRepository;
import com.techcommunityperu.techcommunityperu.repository.InscriptionRepository;
import com.techcommunityperu.techcommunityperu.service.CheckoutService;
import com.techcommunityperu.techcommunityperu.integration.email.service.EmailService;
import com.techcommunityperu.techcommunityperu.service.PurchaseService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final PayPalService payPalService;
    private final PurchaseService purchaseService;
    private final EmailService emailService;
    private final InscripcionMapper inscripcionMapper;
    private final CronogramaRepository cronogramaRepository;

    @Value("${spring.mail.username}")
    private String mailFrom;

    @Override
    public PaymentOrderResponse createPaymentUrl(Integer purchaseId, String returnUrl, String cancelUrl) {
        OrderResponse orderResponse = payPalService.createOrder(purchaseId, returnUrl, cancelUrl);

        String paypalUrl = orderResponse
                .getLinks()
                .stream()
                .filter(link -> link.getRel().equals("approve"))
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .getHref();

        return new PaymentOrderResponse(paypalUrl);
    }

    public void checkoutEmail(Inscripcion inscripcionId) throws MessagingException {

        String descripcionEvento= inscripcionId.getEvento().getDescripcion();
        String nombreEvento = inscripcionId.getEvento().getNombre();
        String nombreParticipante = inscripcionId.getParticipante().getNombre();
        String apellidoParticipante = inscripcionId.getParticipante().getApellido();
        String correoParticipante = inscripcionId.getParticipante().getUsuarioId().getCorreoElectronico();
        Double montoInscripcion = inscripcionId.getMonto();
        LocalDateTime fechaInicio = cronogramaRepository.findFirstByEventoIdOrderByFechaInicioAsc(inscripcionId.getEvento().getId()).getFechaInicio();
        String estadoInscripcion = inscripcionId.getInscripcionStatus().name();
        String tipoPago = inscripcionId.getTipoPago().name();
//        Mapeo para el formato de html
        Map<String, Object> model = new HashMap<>();
        model.put("correoElectronico", correoParticipante);
        model.put("nombreParticipante", nombreParticipante);
        model.put("apellidoParticipante", apellidoParticipante);
        model.put("nombreEvento", nombreEvento);
        model.put("descripcionEvento", descripcionEvento);
        model.put("estadoInscripcion", estadoInscripcion);
        model.put("tipoPago", tipoPago);
        model.put("montoInscripcion", montoInscripcion);
        model.put("fechaInicio", fechaInicio);
//        Configuracion del mensje del email
        EmailDTO mail = emailService.createEmail(
                correoParticipante,
                "Confirmacion de pago a evento",
                model,
                mailFrom
        );
        emailService.sendEmail(mail,"confirmation-template");
    }

    @Override
    public PaymentCaptureResponse capturePayment(String orderId) throws MessagingException {
        OrderCaptureResponse orderCaptureResponse = payPalService.captureOrder(orderId);
        boolean completed = orderCaptureResponse.getStatus().equals("COMPLETED");

        PaymentCaptureResponse paypalCaptureResponse = new PaymentCaptureResponse();
        paypalCaptureResponse.setCompleted(completed);

        if (completed) {
            String purchaseIdStr = orderCaptureResponse.getPurchaseUnits().get(0).getReferenceId();

            InscripcionDTO inscripcionDTO = purchaseService.confirmInscription(Integer.parseInt(purchaseIdStr));

            Inscripcion inscripcion = inscripcionMapper.toEntity(inscripcionDTO);

            paypalCaptureResponse.setInscriptionId(inscripcionDTO.getParticipante().getId());

            checkoutEmail(inscripcion);
        }

        return paypalCaptureResponse;
    }
}
