package com.techcommunityperu.techcommunityperu.dto;

import com.techcommunityperu.techcommunityperu.model.entity.Evento;

import com.techcommunityperu.techcommunityperu.model.enums.paymentType;
import com.techcommunityperu.techcommunityperu.model.enums.statusInscription;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InscripcionDTO {
    @NotNull(message = "El id es obligatorio")
    private Integer id;

    @NotNull(message = "El tipo de pago es obligatorio")
    private paymentType tipoPago;

    @NotNull(message = "El monto es obligatorio")
    private double monto;

    @NotNull(message = "El estado del pago es obligatorio")
    private statusInscription status;

    @NotNull(message = "El participante es obligatorio")
    private ParticipanteDTO participante;

    @NotNull(message = "El evento es obligatorio")
    private EventoResDTO evento;
}


