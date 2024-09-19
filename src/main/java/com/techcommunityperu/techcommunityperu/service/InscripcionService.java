package com.techcommunityperu.techcommunityperu.service;

import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;

public interface InscripcionService {
    void cancelarInscripcion(Integer eventoId, Integer usuarioId);
    Inscripcion obtenerInscripcionPorEventoYUsuario(Integer eventoId, Integer usuarioId);
}
