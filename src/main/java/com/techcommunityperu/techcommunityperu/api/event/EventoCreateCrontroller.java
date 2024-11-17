package com.techcommunityperu.techcommunityperu.api.event;

import com.techcommunityperu.techcommunityperu.dto.EventoCreateDTO;
import com.techcommunityperu.techcommunityperu.service.EventoCreateService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/eventocu")
public class EventoCreateCrontroller {

    private final EventoCreateService eventoCreateService;

    @PreAuthorize("hasAnyRole('PARTICIPANTE', 'ADMINISTRADOR')")
    @PostMapping("/create")
    public ResponseEntity<?> createEvent(@Valid @RequestBody EventoCreateDTO eventoCreateDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errors);
        }
        EventoCreateDTO createdEvent = eventoCreateService.createEvent(eventoCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
    }

    @PreAuthorize("hasAnyRole('PARTICIPANTE', 'ADMINISTRADOR')")
    @PutMapping("/update")
    public ResponseEntity<EventoCreateDTO> updateEvent(@Valid @RequestBody EventoCreateDTO eventoCreateDTO) {
        EventoCreateDTO updateEvent = eventoCreateService.updateEvent(eventoCreateDTO);
        return new ResponseEntity<>(updateEvent, HttpStatus.OK);
    }

}
