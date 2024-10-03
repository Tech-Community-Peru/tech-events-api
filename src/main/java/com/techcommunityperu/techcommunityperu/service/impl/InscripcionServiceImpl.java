package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.dto.InscripcionDTO;
import com.techcommunityperu.techcommunityperu.exception.ResourceNotFoundException;
import com.techcommunityperu.techcommunityperu.mapper.InscripcionMapper;
import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;
import com.techcommunityperu.techcommunityperu.model.entity.Usuario;
import com.techcommunityperu.techcommunityperu.repository.InscripcionRepository;
import com.techcommunityperu.techcommunityperu.repository.UsuarioRepository;
import com.techcommunityperu.techcommunityperu.service.InscripcionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InscripcionServiceImpl implements InscripcionService {

    @Autowired
    private final InscripcionRepository inscriptionRepository;

    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private final InscripcionMapper inscripcionMapper;

    @Override
    public Optional<Inscripcion> verificarInscripcion(Integer usuarioId, Integer eventoId) {
        return inscriptionRepository.findByUsuarioIdAndEventoId(usuarioId, eventoId);
    }

    @Transactional
    @Override
    public void cancelarInscripcion(Integer eventoId, Integer usuarioId) {
        Inscripcion inscripcion = inscriptionRepository.findByEventoAndUsuario(eventoId, usuarioId);

        if (inscripcion != null) {
            inscriptionRepository.deleteByEventoAndUsuario(eventoId, usuarioId);
        } else {
            throw new ResourceNotFoundException("No se encontró la inscripción para cancelar.");
        }
    }

    @Override
    public Inscripcion obtenerInscripcionPorEventoYUsuario(Integer eventoId, Integer usuarioId) {
        return inscriptionRepository.findByEventoAndUsuario(eventoId, usuarioId);
    }

    @Transactional
    @Override
    public void crearInscripcion(InscripcionDTO inscripcionDTO) {
        Usuario usuario = usuarioRepository.findById(inscripcionDTO.getUsuario())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Inscripcion nuevaInscripcion = inscripcionMapper.toEntity(inscripcionDTO);
        nuevaInscripcion.setUsuario(usuario);

        inscriptionRepository.save(nuevaInscripcion);
    }
}

