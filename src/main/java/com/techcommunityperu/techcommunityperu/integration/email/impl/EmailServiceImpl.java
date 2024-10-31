package com.techcommunityperu.techcommunityperu.integration.email.impl;

import com.techcommunityperu.techcommunityperu.integration.email.dto.EmailDTO;
import com.techcommunityperu.techcommunityperu.integration.email.service.EmailService;
import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Map;


@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

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

    @Override
    public EmailDTO createEmail(String to, String subject, Map<String, Object> model, String from) {
        EmailDTO mail = new EmailDTO();
        mail.setFrom(from);
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setModel(model);
        return mail;
    }

    @Override
    public void sendEmail(EmailDTO mail, String templateName) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "UTF-8");

        Context context = new Context();
        context.setVariables(mail.getModel());

        // Procesar la plantilla usando Thymeleaf
        String html = templateEngine.process(templateName, context);
        helper.setTo(mail.getTo());
        helper.setText(html, true);
        helper.setSubject(mail.getSubject());
        helper.setFrom(mail.getFrom());

        // Si necesitas adjuntar un archivo
        //helper.addAttachment("MyTestFile.txt", new ByteArrayResource("test".getBytes()));

        mailSender.send(message);
    }
}
