package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.model.entity.Usuario;
import com.techcommunityperu.techcommunityperu.repository.UserRepository;
import com.techcommunityperu.techcommunityperu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl  implements UserService {
    private final UserRepository userRepository;

    @Transactional
    @Override
    public Usuario registrarUsuario(Usuario usuario) {
        if (userRepository.existsByCorreoElectronico(usuario.getCorreoElectronico())) {
            throw new RuntimeException("El correo electrónico ya existe");
        }
        return userRepository.save(usuario);
    }

    @Override
    public boolean validarCredenciales(String correoElectronico, String contrasenia) {
        // Buscar usuario por correo
        Usuario usuario = userRepository.findByCorreoElectronico(correoElectronico);
        if (usuario != null) {
            // Comparar la contraseña directamente (sin encriptar)
            return usuario.getContrasenia().equals(contrasenia);
        }
        return false;
    }
}
