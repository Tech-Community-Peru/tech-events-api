package com.techcommunityperu.techcommunityperu.model.entity;


import com.techcommunityperu.techcommunityperu.model.enums.subscriptionStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "suscripcion")
public class Suscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tipo_suscripcion", length = 50)
    private String tipoSuscripcion;

    @Column(name = "fecha_inicio", nullable = true)
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin", nullable = true)
    private LocalDateTime fechaFin;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private subscriptionStatus estado;

    //RELACIONES
    @OneToOne
    @JoinColumn(name = "usuario_id",referencedColumnName = "id",foreignKey = @ForeignKey(name = "fk_suscripcion_usuario"))
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "pago_id",referencedColumnName = "id",foreignKey = @ForeignKey(name = "fk_suscripcion_pago"))
    private Pago pago;
}
