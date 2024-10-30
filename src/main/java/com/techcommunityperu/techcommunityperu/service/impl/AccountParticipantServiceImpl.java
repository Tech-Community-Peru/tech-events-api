package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.model.entity.*;
import com.techcommunityperu.techcommunityperu.repository.*;
import com.techcommunityperu.techcommunityperu.service.AccountParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountParticipantServiceImpl implements AccountParticipantService {

    @Autowired
    private ParticipantRepository participanteRepository;

    @Autowired
    private AsistenciaRepository asistenciaRepository;
    @Autowired
    private InscriptionRepository inscriptionRepository;

    @Autowired
    private UserRepository userRepository; // Asegúrate de inyectar UserRepository
    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private UserCommunityRepository userCommunityRepository ;

    @Override
    public Participante updateParticipante(Integer id, Participante updatedParticipante) {
        Optional<Participante> existingParticipanteOpt = participanteRepository.findById(id);

        if (existingParticipanteOpt.isPresent()) {
            Participante existingParticipante = existingParticipanteOpt.get();

            // Actualiza los campos
            existingParticipante.setNombre(updatedParticipante.getNombre());
            existingParticipante.setApellido(updatedParticipante.getApellido());
            existingParticipante.setHabilidades(updatedParticipante.getHabilidades());
            existingParticipante.setLinkedin(updatedParticipante.getLinkedin());
            existingParticipante.setInformacionAdicional(updatedParticipante.getInformacionAdicional());
            existingParticipante.setUbicacion(updatedParticipante.getUbicacion());
            existingParticipante.setPaisOrigen(updatedParticipante.getPaisOrigen());
            existingParticipante.setEdad(updatedParticipante.getEdad());

            return participanteRepository.save(existingParticipante);
        } else {
            return null;
        }
    }

    @Override
    public void deleteParticipante(Integer id) {
        // Verifica si el Participante existe
        Optional<Participante> participanteOpt = participanteRepository.findById(id);

        if (participanteOpt.isPresent()) {
            Participante participante = participanteOpt.get();

            Integer usuarioId = null;
            if (participante.getUsuarioId() != null) {
                usuarioId = participante.getUsuarioId().getId();
            }

            // Elimina todas las inscripciones relacionadas con el participante
            List<Inscripcion> inscripciones = inscriptionRepository.findByParticipanteId(participante.getId());
            if (inscripciones != null && !inscripciones.isEmpty()) {
                inscriptionRepository.deleteAll(inscripciones);
            }

            // Elimina todas las asistencias relacionadas con el participante
            List<Asistencia> asistencias = asistenciaRepository.findByParticipante(participante);
            if (asistencias != null && !asistencias.isEmpty()) {
                asistenciaRepository.deleteAll(asistencias);
            }

            List<Comentario> comentarios = comentarioRepository.findByUsuarioId(usuarioId);
            if (comentarios != null && !comentarios.isEmpty()) {
                comentarioRepository.deleteAll(comentarios);
            }

            List<usuarioComunidad> usuarioComunidad = userCommunityRepository.findByUsuarioId(usuarioId);
            if (usuarioComunidad != null && !usuarioComunidad.isEmpty()) {
                userCommunityRepository.deleteAll(usuarioComunidad);
            }

            // Ahora elimina el Participante
            participanteRepository.delete(participante);

            // Elimina el Usuario asociado si existe
            if (usuarioId != null) {
                userRepository.deleteById(usuarioId);
            }
        }
    }
}