package com.techcommunityperu.techcommunityperu.repository;

import com.techcommunityperu.techcommunityperu.model.entity.Evento;
import com.techcommunityperu.techcommunityperu.model.entity.Participante;
import com.techcommunityperu.techcommunityperu.model.entity.RegistroEscaneo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface RegistroEscaneoRepository extends JpaRepository<RegistroEscaneo, Integer> {
    List<RegistroEscaneo> findByEventoId(Integer eventoId);
    List<RegistroEscaneo> findByParticipanteId(Integer participanteId);
    boolean existsByEventoIdAndParticipanteId(Integer eventoId, Integer participanteId);
    // Método para obtener el último registro de escaneo de un participante en un evento
    Optional<RegistroEscaneo> findTopByEventoAndParticipanteOrderByFechaGeneracionDesc(Evento evento, Participante participante);
}

