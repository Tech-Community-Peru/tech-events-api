package com.techcommunityperu.techcommunityperu.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CronogramaDTO {

    @NotNull(message = "El id deL cronograma es obligatorio")
    private Integer id;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDateTime fechaInicio;

}
