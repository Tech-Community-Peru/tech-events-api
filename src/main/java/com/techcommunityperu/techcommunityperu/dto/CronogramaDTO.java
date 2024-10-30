package com.techcommunityperu.techcommunityperu.dto;

import com.techcommunityperu.techcommunityperu.model.entity.Evento;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CronogramaDTO {

    @NotNull(message = "El id deL cronograma es obligatorio")
    private Integer id;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDateTime fechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    private LocalDateTime fechaFin;

    @NotNull(message = "EL id del evento es obligatorio")
    private EventoCronogramaDTO evento;
}
