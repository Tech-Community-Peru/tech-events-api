package com.techcommunityperu.techcommunityperu.repository;

import com.techcommunityperu.techcommunityperu.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario, Long> {
    boolean existsByCorreoElectronico(String correoElectronico);
    Usuario findByCorreoElectronico(String correoElectronico);
}