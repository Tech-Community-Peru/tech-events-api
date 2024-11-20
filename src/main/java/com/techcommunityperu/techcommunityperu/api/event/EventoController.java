package com.techcommunityperu.techcommunityperu.api.event;

import com.techcommunityperu.techcommunityperu.dto.EventoDTO;
import com.techcommunityperu.techcommunityperu.dto.EventoFiltroDTO;
import com.techcommunityperu.techcommunityperu.dto.EventoResDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.techcommunityperu.techcommunityperu.model.entity.Evento;
import com.techcommunityperu.techcommunityperu.model.enums.categoryEvent;
import com.techcommunityperu.techcommunityperu.service.impl.EventServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/evento")

public class EventoController {


    private  final EventServiceImpl eventService;

    @GetMapping
    public ResponseEntity<List<EventoDTO>> getAllEvento(){
        List<EventoDTO> eventoList = eventService2.getAll();
        return new ResponseEntity<>(eventoList,HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('PARTICIPANTE', 'PONENTE', 'ADMINISTRADOR')")
    @PostMapping("/filtrar")
    public ResponseEntity<?> filtrarEventos(@Valid @RequestBody EventoFiltroDTO filtroDTO) {
        // Este punto solo se ejecuta si las validaciones son correctas
        List<EventoDTO> eventos = eventService.filtrarEventosPorFechaYUbicacion(filtroDTO.getFechaInicio(), filtroDTO.getUbicacionId());
        return ResponseEntity.ok(eventos);
    }
    @PreAuthorize("hasAnyRole('PARTICIPANTE', 'PONENTE', 'ADMINISTRADOR')")
    @GetMapping("/filtrarCategoria")
    public ResponseEntity<List<EventoResDTO>> filtrarEventosPorTipo(@RequestParam categoryEvent tipoEvento) {
        List<Evento> eventos = eventService.obtenerEventosPorTipo(tipoEvento);

        if (eventos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            List<EventoResDTO> evetosDTO = eventos.stream()
                    .map(eventoResDTO -> new EventoResDTO(eventoResDTO.getId(),eventoResDTO.getNombre(),eventoResDTO.getCosto(),eventoResDTO.getDescripcion(),eventoResDTO.getEventoCategoria(),eventoResDTO.getTipoEvento()))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(evetosDTO);
        }
    }
}
