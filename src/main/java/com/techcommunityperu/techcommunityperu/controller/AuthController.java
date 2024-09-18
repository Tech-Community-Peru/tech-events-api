package com.techcommunityperu.techcommunityperu.controller;

import com.techcommunityperu.techcommunityperu.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String correo_electronico, @RequestParam String contrasenia) {
        boolean esValido = usuarioService.validarCredenciales(correo_electronico, contrasenia);

        if (esValido) {
            return ResponseEntity.ok("Inicio de sesión exitoso. Redirigiendo a la página principal...");
        } else {
            return ResponseEntity.status(401).body("El correo electrónico y/o la contraseña son incorrectas.");
        }
    }
}