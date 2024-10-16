package com.techcommunityperu.techcommunityperu.repository;

import com.techcommunityperu.techcommunityperu.model.entity.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participante, Integer> {
    // Aquí puedes agregar métodos adicionales de búsqueda si es necesario
    Participante findByNombre(String nombre);
    Participante findByApellido(String apellido);
    Participante findByNombreAndApellido(String nombre, String apellido);
}