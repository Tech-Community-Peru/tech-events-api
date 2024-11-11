package com.techcommunityperu.techcommunityperu.api.email;

import com.techcommunityperu.techcommunityperu.integration.email.service.EmailService;
import com.techcommunityperu.techcommunityperu.service.PasswordResetTokenService;
import com.techcommunityperu.techcommunityperu.service.impl.FavoritosServiceImpl;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/email")
@PreAuthorize("hasAnyRole('ADMINISTRADOR')")

public class EmailController {

    @Autowired
    private FavoritosServiceImpl favoritosService;

    @Autowired
    private EmailService emailService;

    private final PasswordResetTokenService passwordResetTokenService;

    @PostMapping("/sendMail")
    public ResponseEntity<Void> sendPasswordResetMail(@RequestBody String email) throws Exception {
        passwordResetTokenService.createAndSendPasswordResetToken(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    Verificar si el token es valido
    @GetMapping("/reset/check/{token}")
    public ResponseEntity<Boolean> checkTokenValidity(@PathVariable("token") String token) {
        boolean isValid = passwordResetTokenService.isValidToken(token);
        return new ResponseEntity<>(isValid, HttpStatus.OK);
    }

    @PostMapping("/favoritos/{idInscripcion}")
    public ResponseEntity<String> sendEmail(@PathVariable("idInscripcion")Integer idInscripcion) throws MessagingException {
            System.out.println("El id de usuario a enviar es:" + idInscripcion);
            favoritosService.favoritosEnviar(idInscripcion);
            return ResponseEntity.ok("Post Enviado");
    }

    // Restablecer la contraseña usando el token
    @PostMapping("/reset/{token}")
    public ResponseEntity<Void> resetPassword(@PathVariable("token") String token, @RequestBody String newPassword) {
        passwordResetTokenService.resetPassword(token, newPassword);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/invitacion/{inscripcionId}")
    public ResponseEntity<String> sendInvitacionEvento(@PathVariable("inscripcionId") Integer inscripcionId) throws MessagingException {
        //System.out.println(usuarioid.);
        System.out.println(inscripcionId);
        favoritosService.invitacionEventoCorreo(inscripcionId);
        return ResponseEntity.ok("Correo enviado");
    }
}
