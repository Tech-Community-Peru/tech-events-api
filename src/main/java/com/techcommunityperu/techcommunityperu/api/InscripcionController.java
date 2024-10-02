package com.techcommunityperu.techcommunityperu.api;

import com.techcommunityperu.techcommunityperu.dto.InscripcionDTO;
import com.techcommunityperu.techcommunityperu.mapper.InscripcionMapper;
import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;
import com.techcommunityperu.techcommunityperu.service.InscripcionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inscripcion")
@RequiredArgsConstructor
public class InscripcionController {
    private final InscripcionService inscripcionService;
    private final InscripcionMapper inscripcionMapper;

    @PostMapping
    public ResponseEntity<String> crearInscripcion(@Valid @RequestBody InscripcionDTO inscripcionDTO) {
        inscripcionService.crearInscripcion(inscripcionDTO);
        return ResponseEntity.ok("Inscripción creada exitosamente");
    }

    @GetMapping("/evento/{eventoId}/usuario/{usuarioId}")
    public ResponseEntity<InscripcionDTO> verificarInscripcion(@PathVariable Integer eventoId, @PathVariable Integer usuarioId) {
        Inscripcion inscripcion = inscripcionService.obtenerInscripcionPorEventoYUsuario(eventoId, usuarioId);
        InscripcionDTO inscripcionDTO = inscripcionMapper.toDto(inscripcion);
        return ResponseEntity.ok(inscripcionDTO);
    }

    @DeleteMapping("/cancelar/{eventoId}/{usuarioId}")
    public ResponseEntity<String> cancelarInscripcion(@PathVariable("eventoId") Integer eventoId, @PathVariable("usuarioId") Integer usuarioId) {
        inscripcionService.cancelarInscripcion(eventoId, usuarioId);
        return ResponseEntity.ok("Inscripción cancelada.");
    }
}

