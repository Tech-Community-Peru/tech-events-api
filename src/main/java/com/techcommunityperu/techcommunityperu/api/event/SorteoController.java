package com.techcommunityperu.techcommunityperu.api.event;

import com.techcommunityperu.techcommunityperu.dto.CrearSorteoDTO;
import com.techcommunityperu.techcommunityperu.service.SorteoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sorteos")
@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
public class SorteoController {

    @Autowired
    private SorteoService sorteoService;

    @PostMapping("/crear")
    public ResponseEntity<String> crearSorteo(@RequestBody CrearSorteoDTO crearSorteoDTO) {
        String mensaje = sorteoService.crearSorteo(crearSorteoDTO);
        return ResponseEntity.ok(mensaje);
    }
    @PostMapping("/realizar/{sorteoId}")
    public ResponseEntity<String> realizarSorteo(@PathVariable Integer sorteoId) {
        String mensaje = sorteoService.realizarSorteo(sorteoId);
        return ResponseEntity.ok(mensaje);
    }
}