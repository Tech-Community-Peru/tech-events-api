package com.techcommunityperu.techcommunityperu.service;

import com.techcommunityperu.techcommunityperu.dto.EventoCrearReqDTO;
import com.techcommunityperu.techcommunityperu.dto.EventoCrearResDTO;
import com.techcommunityperu.techcommunityperu.dto.EventoResDTO;
import com.techcommunityperu.techcommunityperu.model.entity.Evento;
import com.techcommunityperu.techcommunityperu.model.enums.categoryEvent;
import com.techcommunityperu.techcommunityperu.dto.EventoDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventService {
    List<EventoDTO> filtrarEventosPorFechaYUbicacion(LocalDateTime fechaInicio,  Integer ubicacionId);
    List<Evento> obtenerEventosPorTipo(categoryEvent tipoEvento);
    List<EventoDTO> getAll();

    EventoCrearResDTO crearEvento(EventoCrearReqDTO eventoCrearReqDTO);
    void eliminarEventoPorId(Integer id);
}
