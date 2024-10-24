package com.techcommunityperu.techcommunityperu.dto;

import lombok.Data;

@Data
public class PaymentCaptureResponse {
    private boolean completed;
    private Integer inscriptionId;
}
