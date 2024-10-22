package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.dto.UsuarioPerfilDTO;
import com.techcommunityperu.techcommunityperu.dto.UsuarioRegistroDTO;
import com.techcommunityperu.techcommunityperu.mapper.UsuarioMapper;
import com.techcommunityperu.techcommunityperu.model.entity.Participante;
import com.techcommunityperu.techcommunityperu.model.entity.Ponente;
import com.techcommunityperu.techcommunityperu.model.entity.Roles;
import com.techcommunityperu.techcommunityperu.model.entity.Usuario;
import com.techcommunityperu.techcommunityperu.model.enums.Role;
import com.techcommunityperu.techcommunityperu.repository.ParticipantRepository;
import com.techcommunityperu.techcommunityperu.repository.PonenteRepository;
import com.techcommunityperu.techcommunityperu.repository.RolesRepository;
import com.techcommunityperu.techcommunityperu.repository.UserRepository;
import com.techcommunityperu.techcommunityperu.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UserRepository userRepository;
    private final ParticipantRepository participantRepository;
    private final PonenteRepository ponenteRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;

    @Override
    public UsuarioPerfilDTO registroParticipante(UsuarioRegistroDTO registroDTO) {
        return registerUsuarioWithRole(registroDTO, Role.PARTICIPANTE);
    }

    @Override
    public UsuarioPerfilDTO registroPonente(UsuarioRegistroDTO registroDTO) {
        return registerUsuarioWithRole(registroDTO, Role.PONENTE);
    }

    @Override
    public UsuarioPerfilDTO updateUsuarioPerfil(Integer id, UsuarioPerfilDTO usuarioPerfilDTO) {
        return null;
    }

    @Override
    public UsuarioPerfilDTO getUsuarioPerfil(Integer id) {
        return null;
    }

    //Metodo privado para no usar los registroParticipante y registroPonente por separado
    //Registar usuario con sus roles
    private UsuarioPerfilDTO registerUsuarioWithRole(UsuarioRegistroDTO usuarioRegistroDTO, Role roleENUM) {
        //verificar si el email ya esta registrado o si ya si existe un usuario con el mismo nombre y apellido
        boolean existCorreo= userRepository.existsByCorreoElectronico(usuarioRegistroDTO.getCorreoElectronico());
        boolean existAsParticipante = participantRepository.existsByNombreAndApellido(usuarioRegistroDTO.getNombre(), usuarioRegistroDTO.getApellido()) ;
        boolean existAsPonente = ponenteRepository.existsByNombreAndApellido(usuarioRegistroDTO.getNombre(), usuarioRegistroDTO.getApellido());

        if(existCorreo){
            throw new IllegalArgumentException("El correo ya existe");
        }
        if(existAsParticipante||existAsPonente){
            throw new IllegalArgumentException("El usuario ya existe con el mismo nombre y apellido");
        }

        Roles role = rolesRepository.findByRol(roleENUM)
                .orElseThrow(()->new RuntimeException("Error: No tenemos ese tipo de rol"));


        usuarioRegistroDTO.setPassword(passwordEncoder.encode(usuarioRegistroDTO.getPassword()));
        Usuario user = usuarioMapper.toUsuarioEntity(usuarioRegistroDTO);
//        user.setRoles(role);

        if (roleENUM == Role.PARTICIPANTE){
            Participante participante = new Participante();
            participante.setNombre(usuarioRegistroDTO.getNombre());
            participante.setApellido(usuarioRegistroDTO.getApellido());
            participante.setPaisOrigen(usuarioRegistroDTO.getPaisOrigen());
            participante.setCreatedAt(LocalDateTime.now());
            participante.setUsuarioId(user);
            user.setParticipante(participante);
        } else if (roleENUM == Role.PONENTE) {
            Ponente ponente = new Ponente();
            ponente.setNombre(usuarioRegistroDTO.getNombre());
            ponente.setApellido(usuarioRegistroDTO.getApellido());
            ponente.setCargo(usuarioRegistroDTO.getCargo());
            ponente.setEspecialidad(usuarioRegistroDTO.getEspecialidad());
            ponente.setCreatedAt(LocalDateTime.now());
            ponente.setUsuarioId(user);
            user.setPonente(ponente);
        }
        user.setRoles(role);
        user.setContrasenia(usuarioRegistroDTO.getPassword());
        Usuario saveUsuario = userRepository.save(user);
        return usuarioMapper.toUsuarioPerfilEntity(saveUsuario);
    }
}
