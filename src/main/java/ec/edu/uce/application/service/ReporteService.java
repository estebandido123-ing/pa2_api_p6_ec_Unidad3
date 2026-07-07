package ec.edu.uce.application.service;

import ec.edu.uce.application.service.Interceptors.MedirTiempo;
import ec.edu.uce.domain.model.Reporte;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class ReporteService {

    @MedirTiempo
    public void guardarReporte(Reporte reporte) {

        String nombreHilo = Thread.currentThread().getName();
        System.out.println("nombre del hilo REPORTESERVICE:" + nombreHilo);
        System.out.println("ID: " + Thread.currentThread().threadId());

        reporte.persist();

    }

    public Reporte buscarReporteporId(Integer id) {

        return Reporte.findById(id);

    }

}