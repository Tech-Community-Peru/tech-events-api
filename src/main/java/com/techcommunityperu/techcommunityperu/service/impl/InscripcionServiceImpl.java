package com.techcommunityperu.techcommunityperu.service.impl;
import com.techcommunityperu.techcommunityperu.api.AdminCategoryController;
import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;
import com.techcommunityperu.techcommunityperu.model.entity.Usuario;
import com.techcommunityperu.techcommunityperu.repository.InscriptionRepository;
import com.techcommunityperu.techcommunityperu.service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class InscripcionServiceImpl implements InscripcionService {
    //Para hacer la inyección de dependencias hay formas:
    //RequiredArgsConstructor : Genera un constructor
    //@Autowired: Crea constructor no es necesario poner private final

    @Autowired
    private InscriptionRepository inscriptionRepository;


    @Override
    public Optional<Inscripcion> verificarInscripcion(Integer usuarioId, Integer eventoId) {
        return inscriptionRepository.findByParticipanteIdAndEventoId(usuarioId, eventoId);
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

    @Transactional(readOnly = true)
    @Override
    public List<Inscripcion> getAll() {
        return List.of();
    }

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    @Override
    public Inscripcion findById(Integer id) {
        return inscriptionRepository.findById(id).orElseThrow(() -> new RuntimeException("Inscripcion no funciona"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Inscripcion> findByParticipanteId(Integer participanteId){
    List<Inscripcion> inscripcion = inscriptionRepository.findByParticipanteId(participanteId);
    return inscripcion;
    }
}