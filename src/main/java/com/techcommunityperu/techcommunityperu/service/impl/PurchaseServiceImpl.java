package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.dto.InscripcionDTO;
import com.techcommunityperu.techcommunityperu.exceptions.InscriptionException;
import com.techcommunityperu.techcommunityperu.exceptions.ResourceNotFoundException;
import com.techcommunityperu.techcommunityperu.integration.email.dto.EmailDTO;
import com.techcommunityperu.techcommunityperu.mapper.EventoMapper;
import com.techcommunityperu.techcommunityperu.mapper.ParticipanteMapper;
import com.techcommunityperu.techcommunityperu.model.entity.Evento;
import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;
import com.techcommunityperu.techcommunityperu.model.entity.Participante;
import com.techcommunityperu.techcommunityperu.model.enums.paymentStatus;
import com.techcommunityperu.techcommunityperu.model.enums.paymentType;
import com.techcommunityperu.techcommunityperu.model.enums.statusInscription;
import com.techcommunityperu.techcommunityperu.repository.EventRepository;
import com.techcommunityperu.techcommunityperu.repository.InscriptionRepository;
import com.techcommunityperu.techcommunityperu.repository.ParticipantRepository;
import com.techcommunityperu.techcommunityperu.service.PaymentService;
import com.techcommunityperu.techcommunityperu.service.PurchaseService;
import com.techcommunityperu.techcommunityperu.integration.email.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import java.nio.file.Paths;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private EventRepository eventoRepository;

    @Autowired
    private InscriptionRepository inscriptionRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EventoMapper eventoMapper;

    @Autowired
    private ParticipanteMapper participanteMapper;


    @Value("${spring.mail.username}")
    private String mailFrom;



    public void checkoutEmail(Inscripcion inscripcionId) throws MessagingException {
        String descripcionEvento= inscripcionId.getEvento().getDescripcion();
        String nombreEvento = inscripcionId.getEvento().getNombre();
        String nombreParticipante = inscripcionId.getParticipante().getNombre();
        String apellidoParticipante = inscripcionId.getParticipante().getApellido();
        String correoParticipante = inscripcionId.getParticipante().getUsuarioId().getCorreoElectronico();
        Double montoInscripcion = inscripcionId.getMonto();
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

//        Configuracion del mensje del email
        EmailDTO mail = emailService.createEmail(
                correoParticipante,
                "Confirmacion de pago a evento",
                model,
                mailFrom
        );
        emailService.sendEmail(mail,"confirmation-template");
    }

    // Metodo para validar si el tipo de pago es reconocido
    private void validatePaymentType(paymentType tipoPago) {
        if (tipoPago == null) {
            throw new InscriptionException("Ese Tipo de Pago no es reconocido");
        }
    }

    @Override
    public double getCostoEvento(Integer eventoId) {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new InscriptionException("Evento no encontrado"));
        return evento.getCosto();
    }

    @Override
    public InscripcionDTO confirmInscription(int inscriptionId) {
        Inscripcion inscripcion = inscriptionRepository.findById(inscriptionId)
                .orElseThrow(() -> new ResourceNotFoundException("Inscripción no encontrada"));

        // Aquí puedes construir el InscripcionDTO
        InscripcionDTO inscripcionDTO = new InscripcionDTO();
        inscripcionDTO.setTipoPago(inscripcion.getTipoPago());
        inscripcionDTO.setMonto(inscripcion.getMonto());
        inscripcionDTO.setStatus(inscripcion.getInscripcionStatus());
        inscripcionDTO.setParticipante(participanteMapper.toDto(inscripcion.getParticipante()));
        inscripcionDTO.setEvento(eventoMapper.toDto(inscripcion.getEvento()));

        return inscripcionDTO;
    }

    @Override
    public String purchaseTicket(Integer eventoId, Integer participanteId, paymentType tipoPago) throws MessagingException {
        // Validar el tipo de pago
        validatePaymentType(tipoPago);

        // Buscar el evento
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new InscriptionException("Evento no encontrado"));

        // Buscar el participante
        Participante participante = participantRepository.findById(participanteId)
                .orElseThrow(() -> new InscriptionException("Participante no encontrado"));

        // Validar si el costo del evento es mayor a 0 y el tipo de pago es FREE
        if (evento.getCosto() > 0 && tipoPago == paymentType.FREE) {
            return "Error: No puedes usar el tipo de pago FREE para eventos con costo mayor a 0.";
        }

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setTipoPago(tipoPago);
        inscripcion.setMonto(evento.getCosto());
        inscripcion.setEvento(evento);
        inscripcion.setInscripcionStatus(statusInscription.PENDING);
        inscripcion.setParticipante(participante);

        // Verificar si el costo del evento es 0
        if (evento.getCosto() == 0) {
            inscripcion.setInscripcionStatus(statusInscription.PAID);
            inscriptionRepository.save(inscripcion);
            generateQrCode(inscripcion); // Generar y guardar el QR
            checkoutEmail(inscripcion);
            return "Recibirás un correo con tu entrada gratuita.";
        }

        inscriptionRepository.save(inscripcion);
        generateQrCode(inscripcion); // Generar y guardar el QR

        if (inscripcion.getInscripcionStatus() == statusInscription.PENDING) {
            return "Redirigiendo a Paypal.";
        } else {
            return "Error en el pago. Por favor, inténtalo de nuevo.";
        }
    }

    private void generateQrCode(Inscripcion inscripcion) {
        String qrData = String.format("Evento: %s\nParticipante: %s %s\nMonto: %.2f\nEstado: %s",
                inscripcion.getEvento().getNombre(),
                inscripcion.getParticipante().getNombre(),
                inscripcion.getParticipante().getApellido(),
                inscripcion.getMonto(),
                inscripcion.getInscripcionStatus().name()
        );

        String qrFilePath = String.format("src/main/resources/qrCodes/inscripcion_%d.png", inscripcion.getId());

        try {
            BitMatrix matrix = new MultiFormatWriter().encode(
                    qrData,
                    BarcodeFormat.QR_CODE,
                    300, // Ancho del QR
                    300  // Alto del QR
            );
            MatrixToImageWriter.writeToPath(matrix, "PNG", Paths.get(qrFilePath));
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el código QR: " + e.getMessage());
        }
    }
}