package com.techcommunityperu.techcommunityperu.service.impl;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.techcommunityperu.techcommunityperu.integration.email.dto.EmailDTO;
import com.techcommunityperu.techcommunityperu.mapper.InscripcionMapper;
import com.techcommunityperu.techcommunityperu.dto.InscripcionDTO;
import com.techcommunityperu.techcommunityperu.dto.PaymentCaptureResponse;
import com.techcommunityperu.techcommunityperu.dto.PaymentOrderResponse;
import com.techcommunityperu.techcommunityperu.integration.payment.paypal.dto.OrderCaptureResponse;
import com.techcommunityperu.techcommunityperu.integration.payment.paypal.dto.OrderResponse;
import com.techcommunityperu.techcommunityperu.integration.payment.paypal.service.PayPalService;
import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;
import com.techcommunityperu.techcommunityperu.model.enums.statusInscription;
import com.techcommunityperu.techcommunityperu.repository.CronogramaRepository;
import com.techcommunityperu.techcommunityperu.repository.InscriptionRepository;
import com.techcommunityperu.techcommunityperu.service.CheckoutService;
import com.techcommunityperu.techcommunityperu.integration.email.service.EmailService;
import com.techcommunityperu.techcommunityperu.service.InscripcionService;
import com.techcommunityperu.techcommunityperu.service.PurchaseService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final PayPalService payPalService;
    private final PurchaseService purchaseService;
    private final EmailService emailService;
    private final InscripcionMapper inscripcionMapper;
    private final InscriptionRepository inscriptionRepository;
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
    public PaymentCaptureResponse capturePayment(String orderId) throws MessagingException, IOException {
        OrderCaptureResponse orderCaptureResponse = payPalService.captureOrder(orderId);
        boolean completed = orderCaptureResponse.getStatus().equals("COMPLETED");

        PaymentCaptureResponse paypalCaptureResponse = new PaymentCaptureResponse();
        paypalCaptureResponse.setCompleted(completed);

        if (completed) {
            // Obtener datos de la inscripción
            String purchaseIdStr = orderCaptureResponse.getPurchaseUnits().get(0).getReferenceId();
            InscripcionDTO inscripcionDTO = purchaseService.confirmInscription(Integer.parseInt(purchaseIdStr));

            Integer eventoId = inscripcionDTO.getEvento().getId();
            Integer participanteId = inscripcionDTO.getParticipante().getId();

            Inscripcion inscripcion = inscriptionRepository.findByEventoIdAndParticipanteId(eventoId, participanteId);
            inscripcion.setInscripcionStatus(statusInscription.PAID);
            inscriptionRepository.save(inscripcion);

            // Generar QR y enviar correo
            generateAndSendEmailWithQr(inscripcion);

            paypalCaptureResponse.setInscriptionId(inscripcion.getParticipante().getId());
        }

        return paypalCaptureResponse;
    }

    private void generateAndSendEmailWithQr(Inscripcion inscripcion) throws MessagingException, IOException {
        // Generar datos para el modelo del correo
        String descripcionEvento = inscripcion.getEvento().getDescripcion();
        String nombreEvento = inscripcion.getEvento().getNombre();
        String nombreParticipante = inscripcion.getParticipante().getNombre();
        String apellidoParticipante = inscripcion.getParticipante().getApellido();
        String correoParticipante = inscripcion.getParticipante().getUsuarioId().getCorreoElectronico();
        Double montoInscripcion = inscripcion.getMonto();
        LocalDateTime fechaInicio = cronogramaRepository
                .findFirstByEventoIdOrderByFechaInicioAsc(inscripcion.getEvento().getId())
                .getFechaInicio();
        String estadoInscripcion = inscripcion.getInscripcionStatus().name();
        String tipoPago = inscripcion.getTipoPago().name();

        // Modelo para el correo
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

        // Generar el QR
        String tempDir = System.getProperty("java.io.tmpdir");
        String qrFilePath = tempDir + String.format("inscripcion_%d.png", inscripcion.getId());
        generateQrCode(inscripcion, qrFilePath); // Usamos la lógica de PurchaseServiceImpl

        // ID único para el contenido del QR
        String qrContentId = "qrCode_" + inscripcion.getId();
        model.put("qrCodePath", "cid:" + qrContentId); // Para el inline del correo

        // Crear correo con adjunto
        EmailDTO mail = emailService.createEmailWithAttachment(
                correoParticipante,
                "Confirmación de pago a evento",
                model,
                mailFrom,
                qrFilePath,
                qrContentId
        );

        // Enviar el correo
        emailService.sendEmail(mail, "confirmation-template");
    }

    private void generateQrCode(Inscripcion inscripcion, String qrFilePath) {
        // Usar el mismo formato del método en PurchaseServiceImpl
        String qrData = String.format("Evento: %s\nParticipante: %s %s\nMonto: %.2f\nEstado: %s",
                inscripcion.getEvento().getNombre(),
                inscripcion.getParticipante().getNombre(),
                inscripcion.getParticipante().getApellido(),
                inscripcion.getMonto(),
                inscripcion.getInscripcionStatus()
        );

        try {
            BitMatrix matrix = new MultiFormatWriter().encode(
                    qrData,
                    BarcodeFormat.QR_CODE,
                    300,  // Ancho del QR
                    300   // Alto del QR
            );
            MatrixToImageWriter.writeToPath(matrix, "PNG", Paths.get(qrFilePath));
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el código QR: " + e.getMessage());
        }
    }
}
