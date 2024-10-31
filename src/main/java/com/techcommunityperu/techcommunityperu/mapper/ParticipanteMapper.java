package com.techcommunityperu.techcommunityperu.mapper;

import com.techcommunityperu.techcommunityperu.dto.ParticipanteDTO;
import com.techcommunityperu.techcommunityperu.model.entity.Participante;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ParticipanteMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public Participante toEntity(ParticipanteDTO dto) {
        return modelMapper.map(dto, Participante.class);
    }

    public ParticipanteDTO toDto(Participante entity) {
        return modelMapper.map(entity, ParticipanteDTO.class);
    }
}