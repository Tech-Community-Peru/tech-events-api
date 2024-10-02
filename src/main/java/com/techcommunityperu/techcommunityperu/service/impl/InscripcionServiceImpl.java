package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.dto.InscripcionDTO;
import com.techcommunityperu.techcommunityperu.exception.InscripcionNotFoundException;
import com.techcommunityperu.techcommunityperu.mapper.InscripcionMapper;
import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;
import com.techcommunityperu.techcommunityperu.model.entity.Usuario;
import com.techcommunityperu.techcommunityperu.repository.InscripcionRepository;
import com.techcommunityperu.techcommunityperu.repository.UsuarioRepository;
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
        Inscripcion inscripcion = inscriptionRepository.findByEventoAndUsuario(eventoId, usuarioId)
                .orElseThrow(() -> new InscripcionNotFoundException("No se encontró inscripción para cancelar."));

        inscriptionRepository.deleteByEventoAndUsuario(eventoId, usuarioId);
    }

    @Override
    public Inscripcion obtenerInscripcionPorEventoYUsuario(Integer eventoId, Integer usuarioId) {
        return inscriptionRepository.findByEventoAndUsuario(eventoId, usuarioId)
                .orElseThrow(() -> new InscripcionNotFoundException("No se encontró inscripción para el evento y usuario especificado."));
    }


    @Transactional
    @Override
    public void crearInscripcion(InscripcionDTO inscripcionDTO) {
        // Validar si el usuario existe
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(inscripcionDTO.getUsuario());
        if (usuarioOpt.isEmpty()) {
            throw new InscripcionNotFoundException("Usuario no encontrado");
        }

        // Crear la entidad de Inscripción y mapear los campos desde el DTO
        Inscripcion nuevaInscripcion = new Inscripcion();
        nuevaInscripcion.setUsuario(usuarioOpt.get());
        nuevaInscripcion.setMonto(inscripcionDTO.getMontoPago());
        nuevaInscripcion.setTipoPago(inscripcionDTO.getTipoPago());
        nuevaInscripcion.setInscripcionStatus(inscripcionDTO.getStatus());

        inscriptionRepository.save(nuevaInscripcion);
    }
}
