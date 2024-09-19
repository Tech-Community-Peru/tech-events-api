package com.techcommunityperu.techcommunityperu.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "comentario")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha_publicacion", nullable = false)
    private LocalDateTime fechaPublicacion;

    @Column(name = "comentario", nullable = false)
    private String comentario;

    // Relación con Evento
    @ManyToOne
    @JoinColumn(name = "evento_id", referencedColumnName = "id")
    @JsonIgnore
    private Evento evento;

    // Relación con Usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @JsonManagedReference // Agregar esta anotación
    private Usuario usuario;
}
