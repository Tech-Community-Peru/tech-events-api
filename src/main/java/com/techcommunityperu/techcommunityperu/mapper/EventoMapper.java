package com.techcommunityperu.techcommunityperu.mapper;

import com.techcommunityperu.techcommunityperu.dto.EventoCreateDTO;
import com.techcommunityperu.techcommunityperu.dto.EventoDTO;
import com.techcommunityperu.techcommunityperu.dto.EventoResDTO;
import com.techcommunityperu.techcommunityperu.model.entity.Evento;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EventoMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public Evento toEntity(EventoResDTO dto) {
        return modelMapper.map(dto, Evento.class);
    }

    public Evento toEntityA(EventoCreateDTO dto) {
        Evento evento = new Evento();
        evento.setNombre(dto.getNombre());
        evento.setCosto(dto.getCosto());
        evento.setDescripcion(dto.getDescripcion());
        evento.setEventoCategoria(dto.getEventoCategoria());
        evento.setTipoEvento(dto.getTipoEvento());
        return evento;
    }

    public EventoResDTO toDto(Evento entity) {
        return modelMapper.map(entity, EventoResDTO.class);
    }

    public EventoDTO toDtoA(Evento entity) {
        return modelMapper.map(entity, EventoDTO.class);
    }

    public EventoCreateDTO toDtoB(Evento evento) {
        EventoCreateDTO dto = new EventoCreateDTO();
        dto.setNombre(evento.getNombre());
        dto.setCosto(evento.getCosto());
        dto.setDescripcion(evento.getDescripcion());
        dto.setEventoCategoria(evento.getEventoCategoria());
        dto.setTipoEvento(evento.getTipoEvento());
        dto.setNombreUbicacion(evento.getUbicacion() != null ? evento.getUbicacion().getNombreLugar() : null);
        dto.setPonente(evento.getPonente() != null ? evento.getPonente().getNombre() : null);
        dto.setComunidad(evento.getComunidad() != null ? evento.getComunidad().getNombre() : null);
        return dto;
    }
}
