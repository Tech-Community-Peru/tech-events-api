package com.techcommunityperu.techcommunityperu.dto;

import com.techcommunityperu.techcommunityperu.model.enums.paymentType;
import com.techcommunityperu.techcommunityperu.model.enums.statusInscription;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InscripcionDTO {

    @NotNull(message = "El tipo de pago es obligatorio")
    private paymentType tipoPago;

    @NotNull(message = "El monto es obligatorio")
    private Double montoPago;

    @NotNull(message = "El estado del pago es obligatorio")
    private statusInscription status;

    @NotNull(message = "El participante es obligatorio")
    private Integer participante;

    @NotNull(message = "El evento es obligatorio")
    private Integer evento;
}


