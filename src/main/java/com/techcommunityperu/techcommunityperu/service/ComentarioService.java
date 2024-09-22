package com.techcommunityperu.techcommunityperu.service;

import com.techcommunityperu.techcommunityperu.model.entity.Comentario;

import java.util.List;

public interface ComentarioService {
    Comentario guardarComentario(Comentario comentario);
    Comentario obtenerComentariosPorEvento(Integer eventoId);
    Comentario obtenerComentariosPorUsuario(Integer usuarioId);
    void eliminarComentario(Integer id);
    Comentario obtenerComentarioPorId(Integer id);
}