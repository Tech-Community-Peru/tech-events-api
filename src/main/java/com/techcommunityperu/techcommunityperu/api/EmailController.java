package com.techcommunityperu.techcommunityperu.api;

import com.techcommunityperu.techcommunityperu.model.entity.Evento;
import com.techcommunityperu.techcommunityperu.model.entity.Usuario;
import com.techcommunityperu.techcommunityperu.service.EmailService;
import com.techcommunityperu.techcommunityperu.service.impl.FavoritosServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private FavoritosServiceImpl favoritosService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/favoritos/{correoElectronico}")
    public ResponseEntity<String> sendEmail(@PathVariable("correoElectronico")String correoElectronico) {
        System.out.println(correoElectronico);
        return ResponseEntity.ok(favoritosService.favoritosEnviar(correoElectronico));
    }

    @PostMapping("/invitacion/evento/{usuarioid}/{eventoid}")
    public ResponseEntity<String> sendInvitacionEvento(@PathVariable("usuarioid") Usuario usuarioid, @PathVariable("eventoid") Evento eventoid) {
        //System.out.println(usuarioid.);
        System.out.println(usuarioid);
        System.out.println(eventoid);
        return ResponseEntity.ok(favoritosService.invitacionEventoCorreo(usuarioid, eventoid));
    }

}
