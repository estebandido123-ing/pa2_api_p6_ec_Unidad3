package ec.edu.uce.infraestructure.repository;

import ec.edu.uce.domain.model.Mail;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class MailRepositoryImpl implements PanacheRepositoryBase<Mail, Integer>{
    

}