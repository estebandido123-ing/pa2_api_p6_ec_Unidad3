package ec.com.uce.Application.service;

import java.time.LocalDate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ec.com.uce.Domain.model.Factura;
import ec.com.uce.Domain.model.Mail;
import ec.com.uce.Domain.model.Reporte;
import ec.com.uce.Infraestructure.repository.FacturaRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped

public class FacturaServiceParalelo {

    @Inject
    private FacturaRepositoryImpl facturaRepositoryImpl;

    @Inject
    private MailService mailService;

    @Inject
    private ReporteService reporteService;


    public void guardar(Factura factura) throws Exception{

        String nombreHilo = Thread.currentThread().getName();
        System.out.println("Nombre del hilo FacturaService " + nombreHilo); 
        System.out.println("ID" + Thread.currentThread().threadId());

        long tiempoInicioTotal = System.currentTimeMillis();

        // Medir y guardar únicamente la Factura
        long inicioFactura = System.currentTimeMillis();
        this.facturaRepositoryImpl.persist(factura);
        long finFactura = System.currentTimeMillis();
        System.out.println("FacturaService = Tiempo de guardado de Factura en BD: " + (finFactura - inicioFactura) + " ms");


        ExecutorService executorService = Executors.newFixedThreadPool(1);

        // Guardar Reporte 
        Reporte rep = new Reporte();
        rep.setAutor("manuel");
        rep.setTipo("reporte de factura");
        rep.setFechaGeneracion(LocalDate.of(2002, 2, 2));
        //ReporteServiceTarea  reporteTarea = new ReporteServiceTarea(rep, reporteService);
        //Future <?> reporyFuture = executorService.submit(reporteTarea);

        

        // C) Guardar Mail 
        Mail mail = new Mail();
        mail.setAsunto("Nuevo mail");
        mail.setCuerpo("Asunto pendiente");
        mail.setDestinatario("jose");
        //MailServiceTarea mailTarea =  new MailServiceTarea(mail, mailService);
        //Future <?> mailFuture = executorService.submit(mailTarea);


        //reporyFuture.get();

        //mailFuture.get();
        
        executorService.shutdown();

        /*
        try {
            Thread.sleep(10000);
            //reporyFuture.get();
            //mailFuture.get();
        } catch (Exception e) {

        }
        */
        

       

        long tiempoFinTotal = System.currentTimeMillis();
        System.out.println("\n Resultado Final = TIEMPO TOTAL DEL PROCESO EN CADENA: " + (tiempoFinTotal - tiempoInicioTotal) + " ms");


    }

}