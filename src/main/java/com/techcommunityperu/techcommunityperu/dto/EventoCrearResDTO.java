package com.techcommunityperu.techcommunityperu.dto;

import com.techcommunityperu.techcommunityperu.model.entity.Ponente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoCrearResDTO {

    private String nombre;
    private Double costo;
    private String descripcion;
    private String eventoCategoria; // Cambia a String para simplificar
    private String tipoEvento; // Cambia a String para simplificar

}
