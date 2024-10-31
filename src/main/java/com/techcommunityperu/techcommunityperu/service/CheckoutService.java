package com.techcommunityperu.techcommunityperu.service;

import com.techcommunityperu.techcommunityperu.dto.PaymentCaptureResponse;
import com.techcommunityperu.techcommunityperu.dto.PaymentOrderResponse;
import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;
import jakarta.mail.MessagingException;

public interface CheckoutService {

    PaymentOrderResponse createPaymentUrl(Integer purchaseId, String returnUrl, String cancelUrl);

    PaymentCaptureResponse capturePayment(String orderId) throws MessagingException;

}
