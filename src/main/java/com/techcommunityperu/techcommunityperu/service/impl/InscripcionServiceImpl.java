package com.techcommunityperu.techcommunityperu.service.impl;
import com.techcommunityperu.techcommunityperu.dto.EventoDTO;
import com.techcommunityperu.techcommunityperu.dto.EventoResDTO;
import com.techcommunityperu.techcommunityperu.dto.InscripcionDTO;
import com.techcommunityperu.techcommunityperu.exceptions.BadRequestException;
import com.techcommunityperu.techcommunityperu.exceptions.InscriptionException;
import com.techcommunityperu.techcommunityperu.exceptions.ResourceNotFoundException;
import com.techcommunityperu.techcommunityperu.mapper.EventoMapper;
import com.techcommunityperu.techcommunityperu.mapper.InscripcionMapper;
import com.techcommunityperu.techcommunityperu.model.entity.Evento;
import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;
import com.techcommunityperu.techcommunityperu.model.entity.Participante;
import com.techcommunityperu.techcommunityperu.repository.*;
import com.techcommunityperu.techcommunityperu.service.InscripcionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class InscripcionServiceImpl implements InscripcionService {
    //Para hacer la inyección de dependencias hay formas:
    //RequiredArgsConstructor : Genera un constructor
    //@Autowired: Crea constructor no es necesario poner private final

    private final InscriptionRepository inscriptionRepository;
    private final InscripcionMapper inscripcionMapper;
    private final ParticipantRepository participantRepository;
    private final GanadorRepository ganadorRepository;
    private final EventRepository eventoRepository;
    private final EventoMapper eventoMapperMapper;


    @Override
    public Optional<Inscripcion> verificarInscripcion(Integer participanteId, Integer eventoId) {
        return inscriptionRepository.findByParticipanteIdAndEventoId(participanteId, eventoId);
    }

    @Transactional
    @Override
    public void cancelarInscripcion(Integer eventoId, Integer participanteId) {
        Inscripcion inscripcion = inscriptionRepository.findByEventoIdAndParticipanteId(eventoId, participanteId);

        if (inscripcion != null) {
            ganadorRepository.deleteByInscripcionId(inscripcion.getId());
            inscriptionRepository.deleteById(inscripcion.getId());
            //inscriptionRepository.deleteByEventoAndParticipante(eventoId, participanteId);
        } else {
            throw new ResourceNotFoundException("No se encontró la inscripción para cancelar.");
        }
    }



    //CRUD INSCRIPCION
    @Transactional
    @Override
    public List<InscripcionDTO> getAll() {
        List<Inscripcion> inscripcionList = inscriptionRepository.findAll();
        return inscripcionList.stream()
                .map(inscripcionMapper::toDto)
                .toList();
    }

    @Transactional
    @Override
    public Page<InscripcionDTO> paginate(Pageable pageable) {
        Page<Inscripcion> inscripcionPage = inscriptionRepository.findAll(pageable);
        return inscripcionPage.map(inscripcionMapper::toDto);
    }

    @Transactional
    @Override
    public InscripcionDTO findById(Integer id) {
        Inscripcion inscripcion = inscriptionRepository.findById(id).orElseThrow(() -> new RuntimeException("La Inscripcion con ID:" + id + " no encontrado"));
        return inscripcionMapper.toDto(inscripcion);
    }

    @Transactional
    @Override
    public InscripcionDTO create(InscripcionDTO inscripcionDTO) {
        inscriptionRepository.findByParticipanteIdAndEventoId(inscripcionDTO.getParticipante().getId(), inscripcionDTO.getEvento().getId())
                .ifPresent(existingInscripcion -> {
                    throw new BadRequestException("No se puede crear la inscripción, debido a que ya hay una existente con el id de participante: "+
                            inscripcionDTO.getParticipante().getId()
                            +" y con el id de evento: "+inscripcionDTO.getEvento().getId());
                });
        Inscripcion inscripcion = inscripcionMapper.toEntity(inscripcionDTO);
        inscripcion = inscriptionRepository.save(inscripcion);
        return inscripcionMapper.toDto(inscripcion);
    }

    @Transactional
    @Override
    public InscripcionDTO update(Integer id, InscripcionDTO updatedInscripcionDTO) {
        Inscripcion inscripcionFromDb = inscriptionRepository.findById(id).orElseThrow(() -> new RuntimeException("La Inscripcion con ID:" + id + " no encontrado"));
        inscriptionRepository.findByParticipanteIdAndEventoId(updatedInscripcionDTO.getParticipante().getId(), updatedInscripcionDTO.getEvento().getId())
                .filter(existingInscripcion -> !existingInscripcion.getId().equals(id))
                .ifPresent(existingInscripcion -> {
                    throw new RuntimeException("La inscripcion ya existe");
                });
        //Actualizar

        Evento eventoConv = eventoMapperMapper.toEntity(updatedInscripcionDTO.getEvento()); // Usamos el DTO correcto
        inscripcionFromDb.setInscripcionStatus(updatedInscripcionDTO.getStatus());
        inscripcionFromDb.setEvento(eventoConv);
        inscripcionFromDb.setMonto(updatedInscripcionDTO.getMonto());
        inscripcionFromDb.setTipoPago(updatedInscripcionDTO.getTipoPago());
        inscripcionFromDb = inscriptionRepository.save(inscripcionFromDb);
        return inscripcionMapper.toDto(inscripcionFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Inscripcion inscripcion = inscriptionRepository.findById(id).orElseThrow(() -> new RuntimeException("La Inscripcion con ID:" + id + " no encontrado"));
        inscriptionRepository.delete(inscripcion);
    }

    @Transactional
    @Override
    public List<Inscripcion> findByParticipanteId(Integer participanteId) {
        List<Inscripcion> inscripcion = inscriptionRepository.findByParticipanteId(participanteId);
        return inscripcion;
    }

    @Transactional
    public List<EventoResDTO> getEventosPorParticipante(Integer idParticipante) {
        // Busca inscripciones relacionadas con el usuario
        List<Inscripcion> inscripciones = inscriptionRepository.findByParticipanteId(idParticipante);

        if (inscripciones.isEmpty()) {
            throw new ResourceNotFoundException("El participante con ID: " + idParticipante + " no tiene eventos inscritos.");
        }

        // Convierte los eventos asociados a DTOs
        return inscripciones.stream()
                .map(inscripcion -> eventoMapperMapper.toDto(inscripcion.getEvento()))
                .toList();
    }
}
