package com.techcommunityperu.techcommunityperu.api;


import com.techcommunityperu.techcommunityperu.dto.UsuarioPerfilDTO;
import com.techcommunityperu.techcommunityperu.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/profile")
@RequiredArgsConstructor
public class UserProfileController {
    private final UsuarioService usuarioService;

    //Actualizar perfil del usuario
    @PutMapping("{id}")
    public ResponseEntity<UsuarioPerfilDTO> updatePerfil(@PathVariable Integer id, @RequestBody UsuarioPerfilDTO usuarioPerfilDTO) {
        UsuarioPerfilDTO usuarioPerfil = usuarioService.updateUsuarioPerfil(id, usuarioPerfilDTO);
        return new ResponseEntity<>(usuarioPerfil, HttpStatus.OK);
    }
    //Obtener un perfil de usuario por id
    @GetMapping("{id}")
    public ResponseEntity<UsuarioPerfilDTO> getPerfil(@PathVariable Integer id) {
        UsuarioPerfilDTO usuarioPerfil = usuarioService.getUsuarioPerfil(id);
        return new ResponseEntity<>(usuarioPerfil, HttpStatus.OK);
    }

}