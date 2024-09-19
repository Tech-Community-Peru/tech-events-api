package com.techcommunityperu.techcommunityperu.api;

import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;
import com.techcommunityperu.techcommunityperu.service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/inscripcion")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @GetMapping("/evento/{eventoId}/usuario/{usuarioId}")
    public ResponseEntity<String> verificarInscripcion(@PathVariable Integer eventoId, @PathVariable Integer usuarioId) {
        Optional<Inscripcion> inscripcion = inscripcionService.verificarInscripcion(usuarioId, eventoId);
        if (inscripcion.isPresent()) {
            return ResponseEntity.ok("El usuario está inscrito en el evento.");
        } else {
            return ResponseEntity.ok("El usuario NO está inscrito en el evento.");
        }
    }
}
