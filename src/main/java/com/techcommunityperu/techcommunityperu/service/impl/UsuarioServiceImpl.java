package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.dto.AuthResponseDTO;
import com.techcommunityperu.techcommunityperu.dto.LoginDTO;
import com.techcommunityperu.techcommunityperu.dto.UsuarioPerfilDTO;
import com.techcommunityperu.techcommunityperu.dto.UsuarioRegistroDTO;
import com.techcommunityperu.techcommunityperu.exceptions.BadRequestException;
import com.techcommunityperu.techcommunityperu.exceptions.ResourceNotFoundException;
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
import com.techcommunityperu.techcommunityperu.security.TokenProvider;
import com.techcommunityperu.techcommunityperu.security.UserPrincipal;
import com.techcommunityperu.techcommunityperu.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @Override
    public UsuarioPerfilDTO registroParticipante(UsuarioRegistroDTO registroDTO) {
        return registerUsuarioWithRole(registroDTO, Role.PARTICIPANTE);
    }

    @Override
    public UsuarioPerfilDTO registroPonente(UsuarioRegistroDTO registroDTO) {
        return registerUsuarioWithRole(registroDTO, Role.PONENTE);
    }

    @Transactional
    @Override
    public UsuarioPerfilDTO updateUsuarioPerfil(Integer id, UsuarioPerfilDTO usuarioPerfilDTO) {
        Usuario usuario = userRepository.findById(id).orElseThrow((()->  new RuntimeException("Usuario no encontrado")));

//        boolean existParticipante = participantRepository.existsByNombreAndApellidoAndUsuarioIdNot(usuarioPerfilDTO.getNombre(), usuarioPerfilDTO.getApellido(), id);
//        boolean existPonente = ponenteRepository.existsByNombreAndApellidoAndUsuarioIdNot(usuarioPerfilDTO.getNombre(), usuarioPerfilDTO.getApellido(), id);
//        System.out.println("Usuario exists: " + existParticipante);
//        if(existParticipante || existPonente) {
//            throw new BadRequestException("Ya existe un usuario con el mismo nombre y apellido");
//        }

        // Actualizar los campos específicos del perfil
        if(usuario.getParticipante() !=null){
            usuario.getParticipante().setNombre(usuarioPerfilDTO.getNombre());
            usuario.getParticipante().setApellido(usuarioPerfilDTO.getApellido());
            usuario.getParticipante().setPaisOrigen(usuarioPerfilDTO.getPaisOrigen());
        }

        if(usuario.getPonente() !=null){
            usuario.getPonente().setNombre(usuarioPerfilDTO.getNombre());
            usuario.getPonente().setApellido(usuarioPerfilDTO.getApellido());
            usuario.getPonente().setCargo(usuarioPerfilDTO.getCargo());
            usuario.getPonente().setEspecialidad(usuarioPerfilDTO.getEspecialidad());
        }

        Usuario updateUser = userRepository.save(usuario);

        return  usuarioMapper.toUsuarioPerfilEntity(updateUser);
    }

    @Override
    public UsuarioPerfilDTO getUsuarioPerfil(Integer id) {
        Usuario user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Usuario no encontrado"));
        return usuarioMapper.toUsuarioPerfilEntity(user);
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

    @Override
    public AuthResponseDTO login(LoginDTO loginDTO) {
        //Autenticacion de usuario
        Authentication authentication = authenticationManager
                .authenticate( new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword()));
//        Una vez autenticado, el objeto autenticacio contiene la informacion del usuario autenticado
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        Usuario usuario = principal.getUsuario();
        String token = tokenProvider.createAccessToken(authentication);
        AuthResponseDTO authResponseDTO = usuarioMapper.toAuthResponseDTO(usuario, token);
        return authResponseDTO;
    }
}
