package com.techcommunityperu.techcommunityperu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipanteActivoDTO {
    private Integer id;
    private String nombre;
    private String apellido;
}
