package ec.com.uce.Application.service;

import ec.com.uce.Domain.model.Reporte;
import jakarta.inject.Inject;

public class ReporteServiceTarea implements Runnable{

    //@Inject
    private ReporteService reporteService;

    private Reporte reporte;

    public ReporteServiceTarea(Reporte reporte, ReporteService reporteService){
        this.reporte = reporte;
        this.reporteService = reporteService;
    }

    @Override
    public void run(){
        System.out.println(this.reporteService);
        this.reporteService.guardar(this.reporte);
    }

}
