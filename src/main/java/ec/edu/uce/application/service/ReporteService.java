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

        reporte.persist();

    }

    @Auditar
    public void guardarListadeReporte(List<Reporte> listar) throws InterruptedException{
        
        for(Reporte p : listar){
            this.guardarReporte(p);

        }

    }

    @Auditar
    public void guardarListaReportesParalelo(List<Reporte> lista) {
        lista.parallelStream().forEach(rep -> {
            // Aqui programo toda la logica qye quieor que se aplique a cada item
            try {
                this.guardarReporte(rep);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
 
    }

    public Reporte buscarReporteporId(Integer id) {

        return Reporte.findById(id);

    }

}