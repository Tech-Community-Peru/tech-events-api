package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.dto.CommunityDTO;
import com.techcommunityperu.techcommunityperu.mapper.CommunityMapper;
import com.techcommunityperu.techcommunityperu.model.entity.Comunidad;
import com.techcommunityperu.techcommunityperu.repository.CommunityRepository;
import com.techcommunityperu.techcommunityperu.service.CommunityService;
import com.techcommunityperu.techcommunityperu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommunityServiceImpl implements CommunityService {
    private final CommunityRepository communityRepository;
    private final UserService userService;
    private final CommunityMapper communityMapper;

    @Override
    public CommunityDTO createCommunity(CommunityDTO communityDTO, Long userId) {
        Comunidad comunidad = communityMapper.toEntity(communityDTO);
        comunidad.setFechaCreacion(LocalDateTime.now());
        // Add the user to the community as a moderator (implementation of user addition needs to be handled)
        return communityMapper.toDto(communityRepository.save(comunidad));
    }

    @Override
    public CommunityDTO updateCommunity(CommunityDTO communityDTO, Long communityId) {
        Comunidad comunidad = communityRepository.findById(communityId)
                .orElseThrow(() -> new RuntimeException("Comunidad no encontrada"));
        comunidad.setNombre(communityDTO.getNombre());
        comunidad.setDescripcion(communityDTO.getDescripcion());
        return communityMapper.toDto(communityRepository.save(comunidad));
    }

    @Override
    public void deleteCommunity(Long communityId) {
        communityRepository.deleteById(communityId);
    }

    @Override
    public List<CommunityDTO> getAllCommunities() {
        return communityRepository.findAll().stream()
                .map(communityMapper::toDto)
                .collect(Collectors.toList());
    }

    // Nuevo método para obtener una comunidad por ID
    @Override
    public CommunityDTO getCommunityById(Long communityId) {
        Comunidad comunidad = communityRepository.findById(communityId)
                .orElseThrow(() -> new RuntimeException("Comunidad no encontrada"));
        return communityMapper.toDto(comunidad);
    }
}