package com.techcommunityperu.techcommunityperu.repository;

import com.techcommunityperu.techcommunityperu.model.entity.Ponente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PonenteRepository extends JpaRepository<Ponente, Integer> {

    Optional<Ponente> findByNombreAndApellido(String nombre, String apellido);

    boolean existsByNombreAndApellido(String nombre, String apellido);

// Método para verificar si ya existe un autor con el mismo nombre y apellido, excepto el usuario actual
// boolean existsByNombreAndApellidoAndUsuarioIdNot(String nombre, String apellido, Integer usuarioId);

}
