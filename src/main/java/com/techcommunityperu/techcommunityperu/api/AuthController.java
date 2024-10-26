package com.techcommunityperu.techcommunityperu.api;

import com.techcommunityperu.techcommunityperu.dto.*;
import com.techcommunityperu.techcommunityperu.dto.UsuarioPerfilDTO;
import com.techcommunityperu.techcommunityperu.dto.UsuarioRegistroDTO;
import com.techcommunityperu.techcommunityperu.model.entity.Usuario;
import com.techcommunityperu.techcommunityperu.service.CustomerService;
import com.techcommunityperu.techcommunityperu.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
@PreAuthorize("hasAnyRole('PONENTE', 'PARTICIPANTE', 'ADMINISTRADOR')")
public class AuthController {
    private final CustomerService customerService;
    private final UsuarioService usuarioService;

//    @PostMapping("/register")
//    public ResponseEntity<UsuarioDTO> register(@RequestBody Usuario usuario) {
//        Usuario newUser = customerService.registrarUsuario(usuario);
//        UsuarioDTO usuarioDTO = new UsuarioDTO(newUser.getId(), newUser.getCorreoElectronico());
//        return new ResponseEntity<>(usuarioDTO, HttpStatus.CREATED);
//        return null;
//    }

    @PostMapping("/register/participante")
    public ResponseEntity<UsuarioPerfilDTO> registerParticipante(@Valid @RequestBody UsuarioRegistroDTO usuarioRegistroDTO) {
        UsuarioPerfilDTO usuarioPerfil =  usuarioService.registroParticipante(usuarioRegistroDTO);
        return new ResponseEntity<>(usuarioPerfil, HttpStatus.CREATED);
    }

    @PostMapping("/register/ponente")
    public ResponseEntity<UsuarioPerfilDTO> registerPonente(@Valid @RequestBody UsuarioRegistroDTO usuarioRegistroDTO) {
        UsuarioPerfilDTO usuarioPerfil =  usuarioService.registroPonente(usuarioRegistroDTO);
        return new ResponseEntity<>(usuarioPerfil, HttpStatus.CREATED);
    }


    // Simulando una "sesión" en memoria
    private Map<String, Integer> session = new HashMap<>(); // Almacena el ID del usuario por correo electrónico

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        AuthResponseDTO authResponseDTO = usuarioService.login(loginDTO);
        return new ResponseEntity<>(authResponseDTO, HttpStatus.OK);
    }
//    public ResponseEntity<String> login(@RequestParam String correo_electronico, @RequestParam String contrasenia) {
//        // Busca el usuario por correo
//        Optional<Usuario> usuarioOptional = customerService.findByCorreoElectronico(correo_electronico);
//
//        if (usuarioOptional.isPresent() && usuarioOptional.get().getContrasenia().equals(contrasenia)) {
//            // Almacena el ID del usuario en el "mapa de sesión"
//            session.put(correo_electronico, usuarioOptional.get().getId());
//            return ResponseEntity.ok("Inicio de sesión exitoso. Redirigiendo a la página principal...");
//        } else {
//            return ResponseEntity.status(401).body("El correo electrónico y/o la contraseña son incorrectas.");
//        }
//    }

    // Método para obtener el ID del usuario de la sesión simulada
    public Integer getUserIdFromSession(String correo_electronico) {
        return session.get(correo_electronico);
    }
}