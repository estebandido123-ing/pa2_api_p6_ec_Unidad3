package ec.com.uce.Infraestructure.repository;

import ec.com.uce.Domain.model.ActaElectoral;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class ActaElectoralRepositoryImpl implements PanacheRepositoryBase<ActaElectoral, Integer> {
}