package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.model.entity.RegistroEscaneo;
import com.techcommunityperu.techcommunityperu.repository.RegistroEscaneoRepository;
import com.techcommunityperu.techcommunityperu.service.EstadisticasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadisticasServiceImpl implements EstadisticasService {

    @Autowired
    private RegistroEscaneoRepository registroEscaneoRepository;

    @Override
    public long obtenerNumeroDeEscaneosPorEvento(Integer eventoId) {
        return registroEscaneoRepository.findByEventoId(eventoId).size();
    }

    @Override
    public long obtenerNumeroDeEscaneosPorParticipante(Integer participanteId) {
        return registroEscaneoRepository.findByParticipanteId(participanteId).size();
    }

    @Override
    public List<RegistroEscaneo> obtenerEscaneosPorEvento(Integer eventoId) {
        return registroEscaneoRepository.findByEventoId(eventoId);
    }
}
