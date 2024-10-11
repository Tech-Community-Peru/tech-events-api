package com.techcommunityperu.techcommunityperu.repository;

import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;
import com.techcommunityperu.techcommunityperu.model.entity.Evento;
import com.techcommunityperu.techcommunityperu.model.entity.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscripcion, Integer> {
    Optional<Inscripcion> findByParticipanteIdAndEventoId(Integer participanteId, Integer eventoId);
    @Modifying
    @Query("DELETE FROM Inscripcion i WHERE i.evento.id = :eventoId AND i.participante.id = :participanteId")
    void deleteByEventoAndParticipante(@Param("eventoId") Integer eventoId, @Param("participanteId") Integer participanteId);

    @Query("SELECT i FROM Inscripcion i WHERE i.evento.id = :eventoId AND i.participante.id = :participanteId")
    Inscripcion findByEventoIdAndParticipanteId(@Param("eventoId") Integer eventoId, @Param("participanteId") Integer participanteId);

    @Query("SELECT i FROM Inscripcion i WHERE i.evento.id = :eventoId AND i.participante.id = :participanteId")
    Inscripcion findByEventoAndParticipante(@Param("eventoId") Evento eventoId, @Param("participanteId") Participante participanteId);


    List<Inscripcion> findByParticipanteId(Integer participanteId);
    List<Inscripcion> findByEvento(Evento evento);
    List<Inscripcion> findAllByEventoId(Integer id);
    void deleteById(Integer id);
}
