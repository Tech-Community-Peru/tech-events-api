package com.techcommunityperu.techcommunityperu.dto;

import lombok.Data;

@Data
public class QRDTO {
    private Integer id;
    private String eventoNombre;
    private String participanteNombre;
    private String participanteApellido;
    private double monto;
    private String estado;
}