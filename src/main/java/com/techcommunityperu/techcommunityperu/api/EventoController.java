package com.techcommunityperu.techcommunityperu.api;

import com.techcommunityperu.techcommunityperu.dto.EventoDTO;
import com.techcommunityperu.techcommunityperu.dto.EventoFiltroDTO;
import com.techcommunityperu.techcommunityperu.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventService eventService;

    @PostMapping("/filtrar")
    public ResponseEntity<?> filtrarEventos(@Valid @RequestBody EventoFiltroDTO filtroDTO) {
        // Este punto solo se ejecuta si las validaciones son correctas
        List<EventoDTO> eventos = eventService.filtrarEventosPorFechaYUbicacion(filtroDTO.getFechaInicio(), filtroDTO.getUbicacionId());
        return ResponseEntity.ok(eventos);
    }
}
