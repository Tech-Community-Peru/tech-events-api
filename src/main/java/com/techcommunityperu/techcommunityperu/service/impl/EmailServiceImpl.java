package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.service.EmailService;
import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

    @Override
    public void sendConfirmationEmail(Inscripcion inscripcion, double monto) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("techcommunityperu@gmail.com"); // El correo que configurarás
            message.setTo(inscripcion.getParticipante().getUsuarioId().getCorreoElectronico()); // Obtener el correo del usuario asociado al participante
            message.setSubject("Confirmación de inscripción al evento " + inscripcion.getEvento().getNombre());

            // Construir el contenido del correo
            String messageBody = "Hola " + inscripcion.getParticipante().getNombre() + ",\n\n"
                    + "Te has inscrito exitosamente al evento " + inscripcion.getEvento().getNombre() + ".\n"
                    + "Estado de inscripción: " + inscripcion.getInscripcionStatus() + "\n"
                    + "Monto pagado: " + monto + " " + inscripcion.getTipoPago() + "\n\n"
                    + "Gracias por participar.\n\n"
                    + "Saludos,\nTechCommunityPeru";

            message.setText(messageBody);

            mailSender.send(message); // Envía el correo
        } catch (Exception e) {
            e.printStackTrace();
            // Manejo de errores
            throw new RuntimeException("Error al enviar el correo: " + e.getMessage());
        }
    }

    private JavaMailSender mailSender;

    public String sendEmail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("techcommunityperu@gmail.com");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
            return "Email sent";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al enviar el correo: " + e.getMessage();
        }
    }



}
