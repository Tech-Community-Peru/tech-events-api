package com.techcommunityperu.techcommunityperu.service;

import com.techcommunityperu.techcommunityperu.model.entity.Comentario;
import com.techcommunityperu.techcommunityperu.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    // Guardar un comentario
    public Comentario guardarComentario(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    // Obtener todos los comentarios de un evento
    public List<Comentario> obtenerComentariosPorEvento(Integer eventoId) {
        return comentarioRepository.findByEventoId(eventoId);
    }

    // Obtener todos los comentarios de un usuario
    public List<Comentario> obtenerComentariosPorUsuario(Integer usuarioId) {
        return comentarioRepository.findByUsuarioId(usuarioId);
    }

    // Eliminar un comentario por ID
    public void eliminarComentario(Integer id) {
        comentarioRepository.deleteById(id);
    }

    // Obtener un comentario por ID
    public Optional<Comentario> obtenerComentarioPorId(Integer id) {
        return comentarioRepository.findById(id);
    }
}
