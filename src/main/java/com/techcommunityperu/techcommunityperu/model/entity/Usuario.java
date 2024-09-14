package com.techcommunityperu.techcommunityperu.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", nullable = true)
    private String nombre;

    @Column(name = "apellido", nullable = true)
    private String apellido;

    @Column(name = "correo_electronico", nullable = true)
    private String correo_electronico;

    @Column(name = "contrasenia",nullable = true)
    private String contrasenia;

    @Column(name = "habilidades", nullable = false)
    private String habilidades;

    @Column(name = "linkedin", nullable = false)
    private String linkedin;

    @Column(name = "informacion_adicional", nullable = false)
    private String informacionAdicional;

    @Column(name = "ubicacion", nullable = true)
    private String ubicacion;

    @Column(name = "pais_origen", nullable = true)
    private String paisOrigen;

    @Column(name = "edad", nullable = true)
    private Integer edad;


    // Relación uno a muchos con UsuarioEvento
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<usuarioEvento> usuarioEventos;

}
