package com.techcommunityperu.techcommunityperu.dto;

import com.techcommunityperu.techcommunityperu.model.entity.Roles;
import lombok.Data;

@Data
public class UsuarioPerfilDTO {
    //Obligatorio
    private Integer id;
    private String correoElectronico;
    private Integer idParticipante;
    private Roles roles;

    private String nombre;
    private String apellido;

    // Depende del tipo de usuario se intente registrar
    // Sea ponente
    private String cargo;
    private String especialidad;
    //Sea participante
    private String habilidades;
    private String paisOrigen;
    private String ubicacion;
    private String informacionAdicional;
    private String linkedin;
    private Integer edad;
}
