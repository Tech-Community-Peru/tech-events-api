package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.dto.EventoCreateDTO;
import com.techcommunityperu.techcommunityperu.mapper.EventoMapper;
import com.techcommunityperu.techcommunityperu.model.entity.Comunidad;
import com.techcommunityperu.techcommunityperu.model.entity.Evento;
import com.techcommunityperu.techcommunityperu.model.entity.Ponente;
import com.techcommunityperu.techcommunityperu.model.entity.Ubicacion;
import com.techcommunityperu.techcommunityperu.repository.CommunityRepository;
import com.techcommunityperu.techcommunityperu.repository.EventoCreateRepository;
import com.techcommunityperu.techcommunityperu.repository.PonenteRepository;
import com.techcommunityperu.techcommunityperu.repository.UbicacionRepository;
import com.techcommunityperu.techcommunityperu.service.EventoCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EventoCreateServiceImpl implements EventoCreateService {

    private final EventoCreateRepository eventoCreateRepository;
    private final EventoMapper eventoMapper;
    private final UbicacionRepository ubicacionRepository;
    private final PonenteRepository ponenteRepository;
    private final CommunityRepository communityRepository;

    @Override
    public EventoCreateDTO createEvent(EventoCreateDTO eventoCreateDTO) {
        Evento evento = eventoMapper.toEntityA(eventoCreateDTO);
        evento = modificarEvento(evento, eventoCreateDTO);
        return eventoMapper.toDtoB(eventoCreateRepository.save(evento));
    }

    @Override
    public EventoCreateDTO updateEvent(EventoCreateDTO eventoCreateDTO, long id) {
        Evento evento = eventoCreateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

        evento.setNombre(eventoCreateDTO.getNombre());
        evento.setCosto(eventoCreateDTO.getCosto());
        evento.setDescripcion(eventoCreateDTO.getDescripcion());
        evento.setEventoCategoria(eventoCreateDTO.getEventoCategoria());
        evento.setTipoEvento(eventoCreateDTO.getTipoEvento());

        return eventoMapper.toDtoB(eventoCreateRepository.save(evento));

    }

    private Evento modificarEvento(Evento evento, EventoCreateDTO eventoCreateDTO) {
        Ubicacion ubicacion = ubicacionRepository.findByNombreLugar(eventoCreateDTO.getNombreUbicacion())
                .orElseThrow(() -> new RuntimeException("Ubicacion no encontrada"));
        evento.setUbicacion(ubicacion);
        Ponente ponente = ponenteRepository.findByNombre(eventoCreateDTO.getPonente())
                .orElseThrow(() -> new RuntimeException("Ponente no encontrada"));
        evento.setPonente(ponente);
        Comunidad comunidad = communityRepository.findByNombre(eventoCreateDTO.getComunidad())
                .orElseThrow(() -> new RuntimeException("Comunidad no encontrada"));
        evento.setComunidad(comunidad);
        return evento;
    }
}
