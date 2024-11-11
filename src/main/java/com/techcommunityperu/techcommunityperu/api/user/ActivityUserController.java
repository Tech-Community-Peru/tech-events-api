package com.techcommunityperu.techcommunityperu.api.user;

import com.techcommunityperu.techcommunityperu.dto.EventoDTO;
import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;
import com.techcommunityperu.techcommunityperu.service.InscripcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/actividad")
@PreAuthorize("hasAnyRole('PARTICIPANTE', 'ADMINISTRADOR')")
public class ActivityUserController {
    @Autowired
    private InscripcionService inscripcionService;

    @GetMapping("/evento/{id_participante}")
    public List<EventoDTO> listarEventosPorParticipante(@PathVariable Integer id_participante) {
       List<Inscripcion> listInscripcion = inscripcionService.findByParticipanteId(id_participante);
       List<EventoDTO> listEventosDTO = new ArrayList<EventoDTO>();
       for(Inscripcion inscripcion : listInscripcion) {
           listEventosDTO.add(new EventoDTO(inscripcion.getEvento().getId(),inscripcion.getEvento().getNombre(),inscripcion.getEvento().getCosto(),inscripcion.getEvento().getDescripcion(),inscripcion.getEvento().getEventoCategoria().name(),inscripcion.getEvento().getTipoEvento().name(),inscripcion.getEvento().getUbicacion().getNombreLugar()));
       }
       return listEventosDTO;
    }
}
