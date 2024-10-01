package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.model.entity.Usuario;
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

}
