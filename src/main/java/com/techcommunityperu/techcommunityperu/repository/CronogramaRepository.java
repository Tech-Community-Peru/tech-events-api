package com.techcommunityperu.techcommunityperu.repository;

import com.techcommunityperu.techcommunityperu.model.entity.Cronograma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CronogramaRepository extends JpaRepository<Cronograma, Integer> {

    // Solo filtrar por fecha de inicio y ubicación
    @Query("SELECT c FROM Cronograma c WHERE c.fechaInicio >= :fechaInicio AND c.evento.ubicacion.id = :ubicacionId")
    List<Cronograma> findByFechaInicioAndUbicacionId(
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("ubicacionId") Integer ubicacionId);
}
