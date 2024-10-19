package com.techcommunityperu.techcommunityperu.mapper;

import com.techcommunityperu.techcommunityperu.dto.UsuarioDTO;
import com.techcommunityperu.techcommunityperu.dto.UsuarioPerfilDTO;
import com.techcommunityperu.techcommunityperu.dto.UsuarioRegistroDTO;
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
            usuarioPerfilDTO.setNombre(usuario.getParticipante().getNombre());
            usuarioPerfilDTO.setPaisOrigen(usuario.getParticipante().getPaisOrigen());
        }

        if(usuario.getPonente()!=null){
            usuarioPerfilDTO.setNombre(usuario.getPonente().getNombre());
            usuarioPerfilDTO.setApellido(usuario.getPonente().getApellido());
            usuarioPerfilDTO.setCargo(usuario.getPonente().getCargo());
        }
        return usuarioPerfilDTO;
    }

}
