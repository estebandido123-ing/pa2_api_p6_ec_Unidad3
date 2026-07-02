package ec.com.uce.Infraestructure.repository;

import ec.com.uce.Domain.model.Factura;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class FacturaRepositoryImpl implements PanacheRepositoryBase<Factura, Integer>{



    public Factura buscarPorNumero(String numero){
        return null;
    }

}
