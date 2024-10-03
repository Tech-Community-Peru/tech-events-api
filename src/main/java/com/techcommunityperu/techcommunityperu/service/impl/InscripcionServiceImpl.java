package com.techcommunityperu.techcommunityperu.service.impl;
import com.techcommunityperu.techcommunityperu.dto.InscripcionDTO;
import com.techcommunityperu.techcommunityperu.exceptions.ResourceNotFoundException;
import com.techcommunityperu.techcommunityperu.mapper.InscripcionMapper;
import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;
import com.techcommunityperu.techcommunityperu.model.entity.Participante;
import com.techcommunityperu.techcommunityperu.repository.InscriptionRepository;
import com.techcommunityperu.techcommunityperu.repository.ParticipantRepository;
import com.techcommunityperu.techcommunityperu.repository.UsuarioRepository;
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

    @Autowired
    private final InscriptionRepository inscriptionRepository;

    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private final InscripcionMapper inscripcionMapper;
    @Autowired
    private ParticipantRepository participantRepository;


    @Override
    public Optional<Inscripcion> verificarInscripcion(Integer usuarioId, Integer eventoId) {
        return inscriptionRepository.findByParticipanteIdAndEventoId(usuarioId, eventoId);
    }

    @Transactional
    @Override
    public void cancelarInscripcion(Integer eventoId, Integer participanteId) {
        Inscripcion inscripcion = inscriptionRepository.findByEventoIdAndParticipanteId(eventoId, participanteId);

        if (inscripcion != null) {
            inscriptionRepository.deleteByEventoAndParticipante(eventoId, participanteId);
        } else {
            throw new ResourceNotFoundException("No se encontró la inscripción para cancelar.");
        }
    }

    @Override
    public Inscripcion obtenerInscripcionPorEventoYParticipante(Integer eventoId, Integer participanteId) {
        return inscriptionRepository.findByEventoIdAndParticipanteId(eventoId, participanteId);
    }

    @Transactional
    @Override
    public List<Inscripcion> getAll() {
        return List.of();
    }

    @Transactional
    @Override
    public Page<Inscripcion> paginate(Pageable pageable) {
        return inscriptionRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Inscripcion create(Inscripcion inscripcion) {
        return inscriptionRepository.save(inscripcion);
    }

    @Transactional
    @Override
    public Inscripcion update(Integer id, Inscripcion inscripcion) {
        Inscripcion inscripcionFromDb = findById(id);
        inscripcionFromDb.setInscripcionStatus(inscripcion.getInscripcionStatus());
        inscripcionFromDb.setEvento(inscripcion.getEvento());
        inscripcionFromDb.setMonto(inscripcion.getMonto());
        inscripcionFromDb.setTipoPago(inscripcion.getTipoPago());
        inscripcionFromDb.setEvento(inscripcion.getEvento());
        return inscriptionRepository.save(inscripcionFromDb);
    }

    @Override
    public void delete(Integer id) {
        Inscripcion inscripcion = findById(id);
        inscriptionRepository.delete(inscripcion);
    }

    @Transactional
    @Override
    public Inscripcion findById(Integer id) {
        return inscriptionRepository.findById(id).orElseThrow(() -> new RuntimeException("Inscripcion no funciona"));
    }

    @Transactional
    @Override
    public List<Inscripcion> findByParticipanteId(Integer participanteId){
    List<Inscripcion> inscripcion = inscriptionRepository.findByParticipanteId(participanteId);
    return inscripcion;
    }
  
     public void crearInscripcion(InscripcionDTO inscripcionDTO) {
        Participante participante = participantRepository.findById(inscripcionDTO.getUsuario())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Inscripcion nuevaInscripcion = inscripcionMapper.toEntity(inscripcionDTO);
        nuevaInscripcion.setParticipante(participante);

        inscriptionRepository.save(nuevaInscripcion);
    }
}