package com.techcommunityperu.techcommunityperu.repository;

import com.techcommunityperu.techcommunityperu.model.entity.Ganador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GanadorRepository extends JpaRepository<Ganador, Integer> {
    // Puedes agregar métodos personalizados si es necesario
    void deleteByInscripcionId(Integer id);

}