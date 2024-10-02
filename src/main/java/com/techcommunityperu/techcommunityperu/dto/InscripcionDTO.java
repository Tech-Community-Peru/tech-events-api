package com.techcommunityperu.techcommunityperu.dto;

import com.techcommunityperu.techcommunityperu.model.entity.Usuario;
import com.techcommunityperu.techcommunityperu.model.enums.paymentType;
import com.techcommunityperu.techcommunityperu.model.enums.statusInscription;
import lombok.Data;

@Data
public class InscripcionDTO {
    private int usuario;
    private paymentType tipoPago;
    private double montoPago;
    private statusInscription status;
}