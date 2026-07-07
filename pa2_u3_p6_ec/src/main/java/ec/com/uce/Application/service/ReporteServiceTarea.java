package ec.com.uce.Application.service;

import ec.com.uce.Domain.model.Reporte;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;


@Dependent
public class ReporteServiceTarea implements Runnable{

    @Inject
    private ReporteService reporteService;

    private Reporte reporte;

    public void setReporte(Reporte reporte){
        this.reporte = reporte;
    }

    @Override
    public void run(){
        System.out.println(this.reporteService);
        this.reporteService.guardar(this.reporte);
    }

}
