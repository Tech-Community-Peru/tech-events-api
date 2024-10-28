package com.techcommunityperu.techcommunityperu.api;

import com.techcommunityperu.techcommunityperu.dto.ParticipanteActivoDTO;
import com.techcommunityperu.techcommunityperu.model.entity.Asistencia;
import com.techcommunityperu.techcommunityperu.model.entity.Evento;
import com.techcommunityperu.techcommunityperu.model.entity.Participante;
import com.techcommunityperu.techcommunityperu.repository.AsistenciaRepository;
import com.techcommunityperu.techcommunityperu.repository.EventRepository;
import com.techcommunityperu.techcommunityperu.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/asistencia")
public class AsistenciaController {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @GetMapping("/estado/{eventId}/{participantId}")
    public ResponseEntity<?> verificarEstadoAsistencia(@PathVariable Integer eventId, @PathVariable Integer participantId) {
        Evento evento = eventRepository.findById(eventId).orElse(null);
        if (evento == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evento no encontrado.");
        }

        Participante participante = participantRepository.findById(participantId).orElse(null);
        if (participante == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Participante no encontrado.");
        }

        Asistencia asistencia = asistenciaRepository.findByEventoAndParticipante(evento, participante).orElse(null);
        if (asistencia == null) {
            return ResponseEntity.status(HttpStatus.OK).body("El participante no ha registrado asistencia para este evento.");
        }

        String estado = asistencia.isAsistio() ? "Activo (Asistió)" : "Inactivo (No asistió)";
        return ResponseEntity.ok("Estado de asistencia: " + estado);
    }

    @GetMapping("/activos/{eventId}")
    public ResponseEntity<?> obtenerParticipantesActivos(@PathVariable Integer eventId) {
        Evento evento = eventRepository.findById(eventId).orElse(null);
        if (evento == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evento no encontrado.");
        }

        List<Asistencia> asistencias = asistenciaRepository.findByEventoAndAsistioTrue(evento);
        List<ParticipanteActivoDTO> participantesActivos = asistencias.stream()
                .map(asistencia -> {
                    Participante participante = asistencia.getParticipante();
                    return new ParticipanteActivoDTO(
                            participante.getId(),
                            participante.getNombre(),
                            participante.getApellido()
                    );
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(participantesActivos);
    }
}

