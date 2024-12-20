package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.model.entity.Comentario;
import com.techcommunityperu.techcommunityperu.repository.ComentarioRepository;
import com.techcommunityperu.techcommunityperu.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Override
    public Comentario guardarComentario(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    @Override
    public List<Comentario> obtenerComentariosPorEvento(Integer eventoId) {
        return comentarioRepository.findByEventoId(eventoId);
    }

    @Override
    public List<Comentario> obtenerComentariosPorUsuario(Integer usuarioId) {
        return comentarioRepository.findByUsuarioId(usuarioId); // Cambiado a List<Comentario>
    }

    @Override
    public void eliminarComentario(Integer id) {
        if (!comentarioRepository.existsById(id)) {
            throw new NoSuchElementException("Comentario con id " + id + " no existe.");
        }
        comentarioRepository.deleteById(id);
    }

    @Override
    public Comentario obtenerComentarioPorId(Integer id) {
        return comentarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Comentario con ID " + id + " no encontrado"));
    }
}