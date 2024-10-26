package com.techcommunityperu.techcommunityperu.api;

import com.google.zxing.WriterException;
import com.techcommunityperu.techcommunityperu.model.entity.Evento;
import com.techcommunityperu.techcommunityperu.model.entity.Participante;
import com.techcommunityperu.techcommunityperu.repository.EventRepository;
import com.techcommunityperu.techcommunityperu.repository.ParticipantRepository;
import com.techcommunityperu.techcommunityperu.service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/qrcode")
@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
public class QRCodeController {

    @Autowired
    private QRCodeService qrCodeService;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @GetMapping("/generate/{eventId}/{participantId}")
    public ResponseEntity<byte[]> generateQRCode(@PathVariable Integer eventId, @PathVariable Integer participantId) {
        Evento evento = eventRepository.findById(eventId).orElse(null);
        if (evento == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Participante participante = participantRepository.findById(participantId).orElse(null);
        if (participante == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Incluir información del participante en el contenido del QR
        String qrContent = "Evento: " + evento.getNombre() + "\n" +
                "Descripción: " + evento.getDescripcion() + "\n" +
                "Ubicación: " + evento.getUbicacion().getNombreLugar() + "\n" +
                "Participante ID: " + participante.getId() + "\n" +
                "Nombre del Participante: " + participante.getNombre() + " " + participante.getApellido() + "\n" +
                "Ubicación del Participante: " + participante.getUbicacion();

        byte[] qrCodeImage;
        try {
            qrCodeImage = qrCodeService.generateQRCodeImage(qrContent, 250, 250);
        } catch (WriterException | IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "image/png");
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(qrCodeImage);
    }
}