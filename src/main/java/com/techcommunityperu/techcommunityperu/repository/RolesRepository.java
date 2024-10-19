package com.techcommunityperu.techcommunityperu.repository;

import com.techcommunityperu.techcommunityperu.model.entity.Roles;
import com.techcommunityperu.techcommunityperu.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
    //Buscar roleENUM por su nombre
    Optional<Roles> findByNombre(Role roleENUM);
}