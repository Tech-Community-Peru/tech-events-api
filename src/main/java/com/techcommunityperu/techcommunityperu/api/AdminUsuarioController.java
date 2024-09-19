package com.techcommunityperu.techcommunityperu.api;

import com.techcommunityperu.techcommunityperu.service.AdminUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class AdminUsuarioController {

    @Autowired
    private AdminUsuarioService adminUsuarioService;

    @GetMapping("/recuperar-contrasenia")
    public ResponseEntity<String> recuperarContrasenia(@RequestParam String correoElectronico) {
        String contrasenia = adminUsuarioService.recuperarContraseniaPorCorreo(correoElectronico);
        if (contrasenia != null) {
            return ResponseEntity.ok(contrasenia);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }
}