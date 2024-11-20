package com.techcommunityperu.techcommunityperu.api.event;

import com.techcommunityperu.techcommunityperu.dto.EventoResDTO;
import com.techcommunityperu.techcommunityperu.service.InscripcionService;
import jakarta.validation.Valid;
import com.techcommunityperu.techcommunityperu.dto.InscripcionDTO;
import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inscripcion")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMINISTRADOR')")
public class InscripcionController {

    private final InscripcionService inscripcionService;

    @GetMapping
    public ResponseEntity<List<InscripcionDTO>> getAllCategories(){
        List<InscripcionDTO> inscripcionList = inscripcionService.getAll();
        return new ResponseEntity<>(inscripcionList,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InscripcionDTO> create(@Valid @RequestBody InscripcionDTO inscripcionDTO) {
        InscripcionDTO createdInscripcionDTO = inscripcionService.create(inscripcionDTO);
        return new ResponseEntity<>(createdInscripcionDTO, HttpStatus.CREATED);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<InscripcionDTO>> paginate(@PageableDefault(size = 5, sort = "firstName")
                                                         Pageable pageable) {
        Page<InscripcionDTO> page = inscripcionService.paginate(pageable);
         return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InscripcionDTO> getById(@PathVariable Integer id) {
        InscripcionDTO inscripcionDTO = inscripcionService.findById(id);
        return new ResponseEntity<>(inscripcionDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InscripcionDTO> update(@PathVariable Integer id,@Valid @RequestBody InscripcionDTO inscripcionDTO) {
        InscripcionDTO updatedInscripcion = inscripcionService.update(id, inscripcionDTO);
        return new ResponseEntity<>(updatedInscripcion, HttpStatus.OK);
    }

    @GetMapping("/evento/{eventoId}/participante/{participanteId}")
    public ResponseEntity<String> verificarInscripcion(@PathVariable Integer eventoId, @PathVariable Integer participanteId) {
        Optional<Inscripcion> inscripcion = inscripcionService.verificarInscripcion(participanteId, eventoId);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        inscripcionService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PreAuthorize("hasRole('PARTICIPANTE')")
    @GetMapping("/participante/{idParticipante}/evento")
    public ResponseEntity<List<EventoResDTO>> getEventosInscritosPorParticipante(@PathVariable Integer idParticipante) {
        List<EventoResDTO> eventos = inscripcionService.getEventosPorParticipante(idParticipante);
        return new ResponseEntity<>(eventos, HttpStatus.OK);
    }
}