package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.dto.EventoDTO;
import com.techcommunityperu.techcommunityperu.mapper.EventoMapper;
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
import com.techcommunityperu.techcommunityperu.model.enums.categoryEvent;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {
    @Autowired
    private CronogramaRepository cronogramaRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventoMapper eventoMapper;

    @Override
    @Transactional
    public List<EventoDTO> getAll() {
        List<Evento> eventoList = eventRepository.findAll();
        return eventoList.stream()
                .map(eventoMapper::toDtoA)
                .toList();
    }

    public List<Evento> obtenerEventosPorTipo(categoryEvent tipoEvento) {
        return eventRepository.findByTipoEvento(tipoEvento);
    }
     @Override
    public List<EventoDTO> filtrarEventosPorFechaYUbicacion(LocalDateTime fechaInicio, Integer ubicacionId) {
        // Usar el nuevo método para filtrar por fecha de inicio y ubicación
        List<Cronograma> cronogramas = cronogramaRepository.findByFechaInicioAndUbicacionId(fechaInicio, ubicacionId);

        List<Evento> eventos = cronogramas.stream()
                .map(Cronograma::getEvento)
                .distinct()
                .collect(Collectors.toList());

        return eventos.stream()
                .map(this::convertirADTO)
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
        dto.setNombreUbicacion(evento.getUbicacion().getNombreLugar());
        return dto;
    }
}
