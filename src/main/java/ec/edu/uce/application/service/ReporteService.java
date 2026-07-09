package ec.edu.uce.application.service;

import java.util.List;

import ec.edu.uce.application.service.Interceptors.MedirTiempo;
import ec.edu.uce.domain.model.Reporte;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class ReporteService {

    @MedirTiempo
    public void guardarReporte(Reporte reporte) throws InterruptedException {

        String nombreHilo = Thread.currentThread().getName();
        System.out.println("nombre del hilo REPORTESERVICE:" + nombreHilo);
        System.out.println("ID: " + Thread.currentThread().threadId());
        Thread.sleep(3000);

        reporte.persist();

    }

    @Auditar
    public void guardarListadeReporte(List<Reporte> listar) throws InterruptedException{
        
        for(Reporte p : listar){
            this.guardarReporte(p);

        }

    }

    public Reporte buscarReporteporId(Integer id) {

        return Reporte.findById(id);

    }

}