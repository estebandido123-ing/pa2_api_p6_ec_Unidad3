package ec.com.uce.Application.service;

import ec.com.uce.Domain.model.Pago;
import ec.com.uce.Infraestructure.repository.PagoRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class PagoService {

    @Inject
    private PagoRepositoryImpl pagoRepository;

    @MedidorTiempo
    public void procesarCobro(Pago pago) {
        System.out.println("Pago Hilo: " + Thread.currentThread().getName() + " | ID: " + Thread.currentThread().getId());
        pagoRepository.persist(pago);
    }
}