package com.techcommunityperu.techcommunityperu.mapper;

import com.techcommunityperu.techcommunityperu.dto.*;
import com.techcommunityperu.techcommunityperu.model.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UsuarioMapper {
    private final ModelMapper modelMapper;

    public Usuario toUsuarioEntity(UsuarioRegistroDTO usuarioRegistroDTO) {
        return modelMapper.map(usuarioRegistroDTO, Usuario.class);
    }

    public UsuarioPerfilDTO toUsuarioPerfilEntity(Usuario usuario) {
        UsuarioPerfilDTO usuarioPerfilDTO = modelMapper.map(usuario, UsuarioPerfilDTO.class);

        if(usuario.getParticipante()!=null){
            usuarioPerfilDTO.setApellido(usuario.getParticipante().getApellido());
            usuarioPerfilDTO.setIdParticipante(usuario.getParticipante().getId());
            usuarioPerfilDTO.setNombre(usuario.getParticipante().getNombre());
            usuarioPerfilDTO.setPaisOrigen(usuario.getParticipante().getPaisOrigen());
        }

        if(usuario.getPonente()!=null){
            usuarioPerfilDTO.setNombre(usuario.getPonente().getNombre());
            usuarioPerfilDTO.setApellido(usuario.getPonente().getApellido());
            usuarioPerfilDTO.setCargo(usuario.getPonente().getCargo());
            usuarioPerfilDTO.setEspecialidad(usuario.getPonente().getEspecialidad());
        }
        return usuarioPerfilDTO;
    }

    // Convertir de LoginDTO a User (cuando procesas el login)
    public Usuario toUserEntity(LoginDTO loginDTO) {
        return modelMapper.map(loginDTO, Usuario.class);
    }

    // Convertir de User a AuthResponseDTO para la respuesta de autenticación
    public AuthResponseDTO toAuthResponseDTO(Usuario usuario, String token) {
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setToken(token); // Asignar el token


        // Si es Participante, asignar los datos de Participante
        if (usuario.getParticipante() != null) {
            authResponseDTO.setNombre(usuario.getParticipante().getNombre());
            authResponseDTO.setApellido(usuario.getParticipante().getApellido());
            authResponseDTO.setIdParticipante(usuario.getParticipante().getId());
        }
        // Si es Ponente, asignar los datos de Ponente
        else if (usuario.getPonente() != null) {
            authResponseDTO.setNombre(usuario.getPonente().getNombre());
            authResponseDTO.setApellido(usuario.getPonente().getApellido());
        }
        // Para cualquier usuario que no sea cliente ni autor (ej. Admin)
        else {
            authResponseDTO.setNombre("Admin");
            authResponseDTO.setApellido("User");
        }

        // Asignar el rol del usuario
        authResponseDTO.setRol(usuario.getRoles().getNombre().toString());

        return authResponseDTO;
    }

}
