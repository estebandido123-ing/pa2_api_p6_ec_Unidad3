package ec.edu.uce.infraestructure.repository;





import ec.edu.uce.domain.model.Auditoria;
import ec.edu.uce.domain.service.AuditoriaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class AuditoriaRepositoryImpl implements AuditoriaRepository {

    @Inject
    private EntityManager em;

    
    @Override
    public void insertar(Auditoria auditoria){
        em.persist(auditoria);
    }

}