package com.techcommunityperu.techcommunityperu.service;

import com.techcommunityperu.techcommunityperu.dto.InscripcionDTO;
import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import java.util.List;


public interface InscripcionService {
    void cancelarInscripcion(Integer eventoId, Integer participanteId);
    Inscripcion obtenerInscripcionPorEventoYParticipante(Integer eventoId, Integer participanteId);
    Optional<Inscripcion> verificarInscripcion(Integer usuarioId, Integer eventoId);

    //CRUD
    List<Inscripcion> getAll();
    Page<Inscripcion> paginate(Pageable pageable);
    //Creacion de un objeto Inscripcion
    Inscripcion create(Inscripcion inscripcion);
    //Actualizacion de la informacion de Inscripcion, primero buscarlos por Id para luego reemplazarlos por el objeto Inscripcion
    Inscripcion update(Integer id, Inscripcion inscripcion);
    //Eliminar
    void delete(Integer id);
    //Obtener por id
    Inscripcion findById(Integer id);
    //Listar eventos por id de participante
    List<Inscripcion> findByParticipanteId(Integer participanteId);
    void crearInscripcion(InscripcionDTO inscripcionDTO);
}
