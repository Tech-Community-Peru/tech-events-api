package com.techcommunityperu.techcommunityperu.model.entity;

import com.techcommunityperu.techcommunityperu.model.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "usuario_comunidad")
public class usuarioComunidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "comunidad_id", nullable = false)
    private Comunidad comunidad;

    @Column(name = "fecha_unido", nullable = false)
    private LocalDateTime fechaUnido;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol")
    private Role role;
}