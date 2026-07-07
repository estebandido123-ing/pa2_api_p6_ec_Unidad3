package ec.edu.uce.application.service;

import ec.edu.uce.domain.model.Reporte;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class ReporteServiceTarea implements Runnable {

    @Inject
    ReporteService rs;

    private Reporte r;

    public void setReporte(Reporte reporte){
        this.r = reporte;

    }

    @Override
    public void run() {
        String nombreHilo = Thread.currentThread().getName();
        System.out.println("nombre del hilo REPORTESERVICETAREA:" + nombreHilo);
        this.rs.guardarReporte(this.r);
    }

}
