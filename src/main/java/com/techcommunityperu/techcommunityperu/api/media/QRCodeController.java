package com.techcommunityperu.techcommunityperu.api.media;

import com.google.zxing.WriterException;
import com.techcommunityperu.techcommunityperu.model.entity.Asistencia;
import com.techcommunityperu.techcommunityperu.model.entity.Evento;
import com.techcommunityperu.techcommunityperu.model.entity.Participante;
import com.techcommunityperu.techcommunityperu.model.entity.RegistroEscaneo;
import com.techcommunityperu.techcommunityperu.repository.AsistenciaRepository;
import com.techcommunityperu.techcommunityperu.repository.EventRepository;
import com.techcommunityperu.techcommunityperu.repository.ParticipantRepository;
import com.techcommunityperu.techcommunityperu.repository.RegistroEscaneoRepository;
import com.techcommunityperu.techcommunityperu.service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/qrcode")
//@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
public class QRCodeController {

    @Autowired
    private QRCodeService qrCodeService;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private RegistroEscaneoRepository registroEscaneoRepository;

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    @GetMapping("/generate/{eventId}/{participantId}")
    public ResponseEntity<?> generateQRCode(@PathVariable Integer eventId, @PathVariable Integer participantId) {
        Evento evento = eventRepository.findById(eventId).orElse(null);
        if (evento == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evento no encontrado.");
        }

        Participante participante = participantRepository.findById(participantId).orElse(null);
        if (participante == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Participante no encontrado.");
        }

        // Verificar si ya existe un registro de escaneo para este evento y participante
        RegistroEscaneo ultimoRegistro = registroEscaneoRepository
                .findTopByEventoAndParticipanteOrderByFechaGeneracionDesc(evento, participante)
                .orElse(null);

        if (ultimoRegistro != null) {
            // Verificar si ya ha escaneado previamente dentro del tiempo límite
            Duration duracion = Duration.between(ultimoRegistro.getFechaGeneracion(), LocalDateTime.now());
            if (duracion.toMinutes() < 2) {
                // Si el escaneo es reciente, retornar sin generar un nuevo QR ni contar el escaneo
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("El tiempo límite para escanear el QR ha expirado.");
            } else {
                // Si el escaneo es antiguo, no generar un nuevo QR, pero permitir que pase el tiempo límite
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("El participante ya está registrado para este evento.");
            }
        }

        // Generar el contenido del código QR
        String qrContent = "Evento: " + evento.getNombre() + "\n" +
                "Descripción: " + evento.getDescripcion() + "\n" +
                "Ubicación: " + evento.getUbicacion().getNombreLugar() + "\n" +
                "Participante ID: " + participante.getId() + "\n" +
                "Nombre del Participante: " + participante.getNombre() + " " + participante.getApellido();

        byte[] qrCodeImage;
        try {
            qrCodeImage = qrCodeService.generateQRCodeImage(qrContent, 250, 250);

            // Registrar el nuevo escaneo en la base de datos con la fecha de generación
            RegistroEscaneo registroEscaneo = new RegistroEscaneo();
            registroEscaneo.setEvento(evento);
            registroEscaneo.setParticipante(participante);
            registroEscaneo.setFechaEscaneo(LocalDateTime.now());
            registroEscaneo.setFechaGeneracion(LocalDateTime.now());
            registroEscaneoRepository.save(registroEscaneo);

            // Actualizar el estado de asistencia del participante
            Asistencia asistencia = asistenciaRepository.findByEventoAndParticipante(evento, participante)
                    .orElse(new Asistencia());
            asistencia.setParticipante(participante);
            asistencia.setEvento(evento);
            asistencia.setAsistio(true);
            asistencia.setFechaAsistencia(LocalDate.now());
            asistenciaRepository.save(asistencia);

        } catch (WriterException | IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al generar el QR.");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "image/png");
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(qrCodeImage);
    }
}