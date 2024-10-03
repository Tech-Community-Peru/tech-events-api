package com.techcommunityperu.techcommunityperu.service;

import com.techcommunityperu.techcommunityperu.model.entity.Participante;

public interface AccountParticipantService {
    Participante updateParticipante(Integer id, Participante updatedParticipante);
    void deleteParticipante(Integer id);
}