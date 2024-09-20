package com.techcommunityperu.techcommunityperu.service.impl;
import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;
import com.techcommunityperu.techcommunityperu.repository.InscripcionRepository;
import com.techcommunityperu.techcommunityperu.service.InscripcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InscripcionServiceImpl implements InscripcionService {

    private final InscripcionRepository inscripcionRepository; // Asegúrate de que esté inyectado

    @Transactional
    @Override
    public void cancelarInscripcion(Integer eventoId, Integer usuarioId) {

        Inscripcion inscripcion = inscripcionRepository.findByEventoAndUsuario(eventoId, usuarioId);

        if (inscripcion != null) {
            // Eliminar la inscripción si existe
            inscripcionRepository.deleteByEventoAndUsuario(eventoId, usuarioId);
        } else {
            throw new RuntimeException("No se encontró la inscripción para cancelar.");
        }
    }

    // Método para obtener una inscripción específica basada en eventoId y usuarioId
    @Override
    public Inscripcion obtenerInscripcionPorEventoYUsuario(Integer eventoId, Integer usuarioId) {
        return inscripcionRepository.findByEventoAndUsuario(eventoId, usuarioId);
    }
}
