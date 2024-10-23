package com.techcommunityperu.techcommunityperu.security;

import com.techcommunityperu.techcommunityperu.model.entity.Usuario;
import com.techcommunityperu.techcommunityperu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;


@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    //Logica del  inicio de sesión
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario user = userRepository.findByCorreoElectronico(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el email: " + email));

//        GrantedAuthority es la representacion de los roles
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRoles().getRol().name());

        return new UserPrincipal(
                user.getId(),
                user.getCorreoElectronico(),
                user.getContrasenia(),
                Collections.singletonList(authority),
                user
        );

    }
}