package com.techcommunityperu.techcommunityperu.service;

import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;

import java.util.Optional;

public interface InscripcionService {
    Optional<Inscripcion> verificarInscripcion(Integer usuarioId, Integer eventoId);
}
