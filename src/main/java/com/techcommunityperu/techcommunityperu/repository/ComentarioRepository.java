package com.techcommunityperu.techcommunityperu.repository;

import com.techcommunityperu.techcommunityperu.model.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    Comentario findByEventoId(Integer eventoId);
    Comentario findByUsuarioId(Integer usuarioId);
}