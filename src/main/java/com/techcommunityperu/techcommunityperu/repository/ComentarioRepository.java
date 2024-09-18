package com.techcommunityperu.techcommunityperu.repository;

import com.techcommunityperu.techcommunityperu.model.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {

    // Obtener comentarios por evento
    List<Comentario> findByEventoId(Integer eventoId);

    // Obtener comentarios por usuario
    List<Comentario> findByUsuarioId(Integer usuarioId);
}