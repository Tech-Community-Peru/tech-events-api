package com.techcommunityperu.techcommunityperu.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "sorteo")
public class Sorteo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    @JoinColumn(name = "descripcion", nullable = false)
    private String descripcion;

    @JoinColumn(name = "fecha_sorteo", nullable = false)
    private LocalDate fechaSorteo;

}
