package ec.edu.uce.application.service;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import ec.edu.uce.application.service.Interceptors.MedirTiempo;
import ec.edu.uce.domain.model.Factura;
import ec.edu.uce.domain.model.Mail;
import ec.edu.uce.domain.model.Reporte;
import ec.edu.uce.infraestructure.repository.FacturaRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
//La clase que hace el llamado a la Orquesta
public class FacturaServiceParalelo {

    @Inject
    private FacturaRepositoryImpl fri;

    @Inject
    private ReporteService rs;

    @Inject
    private MailService ms;

    @Inject 
    private ReporteServiceTarea rst;

    @Inject 
    private MailServiceTarea mst;

    @MedirTiempo
    public void guardar(Factura factura) throws InterruptedException, ExecutionException {

        String nombreHilo = Thread.currentThread().getName();
        System.out.println("nombre del hilo FACTURASERVICEPARALELA:" + nombreHilo);
        System.out.println("ID: " + Thread.currentThread().threadId());

        this.fri.persist(factura);

        ExecutorService exs = Executors.newFixedThreadPool(2);

        //Clase Orquesta
        Reporte r = new Reporte();
        r.setTitulo("Reporte sobre la Factura");
        r.setFormato("PDF");
        r.setDescripcion("El siguiente reporte se da sobre xxxxxx");
        r.setFecha(LocalDate.now());
        this.rst.setReporte(r);        
        
        Future<?> futureReporte = exs.submit(rst);
        //dispara el hilo
        //exs.submit(rst);
        //this.rs.guardarReporte(r);

        Mail m = new Mail();
        m.setAsunto("Factura");
        m.setDestinatario("psaguas@uce.edu.es");
        m.setFechaenvio(LocalDate.now());
        this.mst.setMail(m);
        Future<?> futureMail = exs.submit(mst);

        //esto hace que se debe terminar la tarea de reporte o otras tareas
        futureReporte.get();
        futureMail.get();

        //exs.submit(mst);
        //this.ms.guardarMail(m);

        //Cerramos el proceso de ejecucion de que no se va a enviar mas tareas
        exs.shutdown();
        //Lo mandamos a dormir para dar tiempo de ejecutarse los metodos 
        //Thread.sleep(10000);


    }
}
