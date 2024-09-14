package com.techcommunityperu.techcommunityperu.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ubicacion")
public class Ubicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre_lugar")
    private String nombreLugar;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "ciudad")
    private String ciudad;
    @Column(name = "pais")
    private String pais;
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    // Relación con Evento
    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

}
