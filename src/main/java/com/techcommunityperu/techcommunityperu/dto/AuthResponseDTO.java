package com.techcommunityperu.techcommunityperu.dto;

import com.techcommunityperu.techcommunityperu.model.entity.Roles;
import lombok.Data;

@Data
public class AuthResponseDTO {
    private String token;
    private String nombre;
    private String apellido;
    private String rol;
    private Integer idParticipante;
    private Integer idUsuario;
    private String correoElectronico;



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
