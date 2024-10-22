package com.techcommunityperu.techcommunityperu.model.entity;


import com.techcommunityperu.techcommunityperu.model.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "participante")
public class Participante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = true)
    private String nombre;

    @Column(name = "apellido", nullable = true)
    private String apellido;

    @Column(name = "habilidades",nullable = true)
    private String habilidades;

    @Column(name = "linkedin",nullable = true)
    private String linkedin;

    @Column(name = "informacion_adicional",nullable = true)
    private String informacionAdicional;

    @Column(name = "ubicacion",nullable = true)
    private String ubicacion;

    @Column(name = "pais_origen",nullable = true)
    private String paisOrigen;

    @Column(name = "edad", nullable = true)
    private Integer edad;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    //Relaciones
    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = true)
    private Usuario usuarioId;
}
