package com.techcommunityperu.techcommunityperu.repository;

import com.techcommunityperu.techcommunityperu.model.entity.RegistroEscaneo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RegistroEscaneoRepository extends JpaRepository<RegistroEscaneo, Integer> {
    List<RegistroEscaneo> findByEventoId(Integer eventoId);
    List<RegistroEscaneo> findByParticipanteId(Integer participanteId);
    boolean existsByEventoIdAndParticipanteId(Integer eventoId, Integer participanteId);
}
