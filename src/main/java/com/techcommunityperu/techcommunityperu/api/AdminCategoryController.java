package com.techcommunityperu.techcommunityperu.api;

import com.techcommunityperu.techcommunityperu.service.AdminUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/recuperar")
public class AdminCategoryController {

    private final AdminUsuarioService adminUsuarioService;

    @GetMapping("/contrasenia")
    public ResponseEntity<String> recuperarContrasenia(@RequestParam String correoElectronico) {
        String contrasenia = adminUsuarioService.recuperarContraseniaPorCorreo(correoElectronico);
        if (contrasenia != null) {
            return ResponseEntity.ok(contrasenia);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }
}
