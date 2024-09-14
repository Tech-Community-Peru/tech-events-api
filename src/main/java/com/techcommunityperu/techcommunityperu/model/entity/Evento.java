package com.techcommunityperu.techcommunityperu.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table (name = "evento")
@Entity

public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "costo")
    private Double costo;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "calificacion")
    private Integer calificacion;

    @Column(name = "comentario", length = 50)
    private String comentario;

    // Relación con Ponente
    @ManyToOne
    @JoinColumn(name = "ponente_id", referencedColumnName = "id",foreignKey = @ForeignKey(name = "fk_evento_ponente"))
    private Ponente ponente;

    // Relación con Eventos Comunidad
    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    private List<eventoComunidad> eventosComunidad;

    // Relación con Eventos Categorias
    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    private List<eventoCategoria> categorias;

    // Relación uno a muchos con Comentario
    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    private List<Comentario> comentariol;

    // Relación uno a muchos con Calificacion
    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    private List<Calificacion> calificaciones;
}
