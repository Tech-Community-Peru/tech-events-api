package com.techcommunityperu.techcommunityperu.integration.email.service;

import com.techcommunityperu.techcommunityperu.integration.email.dto.EmailDTO;
import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;
import jakarta.mail.MessagingException;

import java.util.Map;

public interface EmailService {
    void sendConfirmationEmail(Inscripcion inscripcion, double monto);
    EmailDTO createEmail(String to, String subject, Map<String, Object> model,String from);
    void sendEmail(EmailDTO mail, String templateName) throws MessagingException;
}
