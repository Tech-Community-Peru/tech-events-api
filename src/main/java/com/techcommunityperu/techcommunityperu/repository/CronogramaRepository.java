package com.techcommunityperu.techcommunityperu.repository;

import com.techcommunityperu.techcommunityperu.model.entity.Cronograma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CronogramaRepository extends JpaRepository<Cronograma, Integer> {

    @Query("SELECT c FROM Cronograma c WHERE c.fechaInicio BETWEEN :fechaInicio AND :fechaFin AND c.evento.ubicacion.id = :ubicacionId")
    List<Cronograma> findByFechaInicioBetweenAndUbicacionId(
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin,
            @Param("ubicacionId") Integer ubicacionId);
}
