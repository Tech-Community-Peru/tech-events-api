package com.techcommunityperu.techcommunityperu.service;

import com.techcommunityperu.techcommunityperu.dto.AuthResponseDTO;
import com.techcommunityperu.techcommunityperu.dto.LoginDTO;
import com.techcommunityperu.techcommunityperu.dto.UsuarioPerfilDTO;
import com.techcommunityperu.techcommunityperu.dto.UsuarioRegistroDTO;

public interface UsuarioService {

    // Register a Participante
    UsuarioPerfilDTO registroParticipante(UsuarioRegistroDTO registroDTO);

    // Register a Ponente
    UsuarioPerfilDTO registroPonente(UsuarioRegistroDTO registroDTO);

    // Autenticacion
    AuthResponseDTO login(LoginDTO loginDTO);

    // Actualizar perfil de usuario
    UsuarioPerfilDTO updateUsuarioPerfil(Integer id, UsuarioPerfilDTO usuarioPerfilDTO);

    // Get user profile by ID
    UsuarioPerfilDTO getUsuarioPerfil(Integer id);
}