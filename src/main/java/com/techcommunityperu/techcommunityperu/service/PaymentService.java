package com.techcommunityperu.techcommunityperu.service;

import com.techcommunityperu.techcommunityperu.model.enums.paymentStatus;
import com.techcommunityperu.techcommunityperu.model.enums.paymentType;

public interface PaymentService {
    paymentStatus processPayment(paymentType tipoPago, double monto);
}
