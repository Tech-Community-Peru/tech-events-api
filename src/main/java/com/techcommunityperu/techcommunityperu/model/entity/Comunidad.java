package com.techcommunityperu.techcommunityperu.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "comunidad")
public class Comunidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "cantidad_miembros")
    private Integer cantidadMiembros;

    @Column(name = "tematica_principal", nullable = false, length = 100)
    private String tematicaPrincipal;

    // Relación con Comentario
    @OneToMany(mappedBy = "comunidad", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference
    private List<Comentario> comentarios;

    // Relación con Evento
    @OneToMany(mappedBy = "comunidad", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference
    private List<Evento> eventos;

    // Relación con UsuarioComunidad
    @OneToMany(mappedBy = "comunidad", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference
    private List<usuarioComunidad> usuariosComunidad;

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDateTime.now();
    }
}
