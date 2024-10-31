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

    // Método para obtener el ID del usuario de la sesión simulada
    public Integer getUserIdFromSession(String correo_electronico) {
        return session.get(correo_electronico);
    }
}