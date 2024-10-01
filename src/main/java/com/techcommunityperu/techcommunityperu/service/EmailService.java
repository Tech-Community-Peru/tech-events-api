package com.techcommunityperu.techcommunityperu.service;

import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;

import java.math.BigDecimal;

public interface EmailService {
    void sendConfirmationEmail(Inscripcion inscripcion, double monto);
    String sendEmail(String to, String subject, String text);
}
