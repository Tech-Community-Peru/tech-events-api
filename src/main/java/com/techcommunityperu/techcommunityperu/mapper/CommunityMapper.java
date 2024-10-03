package com.techcommunityperu.techcommunityperu.mapper;

import com.techcommunityperu.techcommunityperu.dto.CommunityDTO;
import com.techcommunityperu.techcommunityperu.model.entity.Comunidad;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CommunityMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public Comunidad toEntity(CommunityDTO dto) {
        return modelMapper.map(dto, Comunidad.class);
    }

    public CommunityDTO toDto(Comunidad entity) {
        return modelMapper.map(entity, CommunityDTO.class);
    }
}
