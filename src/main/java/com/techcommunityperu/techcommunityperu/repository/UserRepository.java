package com.techcommunityperu.techcommunityperu.repository;

import com.techcommunityperu.techcommunityperu.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Usuario, Integer> {


    Optional<Usuario> findByCorreoElectronico(String correoElectronico);


    @Query("SELECT COUNT(u) > 0 FROM Usuario u WHERE u.correoElectronico = :correoElectronico")
    boolean existsByCorreoElectronico(@Param("correoElectronico") String correoElectronico);

    @Query("SELECT u FROM Usuario u WHERE u.correoElectronico = :correoElectronico")
    Optional<Usuario>  findByEmailQuery(@Param("correoElectronico") String correoElectronico);

}

