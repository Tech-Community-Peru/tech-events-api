package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.model.entity.Evento;
import com.techcommunityperu.techcommunityperu.model.entity.Inscripcion;
import com.techcommunityperu.techcommunityperu.model.entity.Usuario;
import com.techcommunityperu.techcommunityperu.model.enums.paymentStatus;
import com.techcommunityperu.techcommunityperu.model.enums.paymentType;
import com.techcommunityperu.techcommunityperu.model.enums.statusInscription;
import com.techcommunityperu.techcommunityperu.repository.EventRepository;
import com.techcommunityperu.techcommunityperu.repository.InscriptionRepository;
import com.techcommunityperu.techcommunityperu.repository.UserRepository; // Asegúrate de importar el repositorio de Usuario
import com.techcommunityperu.techcommunityperu.service.PaymentService;
import com.techcommunityperu.techcommunityperu.service.PurchaseService;
import com.techcommunityperu.techcommunityperu.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException; // Importar NoSuchElementException

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private EventRepository eventoRepository;

    @Autowired
    private InscriptionRepository inscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private EmailService emailService;


    @Override
    public String purchaseTicket(Integer eventoId, Integer usuarioId, paymentType tipoPago) {
        // Buscar el evento
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new NoSuchElementException("Evento no encontrado"));

        // Buscar el usuario
        Usuario usuario = userRepository.findById(usuarioId)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        double monto = evento.getCosto();
        paymentStatus statusPago = paymentService.processPayment(tipoPago, monto);

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setTipoPago(tipoPago);
        inscripcion.setMonto(evento.getCosto());
        inscripcion.setEvento(evento);
        inscripcion.setUsuario(usuario); // Asociar el usuario encontrado

        if (statusPago == paymentStatus.PAID) {
            inscripcion.setInscripcionStatus(statusInscription.PAID);
            inscriptionRepository.save(inscripcion);
           // emailService.sendConfirmationEmail(inscripcion, monto);
            return "Compra exitosa. Recibirás un correo con tu entrada.";
        } else {
            inscripcion.setInscripcionStatus(statusInscription.PENDING);
            inscriptionRepository.save(inscripcion);
            return "Error en el pago. Por favor, inténtalo de nuevo.";
        }
    }
}