package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.dto.InscripcionDTO;
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
    private InscripcionRepository inscriptionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository; // Asegúrate de tener esto inyectado

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

    @Transactional // Asegúrate de que el método sea transaccional
    @Override
    public void crearInscripcion(InscripcionDTO inscripcionDTO) {
        // Buscar al usuario por ID
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(inscripcionDTO.getUsuario());
        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        Usuario usuario = usuarioOpt.get();

        // Crear la inscripción y guardar en la base de datos
        Inscripcion nuevaInscripcion = new Inscripcion();
        nuevaInscripcion.setUsuario(usuario);
        nuevaInscripcion.setMonto(inscripcionDTO.getMontoPago());
        nuevaInscripcion.setTipoPago(inscripcionDTO.getTipoPago());
        nuevaInscripcion.setInscripcionStatus(inscripcionDTO.getStatus());

        inscriptionRepository.save(nuevaInscripcion);
    }
}
