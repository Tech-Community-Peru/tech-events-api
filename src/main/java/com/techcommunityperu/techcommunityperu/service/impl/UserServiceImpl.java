package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.model.entity.Usuario;
import com.techcommunityperu.techcommunityperu.repository.UserRepository;
import com.techcommunityperu.techcommunityperu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor // Esta anotación genera el constructor automáticamente.
@Service
public class UserServiceImpl implements UserService {
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
        Optional<Usuario> usuario = userRepository.findByCorreoElectronico(correoElectronico);
        if (usuario.isPresent()) {
            // Comparar la contraseña directamente (sin encriptar)
            return usuario.get().getContrasenia().equals(contrasenia);
        }
        return false;
    }

    @Override
    public Optional<Usuario> findByCorreoElectronico(String correoElectronico) {
        return userRepository.findByCorreoElectronico(correoElectronico);
    }
}
