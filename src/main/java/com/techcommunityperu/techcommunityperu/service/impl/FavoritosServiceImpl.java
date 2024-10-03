package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.model.entity.Evento;
import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;
import com.techcommunityperu.techcommunityperu.model.entity.Participante;
import com.techcommunityperu.techcommunityperu.model.entity.Usuario;
import com.techcommunityperu.techcommunityperu.repository.InscriptionRepository;
import com.techcommunityperu.techcommunityperu.repository.UserRepository;
import com.techcommunityperu.techcommunityperu.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoritosServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;
    @Autowired
    private InscriptionRepository inscriptionRepository;

    public String favoritosEnviar(String correoElectronico) {
        if (userRepository.existsByCorreoElectronico(correoElectronico)) {
            Usuario usuario = userRepository.findByEmailQuery(correoElectronico);
            try {
                String titleEmail = "¡Somos Techcommunity Peru!";
                String messageEmail = "\n\nHola, solicitaste subscribirte al apartado de favoritos "+ correoElectronico +", \nA partir de ahora te llegarán notificaciones relevantes sobre los gustos que elegiste.";
                String resetLink = titleEmail + messageEmail;
                emailService.sendEmail(usuario.getCorreoElectronico(), "Subscripcion a TechCommunityPeru",resetLink);
                return "Correo enviado exitosamente";
            } catch (Exception e) {
                e.printStackTrace();
                return "Token no enviado "+e.getMessage();
            }
        }
        return "Token no encontrado";
    }

    public String invitacionEventoCorreo(Participante participanteid,Evento eventoid ) {
        Inscripcion inscripcion = inscriptionRepository.findByEventoAndParticipante( eventoid, participanteid );
        try {
            String titleEmail = "¡Somos Techcommunity Peru!";
            String messageEmail = "\n\nHola, : "+ participanteid.getNombre()+", \nAcabas de inscribite al evento:\n"+eventoid.getNombre()+"\n:Tipo de evento:\n"+eventoid.getTipoEvento()+"\nDescripcion:\n"+eventoid.getDescripcion();
            String resetLink = titleEmail + messageEmail;
            emailService.sendEmail(inscripcion.getParticipante().getUsuarioId().getCorreoElectronico(),"Inscripcion a evento de id:"+eventoid.getId()+" -> "+eventoid.getNombre(),resetLink);
            return "Correo enviado exitosamente";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error, no enviado "+e.getMessage();
        }
    }

}
