package com.techcommunityperu.techcommunityperu.api;

import com.google.zxing.WriterException;
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

    // Endpoint para generar el QR de un participante específico de un evento
    @GetMapping("/generate/{eventoId}/{usuarioId}")
    public ResponseEntity<byte[]> generateQRCode(@PathVariable Integer eventoId, @PathVariable Integer usuarioId) {
        String qrContent = "Evento ID: " + eventoId + " | Usuario ID: " + usuarioId;
        byte[] qrCodeImage = new byte[0];

        try {
            // Genera el código QR
            qrCodeImage = qrCodeService.generateQRCodeImage(qrContent, 350, 350);
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