package ec.com.uce.Infraestructure.repository;

import ec.com.uce.Domain.model.Envio;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
@Transactional @ApplicationScoped
public class EnvioRepositoryImpl implements PanacheRepositoryBase<Envio, Integer> {
    
}