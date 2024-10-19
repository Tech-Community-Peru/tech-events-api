package com.techcommunityperu.techcommunityperu.dto;

import com.techcommunityperu.techcommunityperu.model.entity.Roles;
import lombok.Data;

@Data
public class UsuarioPerfilDTO {
    //Obligatorio
    private Integer id;
    private String email;
    private Roles role;

    private String nombre;
    private String apellido;

    // Depende del tipo de usuario se intente registrar
    // Sea ponente
    private String cargo;

    //Sea participante
    private String paisOrigen;
}
