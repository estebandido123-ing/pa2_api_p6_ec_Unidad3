package ec.com.uce.Infraestructure.repository;

import ec.com.uce.Domain.model.Mail;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class MailRepositoryImpl implements PanacheRepositoryBase<Mail, Integer> {
    // Los métodos básicos (persist, findById, delete) ya están integrados automáticamente
}
