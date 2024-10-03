package com.techcommunityperu.techcommunityperu.service;

import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;

import java.math.BigDecimal;

public interface EmailService {
    String sendEmail(String to, String subject, String text);
}
