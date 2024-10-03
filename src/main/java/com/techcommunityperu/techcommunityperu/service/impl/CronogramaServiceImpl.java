package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.exceptions.ResourceNotFoundException;
import com.techcommunityperu.techcommunityperu.model.entity.Cronograma;
import com.techcommunityperu.techcommunityperu.repository.CronogramaRepository;
import com.techcommunityperu.techcommunityperu.service.CronogramaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CronogramaServiceImpl implements CronogramaService {

    private CronogramaRepository cronogramaRepository;

    // Crear Cronograma
    public Cronograma save(Cronograma cronograma) {
        return cronogramaRepository.save(cronograma);
    }

    // Leer Cronograma por ID
    public Cronograma findById(Integer id) {
        return cronogramaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cronograma no encontrado con id: " + id));
    }

    // Actualizar Cronograma
    public Cronograma update(Integer id, Cronograma cronogramaActualizado) {
        return cronogramaRepository.findById(id).map(cronograma -> {
            cronograma.setFechaInicio(cronogramaActualizado.getFechaInicio());
            cronograma.setFechaFin(cronogramaActualizado.getFechaFin());
            cronograma.setEvento(cronogramaActualizado.getEvento());
            return cronogramaRepository.save(cronograma);
        }).orElseThrow(() -> new ResourceNotFoundException("Cronograma no encontrado con id: " + id));
    }

    // Eliminar Cronograma
    public void delete(Integer id) {
        if (cronogramaRepository.existsById(id)) {
            cronogramaRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Cronograma no encontrado con id: " + id);
        }
    }

    // Listar todos los cronogramas (opcional, si quieres listar todos)
    public List<Cronograma> findAll() {
        return cronogramaRepository.findAll();
    }
}
