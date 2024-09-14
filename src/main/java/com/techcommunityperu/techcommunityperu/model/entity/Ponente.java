package com.techcommunityperu.techcommunityperu.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "ponente")
public class Ponente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @Column(name = "cargo", nullable = false, length = 50)
    private String cargo;

    @Column(name = "pais_origen", nullable = false, length = 50)
    private String paisOrigen;

    @Column(name = "especialidad", nullable = false, length = 50)
    private String especialidad;

    // Relación uno a muchos con Evento
    @OneToMany(mappedBy = "ponente", cascade = CascadeType.ALL)
    private List<Evento> evento;
}