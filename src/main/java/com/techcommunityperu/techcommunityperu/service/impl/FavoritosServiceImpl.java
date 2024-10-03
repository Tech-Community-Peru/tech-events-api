package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.exception.ResourceNotFoundException;
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
             Usuario usuario = userRepository.findByEmailQuery(correoElectronico)
                     .orElseThrow(()-> new ResourceNotFoundException("El correo :" + correoElectronico + " no existe en el sistema"));
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

    public String invitacionEventoCorreo(Usuario usuarioid,Evento eventoid) {
            Inscripcion inscripcion = inscriptionRepository.findByUsuarioAndEvento( usuarioid,eventoid );
            try {
                String titleEmail = "¡Somos Techcommunity Peru!";
                String messageEmail = "\n\nHola, : "+ usuarioid.getCorreoElectronico()+", \nAcabas de inscribite al evento:\n"+eventoid.getNombre()+"\n:Tipo de evento:\n"+eventoid.getTipoEvento()+"\nDescripcion:\n"+eventoid.getDescripcion();
                String resetLink = titleEmail + messageEmail;
                emailService.sendEmail(inscripcion.getUsuario().getCorreoElectronico(),"Inscripcion a evento de id:"+eventoid.getId()+" -> "+eventoid.getNombre(),resetLink);
                return "Correo enviado exitosamente";
            } catch (Exception e) {
                e.printStackTrace();
                return "Error, no enviado "+e.getMessage();
            }
        }

    }

