package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.model.entity.Usuario;
import com.techcommunityperu.techcommunityperu.repository.UserRepository;
import com.techcommunityperu.techcommunityperu.service.CustomerService;
import com.techcommunityperu.techcommunityperu.repository.ParticipantRepository;
import com.techcommunityperu.techcommunityperu.repository.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final UserRepository userRepository;
    private final ParticipantRepository participantRepository;
    private final RolesRepository rolesRepository;

//    @Transactional
//    @Override
//    public Usuario registrarUsuario(Usuario usuario) {
//        if (userRepository.existsByCorreoElectronico(usuario.getCorreoElectronico())) {
//            throw new RuntimeException("El correo electrónico ya existe");
//        }
//
//        // Obtener el rol con id 1 desde la base de datos
//        Roles rolUsuario = rolesRepository.findById(1)
//                .orElseThrow(() -> new RuntimeException("El rol con id 1 no existe"));
//
//        usuario.setRoles(rolUsuario);
//
//        Usuario newUser = userRepository.save(usuario);
//
//        // Crear un nuevo participante asociado al usuario
//        Participante participante = new Participante();
//        participante.setUsuarioId(newUser); // Asignar la relación con el usuario
//        participantRepository.save(participante); // Guardar el participante en la base de datos
//
//        return newUser;
//    }

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

    @Override
    public Optional<Usuario> findById(Integer userId) {
        return userRepository.findById(userId);
    }

}

