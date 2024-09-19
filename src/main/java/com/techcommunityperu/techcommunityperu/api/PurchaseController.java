package com.techcommunityperu.techcommunityperu.api;

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
    public String purchaseTicket(@RequestParam Integer eventoId,@RequestParam Integer usuarioId,@RequestParam paymentType tipoPago){
        return purchaseService.purchaseTicket(eventoId, usuarioId, tipoPago);
    }
}