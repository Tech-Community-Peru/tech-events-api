package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.service.EmailService;
import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class EmailServiceImpl implements EmailService {

    @Override
    public void sendConfirmationEmail(Inscripcion inscripcion, double monto) {
        // En una implementacion real, aquí iría la lógica de envío de correos electrónicos
        System.out.println("Enviando correo de confirmación para la inscripción ID: " + inscripcion.getId());
    }
}
