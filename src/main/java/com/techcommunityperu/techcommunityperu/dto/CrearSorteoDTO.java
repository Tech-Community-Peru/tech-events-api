package com.techcommunityperu.techcommunityperu.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CrearSorteoDTO {
    private String descripcion;
    private LocalDate fechaSorteo;
    private Integer eventoId; // Aquí puedes enviar el ID del evento
}