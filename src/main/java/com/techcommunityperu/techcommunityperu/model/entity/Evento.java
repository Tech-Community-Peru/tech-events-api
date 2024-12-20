package com.techcommunityperu.techcommunityperu.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import com.techcommunityperu.techcommunityperu.model.enums.categoryEvent;
import com.techcommunityperu.techcommunityperu.model.enums.typeEvent;
import lombok.ToString;

import java.util.List;

@Data
@Table (name = "evento")
@Entity
@ToString(exclude = {"ponente", "comunidad", "ubicacion", "cronograma"})
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

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria_evento")
    private categoryEvent eventoCategoria;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_evento")
    private typeEvent tipoEvento;

    // Relación con Ponente

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ponente_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_evento_ponente"))
    private Ponente ponente;

    // Relación con Comunidad
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comunidad_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_evento_comunidad"))
    @JsonBackReference
    private Comunidad comunidad;

    // Relación con Ubicacion
    @ManyToOne
    @JoinColumn(name = "ubicacion_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_evento_ubicacion"))
    private Ubicacion ubicacion;

    //Relación con Cronograma
    @JsonIgnore
    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cronograma> cronograma;

    // Relación con Asistencia
    @OneToMany(mappedBy = "evento", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference
    private List<Asistencia> asistencias;

    // Relación con Inscripcion
    @OneToMany(mappedBy = "evento", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference
    private List<Inscripcion> inscripciones;

    // Relación con Sorteo
    @OneToMany(mappedBy = "evento", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference
    private List<Sorteo> sorteos;
}
