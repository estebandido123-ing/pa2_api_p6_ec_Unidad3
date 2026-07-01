package ec.com.uce.Application.service;

import ec.com.uce.Domain.model.Envio;
import ec.com.uce.Infraestructure.repository.EnvioRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class LogisticaService {

    @Inject
    private EnvioRepositoryImpl envioRepository;

    @MedidorTiempo
    public void prepararDespacho(Envio envio) {
        System.out.println("Logística Hilo: " + Thread.currentThread().getName() + " | ID: " + Thread.currentThread().getId());
        envioRepository.persist(envio);
    }
}