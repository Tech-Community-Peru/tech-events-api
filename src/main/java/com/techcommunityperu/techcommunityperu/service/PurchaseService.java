package com.techcommunityperu.techcommunityperu.service;

import com.techcommunityperu.techcommunityperu.model.enums.paymentType;

public interface PurchaseService {
    String purchaseTicket(Integer eventoId, Integer usuarioId, paymentType tipoPago);

    double getCostoEvento(Integer eventoId);
}