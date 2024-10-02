package com.techcommunityperu.techcommunityperu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommunityDTO {
    @NotBlank(message = "Ingrese un nombre")
    @NotNull(message = "Ingrese un nombre")
    private String nombre;
    @NotBlank(message = "Ingrese una descripcion")
    @NotNull(message = "Ingrese una descripcion")
    private String descripcion;
    @NotBlank(message = "Ingrese un tema principal")
    @NotNull(message = "Ingrese un tema principal")
    private String tematicaPrincipal;
}
