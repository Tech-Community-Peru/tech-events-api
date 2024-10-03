package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.model.entity.Participante;
import com.techcommunityperu.techcommunityperu.repository.ParticipantRepository;
import com.techcommunityperu.techcommunityperu.service.AccountParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.techcommunityperu.techcommunityperu.repository.UserRepository;

import java.util.Optional;

@Service
public class AccountParticipantServiceImpl implements AccountParticipantService {

    @Autowired
    private ParticipantRepository participanteRepository;

    @Autowired
    private UserRepository userRepository; // Asegúrate de inyectar UserRepository

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

        // Si existe, proceder a eliminar
        if (participanteOpt.isPresent()) {
            Participante participante = participanteOpt.get();

            // Guardar el ID del Usuario asociado
            Integer usuarioId = null;
            if (participante.getUsuarioId() != null) { // Asegúrate de que el usuario no sea nulo
                usuarioId = participante.getUsuarioId().getId(); // Guardar el ID del usuario
            }

            // Ahora eliminar el Participante
            participanteRepository.delete(participante); // Eliminar el Participante

            // Eliminar el Usuario asociado si existe
            if (usuarioId != null) {
                userRepository.deleteById(usuarioId); // Eliminar el Usuario usando el ID almacenado
            }
        }
    }
}