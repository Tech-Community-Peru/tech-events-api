package com.techcommunityperu.techcommunityperu.api;

import com.techcommunityperu.techcommunityperu.integration.email.service.EmailService;
import com.techcommunityperu.techcommunityperu.service.impl.FavoritosServiceImpl;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@AllArgsConstructor
@RequestMapping("/email")
@PreAuthorize("hasAnyRole('ADMINISTRADOR')")

public class EmailController {

    @Autowired
    private FavoritosServiceImpl favoritosService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/favoritos/{idInscripcion}")
    public ResponseEntity<String> sendEmail(@PathVariable("idInscripcion")Integer idInscripcion) throws MessagingException {
            System.out.println("El id de usuario a enviar es:" + idInscripcion);
            favoritosService.favoritosEnviar(idInscripcion);
            return ResponseEntity.ok("Post Enviado");
    }

    @PostMapping("/invitacion/{inscripcionId}")
    public ResponseEntity<String> sendInvitacionEvento(@PathVariable("inscripcionId") Integer inscripcionId) throws MessagingException {
        //System.out.println(usuarioid.);
        System.out.println(inscripcionId);
        favoritosService.invitacionEventoCorreo(inscripcionId);
        return ResponseEntity.ok("Correo enviado");
    }
}
