package com.techcommunityperu.techcommunityperu.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SorteoDTO {
    private Integer id;
    private String descripcion;
    private LocalDate fechaSorteo;
    private Integer eventoId; // Si quieres mostrar el ID del evento asociado
}