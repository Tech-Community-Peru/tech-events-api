package com.techcommunityperu.techcommunityperu.repository;

import com.techcommunityperu.techcommunityperu.model.entity.Ponente;
import com.techcommunityperu.techcommunityperu.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PonenteRepository extends JpaRepository<Ponente, Integer> {

    Optional<Ponente> findByNombreAndApellido(String nombre, String apellido);

    boolean existsByNombreAndApellido(String nombre, String apellido);

// Método para verificar si ya existe un autor con el mismo nombre y apellido, excepto el usuario actual
    //@Query("SELECT p FROM Ponente p WHERE p.nombre=:nombre AND p.apellido=?2 AND NOT p.usuarioId=?3")
    //@Query("SELECT p FROM Ponente p WHERE p.nombre=?1 AND p.apellido=?2 AND NOT p.usuarioId=?3")
    //boolean existsByNombreAndApellidoAndUsuarioIdNot(@Param("nombre") String nombre, String apellido, Usuario usuarioId);

}

