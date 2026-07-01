package ec.com.uce.Application.service;

import ec.com.uce.Domain.model.Notificacion;
import ec.com.uce.Infraestructure.repository.NotificacionRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class NotificacionService {

    @Inject
    private NotificacionRepositoryImpl notificacionRepository;

    @MedidorTiempo
    public void enviarSms(Notificacion notificacion) {
        System.out.println("Notificación Hilo: " + Thread.currentThread().getName() + " | ID: " + Thread.currentThread().getId());
        notificacionRepository.persist(notificacion);
    }
}
