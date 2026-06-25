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
        this.mailRepositoryImpl.persist(mail);
    }

    public Mail buscarPorId(Integer id) {
        return this.mailRepositoryImpl.findById(id);
    }
}