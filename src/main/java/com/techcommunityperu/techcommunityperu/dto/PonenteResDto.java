package com.techcommunityperu.techcommunityperu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PonenteResDto {
    private Integer id;
    private String nombre;
    private String apellido;
    private String cargo;
    private String paisOrigen;
    private String especialidad;
}
