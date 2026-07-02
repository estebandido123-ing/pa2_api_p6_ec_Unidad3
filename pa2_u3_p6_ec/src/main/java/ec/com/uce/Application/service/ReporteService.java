package ec.com.uce.Application.service;

import ec.com.uce.Domain.model.Reporte;
import ec.com.uce.Infraestructure.repository.ReporteRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class ReporteService {

    @Inject
    private ReporteRepositoryImpl reporteRepositoryImpl;

    public void guardar(Reporte reporte) {

        String nombreHilo = Thread.currentThread().getName();
        System.out.println("Nombre del hilo ReporteService " + nombreHilo); 

        System.out.println("ID" + Thread.currentThread().threadId());


        // Inicio del cronómetro manual
        long tiempoInicio = System.currentTimeMillis();

        this.reporteRepositoryImpl.persist(reporte);

        // Fin del cronómetro manual
        long tiempoFin = System.currentTimeMillis();
        System.out.println("ReporteService = Tiempo de guardado en BD: " + (tiempoFin - tiempoInicio) + " ms");

        
        this.reporteRepositoryImpl.persist(reporte);
    }

    public Reporte buscarPorId(Integer id) {
        return this.reporteRepositoryImpl.findById(id);
    }
}