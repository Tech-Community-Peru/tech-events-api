package com.techcommunityperu.techcommunityperu.dto;

import com.techcommunityperu.techcommunityperu.model.enums.categoryEvent;
import com.techcommunityperu.techcommunityperu.model.enums.typeEvent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoCrearReqDTO {
    @NotNull(message = "El nombre no puede ser nulo")
    private String nombre;

    @NotNull(message = "El costo es obligatorio")
    private Double costo;

    @NotNull(message ="El costo es obligatorio")
    private String descripcion;

    @NotNull(message = " El evento categoria es obligatorio")
    private categoryEvent eventoCategoria;

    @NotNull(message = "El tipo de evento es obligatorio")
    private typeEvent tipoEvento;
}
