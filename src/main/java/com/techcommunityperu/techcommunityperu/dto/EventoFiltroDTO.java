package com.techcommunityperu.techcommunityperu.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventoFiltroDTO {

    @NotNull(message = "La fecha de inicio no puede estar vacía.")
    private LocalDateTime fechaInicio;

    @NotNull(message = "La ubicación no puede estar vacía.")
    private Integer ubicacionId;
}