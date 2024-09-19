package com.techcommunityperu.techcommunityperu.repository;

import com.techcommunityperu.techcommunityperu.model.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Evento, Integer> {
    // Métodos personalizados para buscar eventos por nombre o categoría
    List<Evento> findByNombre(String nombre);
    List<Evento> findByEventoCategoria(String categoria); // Utiliza String si categoria_evento es de tipo String en la base de datos
}