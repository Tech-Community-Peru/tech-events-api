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

    public void favoritosEnviar(Integer idInscripcion) throws MessagingException {
        Optional<Inscripcion> inscripcion = inscriptionRepository.findById(idInscripcion);
        String emailDestino = inscripcion.get().getParticipante().getUsuarioId().getCorreoElectronico();
        String nombreParticipante = inscripcion.get().getParticipante().getNombre();
        String apellidoParticipante = inscripcion.get().getParticipante().getApellido();

        Map<String, Object> model = new HashMap<>();
        model.put("correoElectronico", emailDestino);
        model.put("nombre", nombreParticipante);
        model.put("apellido", apellidoParticipante);
        EmailDTO mail = emailService.createEmail(
                emailDestino /*emailUser*/,
                "Favoritos",
                model,
                mailFrom
        );
        emailService.sendEmail(mail, "favoritos-send");
    }

    public void invitacionEventoCorreo(Integer inscripcionId) throws MessagingException {
        Optional<Inscripcion> inscripcion = inscriptionRepository.findById(inscripcionId);
        String descripcionEvento= inscripcion.get().getEvento().getDescripcion();
        String nombreEvento = inscripcion.get().getEvento().getNombre();
        String nombreParticipante = inscripcion.get().getParticipante().getNombre();
        String apellidoParticipante = inscripcion.get().getParticipante().getApellido();
        String correoParticipante = inscripcion.get().getParticipante().getUsuarioId().getCorreoElectronico();
        Double montoEvento= inscripcion.get().getMonto();
        String tipoPago=  inscripcion.get().getTipoPago().toString();
        String statusInscripcion = inscripcion.get().getInscripcionStatus().toString();
        // Ruta relativa del QR
        String qrPath = String.format("qrCodes/inscripcion_%d.png", inscripcionId);

//        Mapeo para el formato de html
        Map<String, Object> model = new HashMap<>();
        model.put("correoElectronico", correoParticipante);
        model.put("nombreParticipante", nombreParticipante);
        model.put("apellidoParticipante", apellidoParticipante);
        model.put("nombreEvento", nombreEvento);
        model.put("descripcionEvento", descripcionEvento);
        model.put("montoInscripcion", montoEvento);
        model.put("tipoPago", tipoPago);
        model.put("estadoInscripcion", statusInscripcion);
        model.put("qrCodePath", qrPath); // Añade la ruta del QR al modelo

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
