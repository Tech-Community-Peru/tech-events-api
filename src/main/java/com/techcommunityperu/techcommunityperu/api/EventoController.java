package com.techcommunityperu.techcommunityperu.api;

import com.techcommunityperu.techcommunityperu.dto.EventoDTO;
import com.techcommunityperu.techcommunityperu.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventService eventService;

    @GetMapping("/filtrar")
    public ResponseEntity<List<EventoDTO>> filtrarEventos(
            @RequestParam("fechaInicio") LocalDateTime fechaInicio,
            @RequestParam("fechaFin") LocalDateTime fechaFin,
            @RequestParam("ubicacionId") Integer ubicacionId) {

        List<EventoDTO> eventos = eventService.filtrarEventosPorFechaYUbicacion(fechaInicio, fechaFin, ubicacionId);

        if (eventos.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }

        return ResponseEntity.ok(eventos); // 200 OK
    }
}
