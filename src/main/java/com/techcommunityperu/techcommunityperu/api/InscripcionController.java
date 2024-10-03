package com.techcommunityperu.techcommunityperu.api;

import com.techcommunityperu.techcommunityperu.dto.InscripcionDTO;
import com.techcommunityperu.techcommunityperu.exception.ResourceNotFoundException;
import com.techcommunityperu.techcommunityperu.mapper.InscripcionMapper;
import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;
import com.techcommunityperu.techcommunityperu.service.InscripcionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/inscripcion")
@RequiredArgsConstructor
public class InscripcionController {

    private final InscripcionService inscripcionService;
    private final InscripcionMapper inscripcionMapper;

    @PostMapping
    public ResponseEntity<String> crearInscripcion(@Valid @RequestBody InscripcionDTO inscripcionDTO) {
        try {
            inscripcionService.crearInscripcion(inscripcionDTO);
            return ResponseEntity.ok("Inscripción creada exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear la inscripción: " + e.getMessage());
        }
    }

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
            return ResponseEntity.ok("Inscripción cancelada.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al cancelar la inscripción: " + e.getMessage());
        }
    }
}