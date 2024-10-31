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

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "fecha_sorteo", nullable = false)
    private LocalDate fechaSorteo;

    @ManyToOne
    @JoinColumn(name = "evento_id",referencedColumnName = "id", foreignKey = @ForeignKey(name="FK_sorteo_evento"))
    private Evento evento;
}