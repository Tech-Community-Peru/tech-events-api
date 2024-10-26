package com.techcommunityperu.techcommunityperu.api;

import com.techcommunityperu.techcommunityperu.model.entity.Evento;
import com.techcommunityperu.techcommunityperu.model.entity.Participante;
import com.techcommunityperu.techcommunityperu.service.EmailService;
import com.techcommunityperu.techcommunityperu.service.impl.FavoritosServiceImpl;
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

    @PostMapping("/favoritos/{correoElectronico}")
    public ResponseEntity<String> sendEmail(@PathVariable("correoElectronico")String correoElectronico) {
        System.out.println(correoElectronico);
        return ResponseEntity.ok(favoritosService.favoritosEnviar(correoElectronico));
    }

    @PostMapping("/invitacion/{participanteid}/{eventoid}")
    public ResponseEntity<String> sendInvitacionEvento(@PathVariable("participanteid") Integer participanteid, @PathVariable("eventoid") Integer eventoid) {
        //System.out.println(usuarioid.);
        System.out.println(participanteid);
        System.out.println(eventoid);
        return ResponseEntity.ok(favoritosService.invitacionEventoCorreo(participanteid,eventoid ));
    }
}
