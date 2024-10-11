package com.techcommunityperu.techcommunityperu.api;

import com.techcommunityperu.techcommunityperu.exceptions.InvalidPaymentTypeException; // Importar la excepción personalizada
import com.techcommunityperu.techcommunityperu.model.enums.paymentType;
import com.techcommunityperu.techcommunityperu.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/purchase")
    public String purchaseTicket(@RequestParam Integer eventoId,
                                 @RequestParam Integer participanteId,
                                 @RequestParam paymentType tipoPago) {
        // Validar si el evento tiene un costo mayor a 0 y el tipo de pago es FREE
        if (tipoPago == paymentType.FREE && eventoId != null) {
            // Verificar el costo del evento desde el servicio
            double costoEvento = purchaseService.getCostoEvento(eventoId); // Implementa este metodo en PurchaseService
            if (costoEvento > 0) {
                throw new InvalidPaymentTypeException("Error: No puedes usar el tipo de pago FREE para eventos con costo mayor a 0.");
            }
        }
        return purchaseService.purchaseTicket(eventoId, participanteId, tipoPago);
    }
}