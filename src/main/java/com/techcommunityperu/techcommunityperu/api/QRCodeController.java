package com.techcommunityperu.techcommunityperu.api;

import com.google.zxing.WriterException;
import com.techcommunityperu.techcommunityperu.model.entity.Evento;
import com.techcommunityperu.techcommunityperu.repository.EventRepository;
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

@RestController
@RequestMapping("/qrcode")
public class QRCodeController {
    @Autowired
    private QRCodeService qrCodeService;

    @Autowired
    private EventRepository eventRepository; // Repositorio para acceder a los datos del evento

    // Endpoint para generar el QR de un participante y mostrar detalles del evento
    @GetMapping("/generate/{eventId}/{participantId}")
    public ResponseEntity<byte[]> generateQRCode(@PathVariable Integer eventId, @PathVariable Integer participantId) {
        // Obtener los detalles del evento desde la base de datos
        Evento evento = eventRepository.findById(eventId).orElse(null);

        if (evento == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Si el evento no existe
        }

        // Contenido del QR: incluye los detalles del evento
        String qrContent = "Evento: " + evento.getNombre() + "\n" +
                "Descripción: " + evento.getDescripcion() + "\n" +
                "Ubicación: " + evento.getUbicacion().getNombreLugar() + "\n" +
                "Participante ID: " + participantId;

        byte[] qrCodeImage = new byte[0];

        try {
            // Genera el código QR con los detalles del evento y el participante
            qrCodeImage = qrCodeService.generateQRCodeImage(qrContent, 250, 250);
        } catch (WriterException | IOException e) {
            // Retorna un mensaje de error en caso de falla
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        // Configura las cabeceras para devolver la imagen como PNG
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "image/png");
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(qrCodeImage);
    }
}