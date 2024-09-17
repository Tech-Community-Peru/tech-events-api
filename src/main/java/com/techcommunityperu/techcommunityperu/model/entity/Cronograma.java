package com.techcommunityperu.techcommunityperu.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "cronograma")
public class Cronograma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha_inicio", nullable = true)
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin", nullable = true)
    private LocalDateTime fechaFin;

    // Relación con Evento (Muchos a Uno)
    @ManyToOne
    @JoinColumn(name = "evento_id", referencedColumnName = "id")
    private Evento evento;
}
