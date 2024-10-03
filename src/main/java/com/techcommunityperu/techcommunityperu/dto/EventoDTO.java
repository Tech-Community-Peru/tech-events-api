package com.techcommunityperu.techcommunityperu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class EventoDTO {
    private Integer id;
    private String nombre;
    private Double costo;
    private String descripcion;
    private String eventoCategoria; // Cambia a String para simplificar
    private String tipoEvento; // Cambia a String para simplificar
    private String nombreUbicacion; // Nombre de la ubicación
}
