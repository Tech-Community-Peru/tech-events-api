package com.techcommunityperu.techcommunityperu.dto;

import com.techcommunityperu.techcommunityperu.model.enums.categoryEvent;
import com.techcommunityperu.techcommunityperu.model.enums.typeEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Locale;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class EventoCreateDTO {

    private String nombre;
    private Double costo;
    private String descripcion;
    private categoryEvent eventoCategoria;
    private typeEvent tipoEvento;
    private String nombreUbicacion;
    private String ponente;
    private String comunidad;

}
