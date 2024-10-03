package com.techcommunityperu.techcommunityperu.api;

import com.techcommunityperu.techcommunityperu.dto.AsistenciaDTO;
import com.techcommunityperu.techcommunityperu.model.entity.Asistencia;
import com.techcommunityperu.techcommunityperu.service.ReporteAsistenciaService;
import com.techcommunityperu.techcommunityperu.service.impl.ReporteAsistenciaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("reportes")
public class ReporteAsistenciaController {

    @Autowired
    private ReporteAsistenciaServiceImpl reporteAsistenciaService;

    @GetMapping("/evento/{eventoId}")
    public List<AsistenciaDTO> obtenerReporteAsistencia(@PathVariable Long eventoId) {
        return reporteAsistenciaService.generarReporteAsistencia(eventoId);
    }
}