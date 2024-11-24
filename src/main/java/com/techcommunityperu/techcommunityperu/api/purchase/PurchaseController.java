package com.techcommunityperu.techcommunityperu.api.purchase;

import com.techcommunityperu.techcommunityperu.exceptions.InvalidPaymentTypeException; // Excepción personalizada
import com.techcommunityperu.techcommunityperu.model.enums.paymentType;
import com.techcommunityperu.techcommunityperu.service.PurchaseService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
@PreAuthorize("hasAnyRole('PARTICIPANTE', 'ADMINISTRADOR')")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseTicket(@RequestParam Integer eventoId,
                                            @RequestParam Integer participanteId,
                                            @RequestParam paymentType tipoPago) throws MessagingException {
        try {
            // Realizar la compra y obtener los detalles de la inscripción con el QR en Base64
            String response = purchaseService.purchaseTicket(eventoId, participanteId, tipoPago);

            // Aquí ya tienes la respuesta con el QR en Base64 y los detalles
            return ResponseEntity.ok(response);  // Retorna la respuesta como JSON al cliente

        } catch (InvalidPaymentTypeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error procesando la inscripción.");
        }
    }
}

