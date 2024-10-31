package com.techcommunityperu.techcommunityperu.service;

import com.techcommunityperu.techcommunityperu.model.enums.paymentType;
import org.springframework.stereotype.Service;

@Service
public class PaymentSecurityService {
    public boolean verifyPayment(paymentType paymentType, double amount) {
        if (paymentType == paymentType.CREDIT_CARD || paymentType == paymentType.DEBIT_CARD) {
            return amount > 0 && amount < 10000;
        }
        return false;
    }
}