package com.techcommunityperu.techcommunityperu.model.entity;

import com.techcommunityperu.techcommunityperu.model.enums.subscriptionStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "acceso_premium")
@Data

public class accesoPremium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private subscriptionStatus status;

    // Relación uno a muchos con Evento
    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id",foreignKey = @ForeignKey(name = "fk_accesoPremium_usuario"))
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "suscripcion_id", referencedColumnName = "id",foreignKey = @ForeignKey(name = "fk_accesoPremium_suscripcion"))
    private Suscripcion suscripcion;

    @ManyToOne
    @JoinColumn(name = "evento_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_accesoPremium_evento"))
    private Evento evento;




}
