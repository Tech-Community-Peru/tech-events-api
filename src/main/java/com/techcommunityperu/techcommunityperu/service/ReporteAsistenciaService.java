package com.techcommunityperu.techcommunityperu.service;

import com.techcommunityperu.techcommunityperu.dto.AsistenciaDTO;

import java.util.List;

public interface ReporteAsistenciaService {
    List<AsistenciaDTO> generarReporteAsistencia(Long eventoId);
}
