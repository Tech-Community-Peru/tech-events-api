package com.techcommunityperu.techcommunityperu.mapper;

import com.techcommunityperu.techcommunityperu.dto.QRDTO;
import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class QRMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public Inscripcion toEntity(QRDTO dto) {
        return modelMapper.map(dto, Inscripcion.class);
    }

    public QRDTO toDto(Inscripcion entity) {
        return modelMapper.map(entity, QRDTO.class);
    }

    public QRMapper() {
        modelMapper.typeMap(Inscripcion.class, QRDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getEvento().getNombre(), QRDTO::setEventoNombre);
            mapper.map(src -> src.getParticipante().getNombre(), QRDTO::setParticipanteNombre);
            mapper.map(src -> src.getParticipante().getApellido(), QRDTO::setParticipanteApellido);
            mapper.map(Inscripcion::getInscripcionStatus, QRDTO::setEstado);
        });
    }
}