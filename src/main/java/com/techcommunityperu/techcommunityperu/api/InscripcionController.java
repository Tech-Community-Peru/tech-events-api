package com.techcommunityperu.techcommunityperu.api;
import com.techcommunityperu.techcommunityperu.service.InscripcionService;
import lombok.RequiredArgsConstructor;
import java.util.Optional;
import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inscripcion")
@RequiredArgsConstructor
public class InscripcionController {
    private final InscripcionService inscripcionService;

    @GetMapping("/evento/{eventoId}/usuario/{usuarioId}")
    public ResponseEntity<String> verificarInscripcion(@PathVariable Integer eventoId, @PathVariable Integer usuarioId) {
        Optional<Inscripcion> inscripcion = inscripcionService.verificarInscripcion(usuarioId, eventoId);
        if (inscripcion.isPresent()) {
            return ResponseEntity.ok("El usuario está inscrito en el evento.");
        } else {
            return ResponseEntity.ok("El usuario NO está inscrito en el evento.");
        }
    }

    @DeleteMapping("/cancelar/{eventoId}/{usuarioId}")
    public ResponseEntity<String> cancelarInscripcion(
            @PathVariable("eventoId") Integer eventoId,
            @PathVariable("usuarioId") Integer usuarioId) {

        try {
            inscripcionService.cancelarInscripcion(eventoId, usuarioId);
            return ResponseEntity.ok("Evento cancelado.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
