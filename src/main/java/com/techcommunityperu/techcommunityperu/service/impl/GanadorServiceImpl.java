package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.model.entity.Ganador;
import com.techcommunityperu.techcommunityperu.repository.GanadorRepository;
import com.techcommunityperu.techcommunityperu.service.GanadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GanadorServiceImpl implements GanadorService {

    @Autowired
    private GanadorRepository ganadorRepository;

    @Override
    public void registrarGanador(Ganador ganador) {
        ganadorRepository.save(ganador);
    }
}