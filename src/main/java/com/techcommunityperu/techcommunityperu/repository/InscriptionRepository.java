package com.techcommunityperu.techcommunityperu.repository;
import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;
import com.techcommunityperu.techcommunityperu.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.techcommunityperu.techcommunityperu.model.entity.Evento;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscripcion, Integer> {
    Optional<Inscripcion> findByUsuarioIdAndEventoId(Integer usuarioId, Integer eventoId);
    @Modifying
    @Query("DELETE FROM Inscripcion i WHERE i.evento.id = :eventoId AND i.usuario.id = :usuarioId")
    void deleteByEventoAndUsuario(@Param("eventoId") Integer eventoId, @Param("usuarioId") Integer usuarioId);

    @Query("SELECT i FROM Inscripcion i WHERE i.evento.id = :eventoId AND i.usuario.id = :usuarioId")
    Inscripcion findByEventoAndUsuario(@Param("eventoId") Integer eventoId, @Param("usuarioId") Integer usuarioId);

    List<Inscripcion> findByUsuario(Usuario usuario);
    List<Inscripcion> findByEvento(Evento evento);
}
