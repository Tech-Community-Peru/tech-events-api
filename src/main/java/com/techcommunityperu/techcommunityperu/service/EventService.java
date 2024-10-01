package com.techcommunityperu.techcommunityperu.service;

import com.techcommunityperu.techcommunityperu.dto.EventoResDTO;
import com.techcommunityperu.techcommunityperu.model.entity.Evento;
import com.techcommunityperu.techcommunityperu.model.enums.categoryEvent;

import java.util.List;

public interface EventService {

    List<Evento> obtenerEventosPorTipo(categoryEvent tipoEvento);
}
