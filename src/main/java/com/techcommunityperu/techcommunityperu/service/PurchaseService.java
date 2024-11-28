package com.techcommunityperu.techcommunityperu.service;

import com.techcommunityperu.techcommunityperu.dto.InscripcionDTO;
import com.techcommunityperu.techcommunityperu.model.enums.paymentType;
import jakarta.mail.MessagingException;

import java.io.IOException;

public interface PurchaseService {
    String purchaseTicket(Integer eventoId, Integer participanteId, paymentType tipoPago) throws MessagingException, IOException;

    double getCostoEvento(Integer eventoId);

    InscripcionDTO confirmInscription(int inscriptionId);


}