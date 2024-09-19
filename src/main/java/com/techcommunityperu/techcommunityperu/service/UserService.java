package com.techcommunityperu.techcommunityperu.service;

import com.techcommunityperu.techcommunityperu.model.entity.Usuario;
import com.techcommunityperu.techcommunityperu.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean validarCredenciales(String correoElectronico, String contrasenia) {
        Optional<Usuario> usuarioOpt = userRepository.findByCorreoElectronico(correoElectronico);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            // Comparar la contraseña directamente (sin encriptar)
            return usuario.getContrasenia().equals(contrasenia);
        }
        return false;
    }
}
