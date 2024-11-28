package com.techcommunityperu.techcommunityperu.model.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.techcommunityperu.techcommunityperu.model.enums.statusInscription;
import com.techcommunityperu.techcommunityperu.model.enums.paymentType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@ToString(exclude = {"participante", "evento", "ganadores"})
@Table(name = "inscripcion")
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pago")
    private paymentType tipoPago;

    @Column(name = "monto_pago")
    private double monto;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_inscripcion")
    private statusInscription inscripcionStatus;

    // Relación con Usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participante_id", referencedColumnName = "id")
    private Participante participante;

    // Relación con Evento
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_id", referencedColumnName = "id")
    @JsonBackReference
    private Evento evento;

    // Relación con Ganador, con eliminación en cascada
    @OneToMany(mappedBy = "inscripcion", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Ganador> ganadores;


}