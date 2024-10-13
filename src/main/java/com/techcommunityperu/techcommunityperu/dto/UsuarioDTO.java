package com.techcommunityperu.techcommunityperu.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    @NotNull(message = "El id de pago es obligatorio")
    private Integer id;
    @NotNull(message = "El correoElectronico  es obligatorio")
    private String correoElectronico;
}