package com.techcommunityperu.techcommunityperu.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@ToString(exclude = {"evento"})
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

    @Column(name = "pais_origen", length = 50)
    private String paisOrigen;

    @Column(name = "especialidad", nullable = false, length = 50)
    private String especialidad;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Relación OneToOne con Usuario
    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = true)
    private Usuario usuarioId;

    // Relación uno a muchos con Evento
    @JsonIgnore
    @OneToMany(mappedBy = "ponente", cascade = CascadeType.ALL)
    private List<Evento> evento;
}