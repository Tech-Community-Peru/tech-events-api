package com.techcommunityperu.techcommunityperu.api;
import com.techcommunityperu.techcommunityperu.service.InscripcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/inscripcion")
@RequiredArgsConstructor
public class InscripcionController {

    private final InscripcionService inscripcionService;

    @DeleteMapping("/cancelar/{eventoId}/{usuarioId}")
    public ResponseEntity<String> cancelarInscripcion(
            @PathVariable("eventoId") Integer eventoId,
            @PathVariable("usuarioId") Integer usuarioId) {

        try {
            inscripcionService.cancelarInscripcion(eventoId, usuarioId);
            return ResponseEntity.ok("Evento cancelado");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
