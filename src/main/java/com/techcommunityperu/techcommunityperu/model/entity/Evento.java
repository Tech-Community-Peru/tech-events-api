
package com.techcommunityperu.techcommunityperu.model.entity;
import jakarta.persistence.*;
import lombok.Data;
import com.techcommunityperu.techcommunityperu.model.enums.categoryEvent;
import com.techcommunityperu.techcommunityperu.model.enums.typeEvent;
import java.util.List;

@Data
@Table (name = "evento")
@Entity

public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "costo")
    private Double costo;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria_evento")
    private categoryEvent eventoCategoria;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_evento")
    private typeEvent tipoEvento;

    // Relación con Ponente
    @ManyToOne
    @JoinColumn(name = "ponente_id", referencedColumnName = "id",foreignKey = @ForeignKey(name = "fk_evento_ponente"))
    private Ponente ponente;

    @ManyToOne
    @JoinColumn(name = "comunidad_id", referencedColumnName = "id",foreignKey= @ForeignKey(name = "fk_evento_comunidad"))
    private Comunidad comunidad;

}
