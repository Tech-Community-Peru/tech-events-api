package com.techcommunityperu.techcommunityperu.repository;

import com.techcommunityperu.techcommunityperu.model.entity.Asistencia;
import com.techcommunityperu.techcommunityperu.model.entity.Evento;
import com.techcommunityperu.techcommunityperu.model.entity.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    List<Asistencia> findByEventoId(Long eventoId);
    Optional<Asistencia> findByEventoAndParticipante(Evento evento, Participante participante);
    List<Asistencia> findByEventoAndAsistioTrue(Evento evento);
}