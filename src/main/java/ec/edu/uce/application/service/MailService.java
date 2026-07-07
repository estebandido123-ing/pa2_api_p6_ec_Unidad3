package ec.edu.uce.application.service;

import ec.edu.uce.application.service.Interceptors.MedirTiempo;
import ec.edu.uce.domain.model.Mail;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class MailService {

    @MedirTiempo
    public void guardarMail(Mail mail) {

        String nombreHilo = Thread.currentThread().getName();
        System.out.println("nombre del hilo MAILSERVICE:" + nombreHilo);
        System.out.println("ID: " + Thread.currentThread().threadId());

        mail.persist();

    }

    public Mail buscarMailPorId(Integer id) {

        return Mail.findById(id);

    }
}