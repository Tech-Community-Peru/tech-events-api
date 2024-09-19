package com.techcommunityperu.techcommunityperu.repository;

import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {

    @Query("SELECT i FROM Inscripcion i WHERE i.usuario.nombre = :nombreUsuario AND i.evento.nombre = :nombreEvento")
    Optional<Inscripcion> findByUsuarioNombreAndEventoNombre(@Param("nombreUsuario") String nombreUsuario, @Param("nombreEvento") String nombreEvento);
}