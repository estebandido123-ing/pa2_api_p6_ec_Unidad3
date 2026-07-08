package ec.com.uce.Application.service;

import ec.com.uce.Domain.model.Auditoria;
import ec.com.uce.Infraestructure.repository.AuditoriaRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Transactional(Transactional.TxType.REQUIRES_NEW) // Crea una nueva transacción independiente para asegurar que la auditoría siempre se guarde
@ApplicationScoped
public class AuditoriaService {

    @Inject
    private AuditoriaRepositoryImpl auditoriaRepository;

    public void guardar(Auditoria auditoria) {
        this.auditoriaRepository.persist(auditoria);
    }
}