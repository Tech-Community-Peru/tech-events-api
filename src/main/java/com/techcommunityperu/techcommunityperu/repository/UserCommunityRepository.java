package com.techcommunityperu.techcommunityperu.repository;

import com.techcommunityperu.techcommunityperu.model.entity.usuarioComunidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCommunityRepository extends JpaRepository<usuarioComunidad, Integer> {
    List<usuarioComunidad> findByUsuarioId(Integer usuarioId);
}
