package com.techcommunityperu.techcommunityperu.mapper;

import com.techcommunityperu.techcommunityperu.dto.InscripcionDTO;
import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class InscripcionMapper {

    private final ModelMapper modelMapper;

    public InscripcionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public InscripcionDTO toDto(Inscripcion inscripcion) {
        return modelMapper.map(inscripcion, InscripcionDTO.class);
    }

    public Inscripcion toEntity(InscripcionDTO inscripcionDTO) {
        return modelMapper.map(inscripcionDTO, Inscripcion.class);
    }
}
