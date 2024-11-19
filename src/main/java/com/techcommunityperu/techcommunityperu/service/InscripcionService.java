package com.techcommunityperu.techcommunityperu.service;

import com.techcommunityperu.techcommunityperu.dto.InscripcionDTO;
import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;


public interface InscripcionService {
    void cancelarInscripcion(Integer eventoId, Integer participanteId);

    Optional<Inscripcion> verificarInscripcion(Integer usuarioId, Integer eventoId);

    //CRUD
    List<InscripcionDTO> getAll();

    Page<InscripcionDTO> paginate(Pageable pageable);

    //Creacion de un objeto Inscripcion
    InscripcionDTO create(InscripcionDTO inscripcionDTO);

    //Actualizacion de la informacion de Inscripcion, primero buscarlos por Id para luego reemplazarlos por el objeto Inscripcion
    InscripcionDTO update(Integer id, InscripcionDTO inscripcionDTO);

    //Eliminar
    void delete(Integer id);

    //Obtener por id
    InscripcionDTO findById(Integer id);


    //Listar eventos por id de participante
    List<Inscripcion> findByParticipanteId(Integer participanteId);

}
