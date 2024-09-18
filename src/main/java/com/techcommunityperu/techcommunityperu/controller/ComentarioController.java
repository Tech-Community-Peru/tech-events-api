package com.techcommunityperu.techcommunityperu.controller;

import com.techcommunityperu.techcommunityperu.model.entity.Comentario;
import com.techcommunityperu.techcommunityperu.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

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
    public ResponseEntity<Void> eliminarComentario(@PathVariable Integer id) {
        comentarioService.eliminarComentario(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener un comentario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Comentario> obtenerComentarioPorId(@PathVariable Integer id) {
        return comentarioService.obtenerComentarioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}