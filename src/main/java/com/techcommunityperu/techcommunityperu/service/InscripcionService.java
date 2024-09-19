package com.techcommunityperu.techcommunityperu.service;
import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;
import java.util.Optional;

public interface InscripcionService {
    void cancelarInscripcion(Integer eventoId, Integer usuarioId);
    Inscripcion obtenerInscripcionPorEventoYUsuario(Integer eventoId, Integer usuarioId);
    Optional<Inscripcion> verificarInscripcion(Integer usuarioId, Integer eventoId);
}
