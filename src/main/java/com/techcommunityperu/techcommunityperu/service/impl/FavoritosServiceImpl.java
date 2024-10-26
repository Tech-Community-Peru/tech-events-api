package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.integration.email.dto.EmailDTO;
import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;
import com.techcommunityperu.techcommunityperu.model.entity.Usuario;
import com.techcommunityperu.techcommunityperu.repository.InscriptionRepository;
import com.techcommunityperu.techcommunityperu.repository.UserRepository;
import com.techcommunityperu.techcommunityperu.integration.email.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
@Service
public class FavoritosServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;
    @Autowired
    private InscriptionRepository inscriptionRepository;

    @Value("${spring.mail.username}")
    private String mailFrom;

    public void favoritosEnviar(Integer idUsuario) throws MessagingException {
//        Usuario usuario = userRepository.findById(idUsuario).get();
//        String emailDestino = usuario.getCorreoElectronico();
////        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////        String emailUser = authentication.getName();
//
//
//        Map<String, Object> model = new HashMap<>();
//        model.put("correoElectronico", emailDestino /*emailUser*/);
//
//        EmailDTO mail = emailService.createEmail(
//                emailDestino /*emailUser*/,
//                "Favoritos",
//                model,
//                mailFrom
//        );
//        emailService.sendEmail(mail,"favoritos-send");
//        if (userRepository.existsByCorreoElectronico(correoElectronico)) {
//            Usuario usuario = userRepository.findByEmailQuery(correoElectronico);
//            try {
//                String titleEmail = "¡Somos Techcommunity Peru!";
//                String messageEmail = "\n\nHola, solicitaste subscribirte al apartado de favoritos "+ correoElectronico +", \nA partir de ahora te llegarán notificaciones relevantes sobre los gustos que elegiste.";
//                String resetLink = titleEmail + messageEmail;
//                emailService.sendEmail(usuario., "Subscripcion a TechCommunityPeru",resetLink);
//                return "Correo enviado exitosamente";
//            } catch (Exception e) {
//                e.printStackTrace();
//                return "Token no enviado "+e.getMessage();
//            }
//        }
//        return "Token no encontrado";
    }

    public void invitacionEventoCorreo(Integer inscripcionId) throws MessagingException {
        Optional<Inscripcion> inscripcion = inscriptionRepository.findById(inscripcionId);
        String descripcionEvento= inscripcion.get().getEvento().getDescripcion();
        String nombreEvento = inscripcion.get().getEvento().getNombre();
        String nombreParticipante = inscripcion.get().getParticipante().getNombre();
        String apellidoParticipante = inscripcion.get().getParticipante().getApellido();
        String correoParticipante = inscripcion.get().getParticipante().getUsuarioId().getCorreoElectronico();

//        Mapeo para el formato de html
        Map<String, Object> model = new HashMap<>();
        model.put("correoElectronico", correoParticipante);
        model.put("nombreParticipante", nombreParticipante);
        model.put("apellidoParticipante", apellidoParticipante);
        model.put("nombreEvento", nombreEvento);
        model.put("descripcionEvento", descripcionEvento);

//        Configuracion del mensje del email
        EmailDTO mail = emailService.createEmail(
                correoParticipante,
                "Invitacion a evento tech",
                model,
                mailFrom
        );
        emailService.sendEmail(mail,"invitacion-send");
    }

}
