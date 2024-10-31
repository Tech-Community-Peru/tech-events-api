package com.techcommunityperu.techcommunityperu.service;

import com.techcommunityperu.techcommunityperu.model.entity.Cronograma;
import com.techcommunityperu.techcommunityperu.repository.CronogramaRepository;

import java.util.List;

public interface CronogramaService {

     Cronograma save(Cronograma cronograma);

     Cronograma findById(Integer id);

    // Actualizar Cronograma
    Cronograma update(Integer id, Cronograma cronogramaActualizado);

    // Eliminar Cronograma
    void delete(Integer id);

    // Listar todos los cronogramas (opcional, si quieres listar todos)
    public List<Cronograma> findAll();


}
