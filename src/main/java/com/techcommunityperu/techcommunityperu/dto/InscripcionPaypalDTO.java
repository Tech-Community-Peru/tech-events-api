package com.techcommunityperu.techcommunityperu.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class InscripcionPaypalDTO {
    private Integer id;
    private double monto;

    public InscripcionPaypalDTO(Integer id, double monto) {
        this.id = id;
        this.monto = monto;
    }

}
