package com.techcommunityperu.techcommunityperu.model.entity;

import com.techcommunityperu.techcommunityperu.model.enums.categoryEvent;
import com.techcommunityperu.techcommunityperu.model.enums.typeEvent;
import jakarta.persistence.*;


@Entity
@Table(name = "ganador")
public class Ganador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Relación con Sorteo
    @ManyToOne
    @JoinColumn(name = "sorteo_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_ganador_sorteo"))
    private Sorteo sorteo;

    // Relación con Participante
    @ManyToOne
    @JoinColumn(name = "participante_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_ganador_participante"))
    private Participante participante;
}
