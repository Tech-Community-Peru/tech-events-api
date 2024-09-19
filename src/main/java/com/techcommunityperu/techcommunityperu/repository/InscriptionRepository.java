package com.techcommunityperu.techcommunityperu.repository;
import com.techcommunityperu.techcommunityperu.model.entity.Evento;
import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;
import com.techcommunityperu.techcommunityperu.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscripcion, Integer> {
    List<Inscripcion> findByUsuario(Usuario usuario);
    List<Inscripcion> findByEvento(Evento evento);
}
