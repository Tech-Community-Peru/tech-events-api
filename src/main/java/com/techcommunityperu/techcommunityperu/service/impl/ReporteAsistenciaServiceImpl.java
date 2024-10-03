package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.dto.AsistenciaDTO;
import com.techcommunityperu.techcommunityperu.model.entity.Asistencia;
import com.techcommunityperu.techcommunityperu.repository.AsistenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteAsistenciaServiceImpl {
    @Autowired
    private AsistenciaRepository asistenciaRepository;

    public List<AsistenciaDTO> generarReporteAsistencia(Long eventoId) {
        // Obtener la lista de asistencias asociadas al evento
        List<Asistencia> asistencias = asistenciaRepository.findByEventoId(eventoId);

        // Convertir las entidades Asistencia a AsistenciaDTO para el reporte
        return asistencias.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    //Método auxiliar para convertir la entidad Asistencia a DTO
    private AsistenciaDTO convertirADTO(Asistencia asistencia) {
        AsistenciaDTO dto = new AsistenciaDTO();
        dto.setNombreParticipante(asistencia.getParticipante().getNombre());
        dto.setAsistio(asistencia.isAsistio());
        dto.setFechaAsistencia(asistencia.getFechaAsistencia());
        return dto;
    }
}
