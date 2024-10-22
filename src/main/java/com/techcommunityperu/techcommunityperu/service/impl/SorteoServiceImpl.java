package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.dto.CrearSorteoDTO;
import com.techcommunityperu.techcommunityperu.dto.GanadorDTO;
import com.techcommunityperu.techcommunityperu.exceptions.FechaPasadaException;
import com.techcommunityperu.techcommunityperu.exceptions.InscripcionesInsuficientesException;
import com.techcommunityperu.techcommunityperu.exceptions.SorteoExistenteException;
import com.techcommunityperu.techcommunityperu.model.entity.Evento;
import com.techcommunityperu.techcommunityperu.model.entity.Ganador;
import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;
import com.techcommunityperu.techcommunityperu.model.entity.Sorteo;
import com.techcommunityperu.techcommunityperu.repository.GanadorRepository;
import com.techcommunityperu.techcommunityperu.repository.SorteoRepository;
import com.techcommunityperu.techcommunityperu.repository.EventRepository;
import com.techcommunityperu.techcommunityperu.repository.InscriptionRepository;
import com.techcommunityperu.techcommunityperu.service.SorteoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
public class SorteoServiceImpl implements SorteoService {

    @Autowired
    private SorteoRepository sorteoRepository;

    @Autowired
    private GanadorRepository ganadorRepository;

    @Autowired
    private InscriptionRepository inscriptionRepository;

    @Autowired
    private EventRepository eventoRepository;

    @Override
    public String crearSorteo(CrearSorteoDTO crearSorteoDTO) {
        // Validar el objeto CrearSorteoDTO
        if (crearSorteoDTO.getDescripcion() == null || crearSorteoDTO.getDescripcion().isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede ser nula o vacía.");
        }
        if (crearSorteoDTO.getFechaSorteo() == null) {
            throw new IllegalArgumentException("La fecha del sorteo no puede ser nula.");
        }
        if (crearSorteoDTO.getEventoId() == null) {
            throw new IllegalArgumentException("El ID del evento no puede ser nulo.");
        }

        if (crearSorteoDTO.getFechaSorteo().isBefore(LocalDate.now())) {
            throw new FechaPasadaException("El sorteo no se puede realizar porque la fecha del sorteo es anterior a la fecha actual.");
        }

        // Verificar si ya existe un sorteo para el evento
        boolean existeSorteo = sorteoRepository.existsByEventoId(crearSorteoDTO.getEventoId());
        if (existeSorteo) {
            throw new SorteoExistenteException("Ya existe un sorteo para el evento con ID: " + crearSorteoDTO.getEventoId());
        }

        // Crear y guardar el sorteo
        Sorteo sorteo = new Sorteo();
        sorteo.setDescripcion(crearSorteoDTO.getDescripcion());
        sorteo.setFechaSorteo(crearSorteoDTO.getFechaSorteo());

        // Obtener el evento
        Evento evento = eventoRepository.findById(crearSorteoDTO.getEventoId())
                .orElseThrow(() -> new NoSuchElementException("Evento no encontrado"));
        sorteo.setEvento(evento);

        // Guardar el sorteo
        sorteoRepository.save(sorteo);

        // Devolver un mensaje con el nombre del evento
        return "El sorteo del evento " + evento.getNombre() + " fue creado.";
    }

    public String realizarSorteo(Integer sorteoId) {
        // Buscar el sorteo por ID
        Sorteo sorteo = sorteoRepository.findById(sorteoId)
                .orElseThrow(() -> new NoSuchElementException("Sorteo no encontrado"));

        // Obtener las inscripciones del evento asociado al sorteo
        List<Inscripcion> inscripciones = obtenerInscripciones(sorteo.getEvento());

        // Verificar si hay suficientes inscripciones para realizar el sorteo
        if (inscripciones.size() <= 1) {
            throw new InscripcionesInsuficientesException("El sorteo no se puede realizar, porque no hay una buena cantidad de participantes.");
        }

        // Seleccionar al ganador de las inscripciones
        Inscripcion inscripcionGanadora = seleccionarGanador(inscripciones);

        // Crear un nuevo objeto Ganador y asociarlo con el sorteo y la inscripción ganadora
        Ganador nuevoGanador = new Ganador();
        nuevoGanador.setSorteo(sorteo);
        nuevoGanador.setInscripcion(inscripcionGanadora);

        // Guardar el nuevo ganador en el repositorio
        ganadorRepository.save(nuevoGanador);

        // Obtener el correo electrónico del ganador
        String correoGanador = inscripcionGanadora.getParticipante().getUsuarioId().getCorreoElectronico();

        // Retornar un mensaje informativo con el correo del ganador
        return "El ganador del sorteo del evento '" + sorteo.getEvento().getNombre() + "' es: " + correoGanador;
    }

    private List<Inscripcion> obtenerInscripciones(Evento evento) {
        // Usar el repositorio de inscripciones para obtener las inscripciones de un evento
        return inscriptionRepository.findAllByEventoId(evento.getId());
    }

    private Inscripcion seleccionarGanador(List<Inscripcion> inscripciones) {
        Random random = new Random();
        return inscripciones.get(random.nextInt(inscripciones.size()));
    }
}