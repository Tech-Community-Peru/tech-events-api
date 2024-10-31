package com.techcommunityperu.techcommunityperu.service;

import com.techcommunityperu.techcommunityperu.dto.InscripcionDTO;
import com.techcommunityperu.techcommunityperu.model.enums.paymentType;
import jakarta.mail.MessagingException;

public interface PurchaseService {
    String purchaseTicket(Integer eventoId, Integer participanteId, paymentType tipoPago) throws MessagingException;

    double getCostoEvento(Integer eventoId);

    InscripcionDTO confirmInscription(int inscriptionId);
}