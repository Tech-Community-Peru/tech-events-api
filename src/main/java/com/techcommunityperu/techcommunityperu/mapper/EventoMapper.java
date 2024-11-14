package com.techcommunityperu.techcommunityperu.mapper;

import com.techcommunityperu.techcommunityperu.dto.CommunityDTO;
import com.techcommunityperu.techcommunityperu.dto.EventoDTO;
import com.techcommunityperu.techcommunityperu.dto.EventoResDTO;
import com.techcommunityperu.techcommunityperu.model.entity.Comunidad;
import com.techcommunityperu.techcommunityperu.model.entity.Evento;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EventoMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public Evento toEntity(EventoResDTO dto) {
        return modelMapper.map(dto, Evento.class);
    }

    public EventoResDTO toDto(Evento entity) {
        return modelMapper.map(entity, EventoResDTO.class);
    }
}
