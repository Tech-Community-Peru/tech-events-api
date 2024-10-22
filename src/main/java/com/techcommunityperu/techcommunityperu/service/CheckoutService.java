package com.techcommunityperu.techcommunityperu.service;

import com.techcommunityperu.techcommunityperu.dto.PaymentCaptureResponse;
import com.techcommunityperu.techcommunityperu.dto.PaymentOrderResponse;

public interface CheckoutService {

    PaymentOrderResponse createPaymentUrl(Integer purchaseId, String returnUrl, String cancelUrl);

    PaymentCaptureResponse capturePayment(String orderId);

}
