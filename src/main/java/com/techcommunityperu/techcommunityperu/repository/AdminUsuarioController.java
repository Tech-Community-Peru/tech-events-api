package com.techcommunityperu.techcommunityperu.repository;

import com.techcommunityperu.techcommunityperu.model.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/usuario")
public class AdminUsuarioController {

    //private final AdminUsuarioService adminUserService;
//    @GetMapping
//    public ResponseEntity<List<Usuario>> list(){
//        //return ResponseEntity.ok(adminUserService.getAll());
//        List<Usuario> usuarios = new ArrayList<>();
//        Usuario usuario = new Usuario();
//        usuario.setNombre("admin");
//        usuarios.add(usuario);
//
//        Usuario usuario2 = new Usuario();
//        usuario2.setNombre("diego");
//        usuarios.add(usuario2);
//        return ResponseEntity.ok(usuarios);


    //}
}
