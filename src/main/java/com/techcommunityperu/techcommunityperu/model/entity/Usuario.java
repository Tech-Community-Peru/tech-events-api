package com.techcommunityperu.techcommunityperu.model.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "correo_electronico", nullable = false)
    private String correoElectronico;

    @Column(name = "contrasenia", nullable = true)
    private String contrasenia;

    @ManyToOne
    @JoinColumn(name = "roles_id", referencedColumnName = "id")
    private Roles roles;

}