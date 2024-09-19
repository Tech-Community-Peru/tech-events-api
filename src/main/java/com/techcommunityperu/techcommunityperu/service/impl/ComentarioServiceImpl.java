package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.model.entity.Comentario;
import com.techcommunityperu.techcommunityperu.repository.ComentarioRepository;
import com.techcommunityperu.techcommunityperu.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return comentarioRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public void eliminarComentario(Integer id) {
        comentarioRepository.deleteById(id);
    }

    @Override
    public Optional<Comentario> obtenerComentarioPorId(Integer id) {
        return comentarioRepository.findById(id);
    }
}
