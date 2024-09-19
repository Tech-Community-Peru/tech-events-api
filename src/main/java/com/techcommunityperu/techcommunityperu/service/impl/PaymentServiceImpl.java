package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.model.enums.paymentStatus;
import com.techcommunityperu.techcommunityperu.model.enums.paymentType;
import com.techcommunityperu.techcommunityperu.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public paymentStatus processPayment(paymentType tipoPago, double monto) {
        // Lógica de procesamiento de pagos
        return paymentStatus.PAID; // O el estado adecuado según la lógica
    }
}
