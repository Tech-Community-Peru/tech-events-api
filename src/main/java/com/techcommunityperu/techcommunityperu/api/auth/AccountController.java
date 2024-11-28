package com.techcommunityperu.techcommunityperu.api.auth;

import com.techcommunityperu.techcommunityperu.dto.UsuarioPerfilDTO;
import com.techcommunityperu.techcommunityperu.model.entity.Participante;
import com.techcommunityperu.techcommunityperu.service.AccountParticipantService;
import com.techcommunityperu.techcommunityperu.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('PARTICIPANTE','PONENTE')")
public class AccountController {

    @Autowired
    private AccountParticipantService participanteService;

    @Autowired
    private final UsuarioService usuarioService;


//    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody Participante updatedParticipante) {
//        Participante updated = participanteService.updateParticipante(id, updatedParticipante);
//        if (updated != null) {
//            return ResponseEntity.ok("Tus datos han sido actualizados");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body("Tus datos no se han podido cambiar, inténtalo más tarde");
//        }
//    }
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody UsuarioPerfilDTO usuarioPerfilDTO) {
        UsuarioPerfilDTO updatedProfile = usuarioService.updateUsuarioPerfil(id, usuarioPerfilDTO);
        if (updatedProfile != null) {
            return ResponseEntity.ok("Tus datos han sido actualizados");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Tus datos no se han podido cambiar, inténtalo más tarde");
        }
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            participanteService.deleteParticipante(id);
            return ResponseEntity.ok("Tu usuario fue eliminado exitosamente");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se ha podido eliminar tu usuario, inténtelo más tarde");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("No se ha podido eliminar tu usuario, inténtelo más tarde");
        }
    }
}