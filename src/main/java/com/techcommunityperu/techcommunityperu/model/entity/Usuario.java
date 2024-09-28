package com.techcommunityperu.techcommunityperu.model.entity;


import com.techcommunityperu.techcommunityperu.model.enums.Role;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = true)
    private String nombre;

    @Column(name = "apellido", nullable = true)
    private String apellido;

    @Column(name = "correo_electronico", nullable = false)
    private String correoElectronico;

    @Column(name = "contrasenia", nullable = true)
    private String contrasenia;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "rol")
    private Role role;
}