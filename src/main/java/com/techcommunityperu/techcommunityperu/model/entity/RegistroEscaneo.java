package com.techcommunityperu.techcommunityperu.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "registro_escaneo")
public class RegistroEscaneo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "participante_id", nullable = false)
    private Participante participante;

    @Column(name = "fecha_escaneo", nullable = false)
    private LocalDateTime fechaEscaneo;

    private LocalDateTime fechaGeneracion; // Fecha y hora de generación del QR
}

