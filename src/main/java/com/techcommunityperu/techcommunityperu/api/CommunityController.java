package com.techcommunityperu.techcommunityperu.api;

import com.techcommunityperu.techcommunityperu.dto.CommunityDTO;
import com.techcommunityperu.techcommunityperu.service.CommunityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/communities")
public class CommunityController {
    private final CommunityService communityService;

    @PostMapping
    public ResponseEntity<?> createCommunity(@Valid @RequestBody CommunityDTO communityDTO, BindingResult result) {
        // Verifica si hay errores en la validación
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errors);
        }
        // Aquí la lógica para guardar la comunidad
        return ResponseEntity.ok("Comunidad creada correctamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommunityDTO> updateCommunity(@PathVariable Long id, @RequestBody CommunityDTO communityDTO) {
        CommunityDTO updatedCommunity = communityService.updateCommunity(communityDTO, id);
        return new ResponseEntity<>(updatedCommunity, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommunity(@PathVariable Long id) {
        communityService.deleteCommunity(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CommunityDTO>> getAllCommunities() {
        List<CommunityDTO> communities = communityService.getAllCommunities();
        return ResponseEntity.ok(communities);
    }
}


