package com.techcommunityperu.techcommunityperu.model.entity;

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

    @ManyToOne
    @JoinColumn(name = "creador_id", referencedColumnName = "id")
    private Usuario creador;

    @OneToMany(mappedBy = "comunidad", cascade = CascadeType.ALL)
    private List<eventoComunidad> eventosComunidad;

    //Relación uno a muchos con UsuarioComunidad
    @OneToMany(mappedBy = "comunidad", cascade = CascadeType.ALL)
    private List<usuarioComunidad> usuarioComunidad;
}
