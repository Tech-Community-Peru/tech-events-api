package com.techcommunityperu.techcommunityperu.service;

import com.techcommunityperu.techcommunityperu.model.entity.Usuario;

public interface UserService {
    Usuario registrarUsuario(Usuario usuario);
    boolean validarCredenciales(String correoElectronico, String contrasenia);
}
