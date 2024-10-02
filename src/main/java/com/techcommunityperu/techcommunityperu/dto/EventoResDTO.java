package com.techcommunityperu.techcommunityperu.dto;

import com.techcommunityperu.techcommunityperu.model.enums.categoryEvent;
import com.techcommunityperu.techcommunityperu.model.enums.typeEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoResDTO {

    private Integer id;

    private String nombre;

    private Double costo;

    private String descripcion;

    private categoryEvent eventoCategoria;

    private typeEvent tipoEvento;

}
