package com.techcommunityperu.techcommunityperu.api;

import com.techcommunityperu.techcommunityperu.service.EmailService;
import com.techcommunityperu.techcommunityperu.service.impl.FavoritosServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@AllArgsConstructor
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private FavoritosServiceImpl favoritosService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/favoritos/{correoElectronico}")
    public ResponseEntity<String> sendEmail(@PathVariable("correoElectronico")String correoElectronico) {
        System.out.println(correoElectronico);
        return ResponseEntity.ok(favoritosService.favoritosEnviar(correoElectronico));
    }

}
