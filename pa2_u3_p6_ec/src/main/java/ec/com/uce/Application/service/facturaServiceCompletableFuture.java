package ec.com.uce.Application.service;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

import ec.com.uce.Domain.model.Factura;
import ec.com.uce.Domain.model.Mail;
import ec.com.uce.Domain.model.Reporte;
import ec.com.uce.Infraestructure.repository.FacturaRepositoryImpl;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class facturaServiceCompletableFuture {

    @Inject
    private FacturaRepositoryImpl facturaRepositoryImpl;

    @Inject
    private ReporteService reporteService;

    @Inject
    private MailService mailService;


    public void guardar(Factura factura) {

        String nombreHilo = Thread.currentThread().getName();
        System.out.println("Nombre del hilo FacturaService " + nombreHilo); 

        System.out.println("ID" + Thread.currentThread().threadId());

        //factura.persist();
        this.facturaRepositoryImpl.persist(factura);

        // Guardar Reporte 
        Reporte rep = new Reporte();
        rep.setAutor("manuel");
        rep.setTipo("reporte de factura");
        rep.setFechaGeneracion(LocalDate.of(2002, 2, 2));
        
        CompletableFuture <Void> completableReporte = CompletableFuture.runAsync(()-> this.reporteService.guardar(rep));
        


        // C) Guardar Mail 
        Mail mail = new Mail();
        mail.setAsunto("Nuevo mail");
        mail.setCuerpo("Asunto pendiente");
        mail.setDestinatario("jose");
        

        CompletableFuture <Void> completableMail = CompletableFuture.runAsync(()-> this.mailService.guardar(mail));

        //espera a que las dos tareas se termien 
        CompletableFuture.allOf(completableReporte, completableMail).join();


    }

}
