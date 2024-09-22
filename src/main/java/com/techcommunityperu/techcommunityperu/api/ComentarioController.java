package com.techcommunityperu.techcommunityperu.api;

import com.techcommunityperu.techcommunityperu.dto.ComentarioDTO;
import com.techcommunityperu.techcommunityperu.model.entity.Comentario;
import com.techcommunityperu.techcommunityperu.service.ComentarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/comentarios")
@RequiredArgsConstructor
public class ComentarioController {

    private final ComentarioService comentarioService;

    @GetMapping("/evento/{eventoId}")
    public ResponseEntity<ComentarioDTO> obtenerComentariosPorEvento(@PathVariable Integer eventoId) {
        Comentario comentarios = comentarioService.obtenerComentariosPorEvento(eventoId);
        ComentarioDTO comentarioDTO = new ComentarioDTO(comentarios.getId(),comentarios.getComentario());
        return ResponseEntity.ok(comentarioDTO);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<ComentarioDTO> obtenerComentariosPorUsuario(@PathVariable Integer usuarioId) {
        Comentario comentarios = comentarioService.obtenerComentariosPorUsuario(usuarioId);
        ComentarioDTO comentarioDTO = new ComentarioDTO(comentarios.getId(),comentarios.getComentario());
        return ResponseEntity.ok(comentarioDTO);
    }

    @PostMapping("/guardar")
    public ResponseEntity<ComentarioDTO> guardarComentario(@RequestBody Comentario comentario) {
        Comentario nuevoComentario = comentarioService.guardarComentario(comentario);
        ComentarioDTO comentarioDTO = new ComentarioDTO(nuevoComentario.getId(),nuevoComentario.getComentario());
        return ResponseEntity.status(HttpStatus.CREATED).body(comentarioDTO);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarComentario(@PathVariable Integer id) {
        try {
            comentarioService.eliminarComentario(id);
            return ResponseEntity.ok("Comentario con ID " + id + " eliminado.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comentario no encontrado.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al eliminar el comentario.");
        }
    }

}