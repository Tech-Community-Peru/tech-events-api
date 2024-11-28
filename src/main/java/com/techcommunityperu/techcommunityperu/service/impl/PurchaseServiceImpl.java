package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.dto.InscripcionDTO;
import com.techcommunityperu.techcommunityperu.dto.QRDTO;
import com.techcommunityperu.techcommunityperu.exceptions.InscriptionException;
import com.techcommunityperu.techcommunityperu.exceptions.ResourceNotFoundException;
import com.techcommunityperu.techcommunityperu.integration.email.dto.EmailDTO;
import com.techcommunityperu.techcommunityperu.mapper.EventoMapper;
import com.techcommunityperu.techcommunityperu.mapper.ParticipanteMapper;
import com.techcommunityperu.techcommunityperu.mapper.QRMapper;
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
import java.util.Optional;

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

    @Autowired
    private QRMapper qRMapper;


    @Value("${spring.mail.username}")
    private String mailFrom;



    public void checkoutEmail(Inscripcion inscripcionId) throws MessagingException, IOException {
        // Información básica
        String descripcionEvento = inscripcionId.getEvento().getDescripcion();
        String nombreEvento = inscripcionId.getEvento().getNombre();
        String nombreParticipante = inscripcionId.getParticipante().getNombre();
        String apellidoParticipante = inscripcionId.getParticipante().getApellido();
        String correoParticipante = inscripcionId.getParticipante().getUsuarioId().getCorreoElectronico();
        Double montoInscripcion = inscripcionId.getMonto();
        String estadoInscripcion = inscripcionId.getInscripcionStatus().name();
        String tipoPago = inscripcionId.getTipoPago().name();

        // Crear un mapa para el modelo
        Map<String, Object> model = new HashMap<>();
        model.put("correoElectronico", correoParticipante);
        model.put("nombreParticipante", nombreParticipante);
        model.put("apellidoParticipante", apellidoParticipante);
        model.put("nombreEvento", nombreEvento);
        model.put("descripcionEvento", descripcionEvento);
        model.put("estadoInscripcion", estadoInscripcion);
        model.put("tipoPago", tipoPago);
        model.put("montoInscripcion", montoInscripcion);

        // Generar el QR
        String tempDir = System.getProperty("java.io.tmpdir");
        String qrFilePath = tempDir + String.format("inscripcion_%d.png", inscripcionId.getId());
        generateQrCode(inscripcionId, qrFilePath); // Llamar al método para generar QR

        // ID único para el contenido del QR
        String qrContentId = "qrCode_" + inscripcionId.getId();
        model.put("qrCodePath", "cid:" + qrContentId); // Agregar al modelo

        // Crear el correo con el adjunto
        EmailDTO mail = emailService.createEmailWithAttachment(
                correoParticipante,
                "Confirmación de pago a evento",
                model,
                mailFrom,
                qrFilePath,
                qrContentId
        );

        // Enviar correo
        emailService.sendEmail(mail, "confirmation-template");
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
    public String purchaseTicket(Integer eventoId, Integer participanteId, paymentType tipoPago) throws MessagingException, IOException {

        // Validar el tipo de pago
        validatePaymentType(tipoPago);

        // Buscar el evento
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new InscriptionException("Evento no encontrado"));

        // Buscar el participante
        Participante participante = participantRepository.findById(participanteId)
                .orElseThrow(() -> new InscriptionException("Participante no encontrado"));

        // Verificar si ya existe una inscripción para este evento y participante
        Inscripcion existingInscripcion = inscriptionRepository.findByEventoIdAndParticipanteId(evento.getId(), participante.getId());

        if (existingInscripcion != null) {
            throw new InscriptionException("Ya estás inscrito en este evento.");
        }

        // Continuar con la lógica de creación de inscripción
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setTipoPago(tipoPago);
        inscripcion.setMonto(evento.getCosto());
        inscripcion.setEvento(evento);
        inscripcion.setParticipante(participante);
        inscripcion.setInscripcionStatus(statusInscription.PENDING);
        inscripcion.setParticipante(participante);

        // Generar ruta para guardar el QR
        String tempDir = System.getProperty("java.io.tmpdir"); // Directorio temporal del sistema
        String qrFilePath = tempDir + String.format("inscripcion_%d.png", inscripcion.getId());

        if (evento.getCosto() == 0) {
            inscripcion.setInscripcionStatus(statusInscription.PAID);
            inscriptionRepository.save(inscripcion);
            checkoutEmail(inscripcion); // Enviar correo con QR en un solo paso
            return "Recibirás un correo con tu entrada gratuita.";
        }

        inscriptionRepository.save(inscripcion);
        generateQrCode(inscripcion, qrFilePath); // Generar y guardar el QR

        if (inscripcion.getInscripcionStatus() == statusInscription.PENDING) {
            return "Redirigiendo a Paypal.";
        } else {
            return "Error en el pago. Por favor, inténtalo de nuevo.";
        }
    }

    private void generateQrCode(Inscripcion inscripcion, String qrFilePath) {
        // Convertir entidad a DTO
        QRDTO inscripcionDTO = qRMapper.toDto(inscripcion);

        String qrData = String.format("Evento: %s\nParticipante: %s %s\nMonto: %.2f\nEstado: %s",
                inscripcionDTO.getEventoNombre(),
                inscripcionDTO.getParticipanteNombre(),
                inscripcionDTO.getParticipanteApellido(),
                inscripcionDTO.getMonto(),
                inscripcionDTO.getEstado()
        );

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