package com.techcommunityperu.techcommunityperu.api;

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

    // Obtener todos los comentarios por evento
    @GetMapping("/evento/{eventoId}")
    public ResponseEntity<List<Comentario>> obtenerComentariosPorEvento(@PathVariable Integer eventoId) {
        List<Comentario> comentarios = comentarioService.obtenerComentariosPorEvento(eventoId);
        return ResponseEntity.ok(comentarios);
    }

    // Obtener todos los comentarios por usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Comentario>> obtenerComentariosPorUsuario(@PathVariable Integer usuarioId) {
        List<Comentario> comentarios = comentarioService.obtenerComentariosPorUsuario(usuarioId);
        return ResponseEntity.ok(comentarios);
    }

    // Guardar un comentario
    @PostMapping("/guardar")
    public ResponseEntity<Comentario> guardarComentario(@RequestBody Comentario comentario) {
        Comentario nuevoComentario = comentarioService.guardarComentario(comentario);
        return ResponseEntity.ok(nuevoComentario);
    }

    // Eliminar un comentario por ID
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarComentario(@PathVariable Integer id) {
        try {
            comentarioService.eliminarComentario(id);
            return ResponseEntity.ok("Comentario de id " + id + " eliminado");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al eliminar el comentario");
        }
    }

    // Obtener un comentario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Comentario> obtenerComentarioPorId(@PathVariable Integer id) {
        return comentarioService.obtenerComentarioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}