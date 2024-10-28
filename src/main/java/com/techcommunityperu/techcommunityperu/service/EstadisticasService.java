package com.techcommunityperu.techcommunityperu.service;

import com.techcommunityperu.techcommunityperu.model.entity.RegistroEscaneo;

import java.util.List;

public interface EstadisticasService {
    long obtenerNumeroDeEscaneosPorEvento(Integer eventoId);
    long obtenerNumeroDeEscaneosPorParticipante(Integer participanteId);
    List<RegistroEscaneo> obtenerEscaneosPorEvento(Integer eventoId);
}