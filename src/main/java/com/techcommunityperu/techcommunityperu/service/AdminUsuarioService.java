package com.techcommunityperu.techcommunityperu.service;

import com.techcommunityperu.techcommunityperu.model.entity.Usuario;

public interface AdminUsuarioService {
    String recuperarContraseniaPorCorreo(String correoElectronico);
}