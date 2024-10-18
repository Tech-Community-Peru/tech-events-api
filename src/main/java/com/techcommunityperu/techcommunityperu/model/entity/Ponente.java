package com.techcommunityperu.techcommunityperu.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    // Relación OneToOne con Usuario
    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = true)
    private Usuario usuarioId;

    // Relación uno a muchos con Evento
    @JsonIgnore
    @OneToMany(mappedBy = "ponente", cascade = CascadeType.ALL)
    private List<Evento> evento;
}