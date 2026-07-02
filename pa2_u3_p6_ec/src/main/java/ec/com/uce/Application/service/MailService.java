package ec.com.uce.Application.service;

import ec.com.uce.Domain.model.Mail;
import ec.com.uce.Infraestructure.repository.MailRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class MailService {

    @Inject
    private MailRepositoryImpl mailRepositoryImpl;

    
    public void guardar(Mail mail) {

        String nombreHilo = Thread.currentThread().getName();
        System.out.println("Nombre del hilo MailService " + nombreHilo); 

        System.out.println("ID" + Thread.currentThread().threadId());

        // Inicio del cronómetro manual
        long tiempoInicio = System.currentTimeMillis();

        this.mailRepositoryImpl.persist(mail);

        // Fin del cronómetro manual
        long tiempoFin = System.currentTimeMillis();
        System.out.println("MailService = Tiempo de guardado en BD: " + (tiempoFin - tiempoInicio) + " ms");


        mail.persist();
        this.mailRepositoryImpl.persist(mail);
    }

    public Mail buscarPorId(Integer id) {
        return this.mailRepositoryImpl.findById(id);
        //return Mail.findById(id);
    }
}