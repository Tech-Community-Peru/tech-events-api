package com.techcommunityperu.techcommunityperu.repository;

import com.techcommunityperu.techcommunityperu.dto.EventoCreateDTO;
import com.techcommunityperu.techcommunityperu.model.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventoCreateRepository extends JpaRepository<Evento, Long> {

    Optional<Evento> findByNombre(String nombre);

}
