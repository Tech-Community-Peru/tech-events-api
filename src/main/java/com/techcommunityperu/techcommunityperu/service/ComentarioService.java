package com.techcommunityperu.techcommunityperu.service;

import com.techcommunityperu.techcommunityperu.model.entity.Comentario;

import java.util.List;

public interface ComentarioService {

    Comentario guardarComentario(Comentario comentario);
    List<Comentario> obtenerComentariosPorEvento(Integer eventoId);
    List<Comentario> obtenerComentariosPorUsuario(Integer usuarioId); // Cambiado a List<Comentario>
    void eliminarComentario(Integer id);
    Comentario obtenerComentarioPorId(Integer id);
}