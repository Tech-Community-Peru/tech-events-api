package com.techcommunityperu.techcommunityperu.repository;

import com.techcommunityperu.techcommunityperu.model.entity.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participante, Integer> {
    // Aquí puedes agregar métodos adicionales de búsqueda si es necesario
    Participante findByNombre(String nombre);
    Participante findByApellido(String apellido);
    boolean existsByNombreAndApellido(String nombre, String apellido);
    // Método para verificar si ya existe un participante con el mismo nombre y apellido, excepto el USUario actual
    //boolean existsByNombreAndApellidoAndUsuarioIdNot(String nombre, String apellido, Integer usuarioId);
}