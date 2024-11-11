package com.techcommunityperu.techcommunityperu.api.report;

import com.techcommunityperu.techcommunityperu.dto.RegistroEscaneoDTO;
import com.techcommunityperu.techcommunityperu.service.EstadisticasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estadisticas")
public class EstadisticasController {

    @Autowired
    private EstadisticasService estadisticasService;

    @GetMapping("/eventos/{eventoId}/total-escaneos")
    public ResponseEntity<Long> obtenerNumeroDeEscaneosPorEvento(@PathVariable Integer eventoId) {
        long totalEscaneos = estadisticasService.obtenerNumeroDeEscaneosPorEvento(eventoId);
        return ResponseEntity.ok(totalEscaneos);
    }

    @GetMapping("/participantes/{participanteId}/total-escaneos")
    public ResponseEntity<Long> obtenerNumeroDeEscaneosPorParticipante(@PathVariable Integer participanteId) {
        long totalEscaneos = estadisticasService.obtenerNumeroDeEscaneosPorParticipante(participanteId);
        return ResponseEntity.ok(totalEscaneos);
    }

    @GetMapping("/eventos/{eventoId}/escaneos")
    public ResponseEntity<List<RegistroEscaneoDTO>> obtenerEscaneosPorEvento(@PathVariable Integer eventoId) {
        List<RegistroEscaneoDTO> escaneos = estadisticasService.obtenerEscaneosPorEvento(eventoId);
        return ResponseEntity.ok(escaneos);
    }
}
