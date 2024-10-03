package com.techcommunityperu.techcommunityperu.dto;

import lombok.Data;

@Data
public class GanadorDTO {
    private Integer id; // ID del ganador
    private Integer sorteoId; // ID del sorteo al que pertenece
    private Integer inscripcionId; // ID de la inscripción ganadora
    private String correoElectronico; // Correo del usuario que ganó

    public GanadorDTO(Integer id, String correoElectronico) {
    }
}