package com.techcommunityperu.techcommunityperu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsistenciaDTO {

    private Long id;
    private String nombreParticipante;
    private boolean asistio;
    private LocalDate fechaAsistencia;

}
