package com.techcommunityperu.techcommunityperu.service;

import com.techcommunityperu.techcommunityperu.model.entity.Usuario;

import java.util.Optional;

public interface UserService {
    Usuario registrarUsuario(Usuario usuario);
    boolean validarCredenciales(String correoElectronico, String contrasenia);
    Optional<Usuario> findByCorreoElectronico(String correoElectronico);
    Optional<Usuario> findById(Integer userId);

}