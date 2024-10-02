package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;
import com.techcommunityperu.techcommunityperu.repository.InscriptionRepository;
import com.techcommunityperu.techcommunityperu.service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InscripcionServiceImpl implements InscripcionService {

    @Autowired
    private InscriptionRepository inscriptionRepository;

    @Override
    public Optional<Inscripcion> verificarInscripcion(Integer usuarioId, Integer eventoId) {
        return inscriptionRepository.findByUsuarioIdAndEventoId(usuarioId, eventoId);
    }


    @Transactional
    @Override
    public void cancelarInscripcion(Integer eventoId, Integer usuarioId) {
        // Verificar si existe la inscripción antes de intentar eliminarla
        Inscripcion inscripcion = inscriptionRepository.findByEventoAndUsuario(eventoId, usuarioId);

        if (inscripcion != null) {
            // Eliminar la inscripción si existe
            inscriptionRepository.deleteByEventoAndUsuario(eventoId, usuarioId);
        } else {
            throw new RuntimeException("No se encontró la inscripción para cancelar.");
        }
    }

    // Método para obtener una inscripción específica basada en eventoId y usuarioId
    @Override
    public Inscripcion obtenerInscripcionPorEventoYUsuario(Integer eventoId, Integer usuarioId) {
        return inscriptionRepository.findByEventoAndUsuario(eventoId, usuarioId);
    }

}