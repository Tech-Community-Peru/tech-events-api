package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.model.entity.Usuario;
import com.techcommunityperu.techcommunityperu.repository.UserRepository;
import com.techcommunityperu.techcommunityperu.service.AdminUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AdminUsuarioServiceImpl implements AdminUsuarioService {
    @Autowired
    private UserRepository usuarioRepository;
    @Override
    public String recuperarContraseniaPorCorreo(String correoElectronico) {
        Optional<Usuario> usuario = usuarioRepository.findByCorreoElectronico(correoElectronico);
        return usuario.map(Usuario::getContrasenia).orElse(null);
    }
}
