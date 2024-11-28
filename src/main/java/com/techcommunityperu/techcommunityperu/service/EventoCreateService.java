package com.techcommunityperu.techcommunityperu.service;

import com.techcommunityperu.techcommunityperu.dto.EventoCreateDTO;

public interface EventoCreateService {

    EventoCreateDTO createEvent(EventoCreateDTO eventoCreateDTO);
    EventoCreateDTO updateEvent(EventoCreateDTO eventoCreateDTO, long id);

}
