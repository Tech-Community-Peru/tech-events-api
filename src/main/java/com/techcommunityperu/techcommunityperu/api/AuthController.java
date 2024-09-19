package com.techcommunityperu.techcommunityperu.api;

import com.techcommunityperu.techcommunityperu.dto.UsuarioDTO;
import com.techcommunityperu.techcommunityperu.model.entity.Usuario;
import com.techcommunityperu.techcommunityperu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    // Endpoint para iniciar sesión
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String correo_electronico, @RequestParam String contrasenia) {
        boolean esValido = userService.validarCredenciales(correo_electronico, contrasenia);

        if (esValido) {
            return ResponseEntity.ok("Inicio de sesión exitoso. Redirigiendo a la página principal...");
        } else {
            return ResponseEntity.status(401).body("El correo electrónico y/o la contraseña son incorrectas.");
        }
    }

    // Endpoint para registrar un usuario
    @PostMapping("/register")
    public ResponseEntity<UsuarioDTO> register(@RequestBody Usuario usuario) {
        Usuario newUser = userService.registrarUsuario(usuario);
        UsuarioDTO usuarioDTO = new UsuarioDTO(newUser.getId(), newUser.getNombre());
        return new ResponseEntity<>(usuarioDTO, HttpStatus.CREATED);
    }
}