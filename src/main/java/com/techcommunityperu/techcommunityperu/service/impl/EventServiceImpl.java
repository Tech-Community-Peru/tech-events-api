package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.dto.EventoDTO;
import com.techcommunityperu.techcommunityperu.model.entity.Cronograma;
import com.techcommunityperu.techcommunityperu.model.entity.Evento;
import com.techcommunityperu.techcommunityperu.repository.CronogramaRepository;
import com.techcommunityperu.techcommunityperu.repository.EventRepository;
import com.techcommunityperu.techcommunityperu.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CronogramaRepository cronogramaRepository;

    @Override
    public List<EventoDTO> filtrarEventosPorFechaYUbicacion(LocalDateTime fechaInicio, LocalDateTime fechaFin, Integer ubicacionId) {
        List<Cronograma> cronogramas = cronogramaRepository.findByFechaInicioBetweenAndUbicacionId(fechaInicio, fechaFin, ubicacionId);

        List<Evento> eventos = cronogramas.stream()
                .map(Cronograma::getEvento)
                .distinct()
                .collect(Collectors.toList());

        return eventos.stream()
                .map(this::convertirADTO) // Suponiendo que tienes un método para convertir a DTO
                .collect(Collectors.toList());
    }

    private EventoDTO convertirADTO(Evento evento) {
        EventoDTO dto = new EventoDTO();
        dto.setId(evento.getId());
        dto.setNombre(evento.getNombre());
        dto.setCosto(evento.getCosto());
        dto.setDescripcion(evento.getDescripcion());
        dto.setEventoCategoria(evento.getEventoCategoria().name());
        dto.setTipoEvento(evento.getTipoEvento().name());
        dto.setNombreUbicacion(evento.getUbicacion().getNombreLugar()); // Suponiendo que quieres el nombre de la ubicación
        return dto;
    }
}
