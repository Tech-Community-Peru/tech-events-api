package com.techcommunityperu.techcommunityperu.model.entity;

import com.techcommunityperu.techcommunityperu.model.enums.paymentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "pago")
@Data
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "monto", nullable = true, length = 50)
    private Double monto;
    @Column(name = "fecha_pago")
    private Date fechaPago;
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private paymentStatus estado;

    @OneToOne
    @JoinColumn(name ="usuario_id",referencedColumnName = "id",foreignKey = @ForeignKey(name = "fk_pago_usuario"))
    private Usuario usuario;
}
