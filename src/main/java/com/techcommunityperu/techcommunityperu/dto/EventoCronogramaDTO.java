package com.techcommunityperu.techcommunityperu.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EventoCronogramaDTO {
    @NotNull(message = "El id del evento es obligatorio")
    private Integer id;
}
