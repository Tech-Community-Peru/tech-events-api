package com.techcommunityperu.techcommunityperu.service;

import com.techcommunityperu.techcommunityperu.model.entity.Comentario;

import java.util.List;
import java.util.Optional;

public interface ComentarioService {
    Comentario guardarComentario(Comentario comentario);
    List<Comentario> obtenerComentariosPorEvento(Integer eventoId);
    List<Comentario> obtenerComentariosPorUsuario(Integer usuarioId);
    void eliminarComentario(Integer id);
    Optional<Comentario> obtenerComentarioPorId(Integer id);
}
