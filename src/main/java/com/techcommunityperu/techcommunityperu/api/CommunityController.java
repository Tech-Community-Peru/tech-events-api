package com.techcommunityperu.techcommunityperu.api;

import com.techcommunityperu.techcommunityperu.dto.CommunityDTO;
import com.techcommunityperu.techcommunityperu.service.CommunityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/communities")
@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
public class CommunityController {
    private final CommunityService communityService;

    @PostMapping
    public ResponseEntity<?> createCommunity(@Valid @RequestBody CommunityDTO communityDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errors);
        }
        CommunityDTO createdCommunity = communityService.createCommunity(communityDTO, 1L); // Reemplaza 1L con el userId adecuado
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCommunity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommunityDTO> updateCommunity(@PathVariable Long id, @Valid @RequestBody CommunityDTO communityDTO) {
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getCommunityById(@PathVariable Long id) {
        CommunityDTO communityDTO = communityService.getCommunityById(id);
        return ResponseEntity.ok(communityDTO);
    }
}



