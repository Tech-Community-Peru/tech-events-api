package com.techcommunityperu.techcommunityperu.api;

import com.techcommunityperu.techcommunityperu.dto.EventoDTO;
import com.techcommunityperu.techcommunityperu.dto.EventoFiltroDTO;
import com.techcommunityperu.techcommunityperu.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import com.techcommunityperu.techcommunityperu.model.entity.Evento;
import com.techcommunityperu.techcommunityperu.model.enums.categoryEvent;
import com.techcommunityperu.techcommunityperu.service.impl.EventServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/evento")
@PreAuthorize("hasAnyRole('PARTICIPANTE', 'PONENTE', 'ADMINISTRADOR')")

public class EventoController {


    private  final EventServiceImpl eventService;


    @PostMapping("/filtrar")
    public ResponseEntity<?> filtrarEventos(@Valid @RequestBody EventoFiltroDTO filtroDTO) {
        // Este punto solo se ejecuta si las validaciones son correctas
        List<EventoDTO> eventos = eventService.filtrarEventosPorFechaYUbicacion(filtroDTO.getFechaInicio(), filtroDTO.getUbicacionId());
        return ResponseEntity.ok(eventos);
    }
    @GetMapping("/filtrarCategoria")
    public ResponseEntity<List<Evento>> filtrarEventosPorTipo(@RequestParam categoryEvent tipoEvento) {
        List<Evento> eventos = eventService.obtenerEventosPorTipo(tipoEvento);

        if (eventos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            return ResponseEntity.ok(eventos);
        }
    }
}
