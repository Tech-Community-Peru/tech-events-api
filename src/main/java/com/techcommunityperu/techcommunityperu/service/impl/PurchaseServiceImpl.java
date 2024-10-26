package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.dto.InscripcionDTO;
import com.techcommunityperu.techcommunityperu.exceptions.InscriptionException;
import com.techcommunityperu.techcommunityperu.exceptions.ResourceNotFoundException;
import com.techcommunityperu.techcommunityperu.mapper.EventoMapper;
import com.techcommunityperu.techcommunityperu.mapper.ParticipanteMapper;
import com.techcommunityperu.techcommunityperu.model.entity.Evento;
import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;
import com.techcommunityperu.techcommunityperu.model.entity.Participante;
import com.techcommunityperu.techcommunityperu.model.enums.paymentStatus;
import com.techcommunityperu.techcommunityperu.model.enums.paymentType;
import com.techcommunityperu.techcommunityperu.model.enums.statusInscription;
import com.techcommunityperu.techcommunityperu.repository.EventRepository;
import com.techcommunityperu.techcommunityperu.repository.InscriptionRepository;
import com.techcommunityperu.techcommunityperu.repository.ParticipantRepository;
import com.techcommunityperu.techcommunityperu.service.PaymentService;
import com.techcommunityperu.techcommunityperu.service.PurchaseService;
import com.techcommunityperu.techcommunityperu.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private EventRepository eventoRepository;

    @Autowired
    private InscriptionRepository inscriptionRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EventoMapper eventoMapper;

    @Autowired
    private ParticipanteMapper participanteMapper;


    // Metodo para validar si el tipo de pago es reconocido
    private void validatePaymentType(paymentType tipoPago) {
        if (tipoPago == null) {
            throw new InscriptionException("Ese Tipo de Pago no es reconocido");
        }
    }

    @Override
    public double getCostoEvento(Integer eventoId) {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new InscriptionException("Evento no encontrado"));
        return evento.getCosto();
    }

    @Override
    public InscripcionDTO confirmInscription(int inscriptionId) {
        Inscripcion inscripcion = inscriptionRepository.findById(inscriptionId)
                .orElseThrow(() -> new ResourceNotFoundException("Inscripción no encontrada"));

        // Aquí puedes construir el InscripcionDTO
        InscripcionDTO inscripcionDTO = new InscripcionDTO();
        inscripcionDTO.setTipoPago(inscripcion.getTipoPago());
        inscripcionDTO.setMonto(inscripcion.getMonto());
        inscripcionDTO.setStatus(inscripcion.getInscripcionStatus());
        inscripcionDTO.setParticipante(participanteMapper.toDto(inscripcion.getParticipante()));
        inscripcionDTO.setEvento(eventoMapper.toDto(inscripcion.getEvento()));

        return inscripcionDTO;
    }

    @Override
    public String purchaseTicket(Integer eventoId, Integer partipanteId, paymentType tipoPago) {
        // Validar el tipo de pago
        validatePaymentType(tipoPago);

        // Buscar el evento
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new InscriptionException("Evento no encontrado"));

        // Buscar el participante
        Participante participante = participantRepository.findById(partipanteId)
                .orElseThrow(() -> new InscriptionException("Participante no encontrado"));

        // Validar si el costo del evento es mayor a 0 y el tipo de pago es FREE
        if (evento.getCosto() > 0 && tipoPago == paymentType.FREE) {
            return "Error: No puedes usar el tipo de pago FREE para eventos con costo mayor a 0.";
        }

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setTipoPago(tipoPago);
        inscripcion.setMonto(evento.getCosto());
        inscripcion.setEvento(evento);
        inscripcion.setParticipante(participante); // Asociar el participante encontrado

        // Verificar si el costo del evento es 0
        if (evento.getCosto() == 0) {
            inscripcion.setInscripcionStatus(statusInscription.PAID); // Establecer el estado como PAID
            inscriptionRepository.save(inscripcion); // Guardar la inscripción
            emailService.sendConfirmationEmail(inscripcion, 0); // Enviar correo sin monto
            return "Recibirás un correo con tu entrada gratuita.";
        }

        // Procesar pago para otros tipos
        double monto = evento.getCosto();
        paymentStatus statusPago = paymentService.processPayment(tipoPago, monto);

        if (statusPago == paymentStatus.PAID) {
            inscripcion.setInscripcionStatus(statusInscription.PENDING);
            inscriptionRepository.save(inscripcion);
            return "Redigiriendo a Paypal.";
        } else {
            return "Error en el pago. Por favor, inténtalo de nuevo.";
        }
    }
}