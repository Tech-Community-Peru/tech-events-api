package com.techcommunityperu.techcommunityperu.service;

import com.techcommunityperu.techcommunityperu.dto.EventoDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    List<EventoDTO> filtrarEventosPorFechaYUbicacion(LocalDateTime fechaInicio, LocalDateTime fechaFin, Integer ubicacionId);
}
