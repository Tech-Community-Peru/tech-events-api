package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.dto.InscripcionDTO;
import com.techcommunityperu.techcommunityperu.dto.PaymentCaptureResponse;
import com.techcommunityperu.techcommunityperu.dto.PaymentOrderResponse;
import com.techcommunityperu.techcommunityperu.integration.payment.paypal.dto.OrderCaptureResponse;
import com.techcommunityperu.techcommunityperu.integration.payment.paypal.dto.OrderResponse;
import com.techcommunityperu.techcommunityperu.integration.payment.paypal.service.PayPalService;
import com.techcommunityperu.techcommunityperu.service.CheckoutService;
import com.techcommunityperu.techcommunityperu.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final PayPalService payPalService;
    private final PurchaseService purchaseService;

    @Override
    public PaymentOrderResponse createPaymentUrl(Integer purchaseId, String returnUrl, String cancelUrl) {
        OrderResponse orderResponse = payPalService.createOrder(purchaseId, returnUrl, cancelUrl);

        String paypalUrl = orderResponse
                .getLinks()
                .stream()
                .filter(link -> link.getRel().equals("approve"))
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .getHref();

        return new PaymentOrderResponse(paypalUrl);
    }

    @Override
    public PaymentCaptureResponse capturePayment(String orderId) {
        OrderCaptureResponse orderCaptureResponse = payPalService.captureOrder(orderId);
        boolean completed = orderCaptureResponse.getStatus().equals("COMPLETED");

        PaymentCaptureResponse paypalCaptureResponse = new PaymentCaptureResponse();
        paypalCaptureResponse.setCompleted(completed);

        if (completed) {
            String purchaseIdStr = orderCaptureResponse.getPurchaseUnits().get(0).getReferenceId();

            InscripcionDTO inscripcionDTO = purchaseService.confirmInscription(Integer.parseInt(purchaseIdStr));

            paypalCaptureResponse.setPurchaseId(inscripcionDTO.getParticipante().getId());
        }

        return paypalCaptureResponse;
    }
}
