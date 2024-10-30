package com.techcommunityperu.techcommunityperu.dto;

import java.time.LocalDateTime;

public class RegistroEscaneoDTO {
    private Integer id;
    private LocalDateTime fechaEscaneo;
    private Integer eventoId;
    private Integer participanteId;

    public RegistroEscaneoDTO(Integer id, LocalDateTime fechaEscaneo, Integer eventoId, Integer participanteId) {
        this.id = id;
        this.fechaEscaneo = fechaEscaneo;
        this.eventoId = eventoId;
        this.participanteId = participanteId;
    }

    // Getters y setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getFechaEscaneo() {
        return fechaEscaneo;
    }

    public void setFechaEscaneo(LocalDateTime fechaEscaneo) {
        this.fechaEscaneo = fechaEscaneo;
    }

    public Integer getEventoId() {
        return eventoId;
    }

    public void setEventoId(Integer eventoId) {
        this.eventoId = eventoId;
    }

    public Integer getParticipanteId() {
        return participanteId;
    }

    public void setParticipanteId(Integer participanteId) {
        this.participanteId = participanteId;
    }
}
