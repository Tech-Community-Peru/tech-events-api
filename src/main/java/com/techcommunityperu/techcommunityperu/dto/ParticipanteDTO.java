package com.techcommunityperu.techcommunityperu.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ParticipanteDTO {
    @NotNull(message = "El id es obligatorio")
    private Integer id;

    @NotNull(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull(message = "El apellido es obligatorio")
    private String apellido;

    private Integer edad;

    private UsuarioDTO usuarioId;
}
