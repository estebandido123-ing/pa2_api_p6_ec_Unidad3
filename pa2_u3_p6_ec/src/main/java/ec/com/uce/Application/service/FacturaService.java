package ec.com.uce.Application.service;

import java.time.LocalDate;

import ec.com.uce.Domain.model.Factura;
import ec.com.uce.Domain.model.Mail;
import ec.com.uce.Domain.model.Reporte;
import ec.com.uce.Infraestructure.repository.FacturaRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class FacturaService {


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

        long tiempoInicioTotal = System.currentTimeMillis();

        // Medir y guardar únicamente la Factura
        long inicioFactura = System.currentTimeMillis();
        this.facturaRepositoryImpl.persist(factura);
        long finFactura = System.currentTimeMillis();
        System.out.println("FacturaService = Tiempo de guardado de Factura en BD: " + (finFactura - inicioFactura) + " ms");

        // Guardar Reporte 
        Reporte rep = new Reporte();
        rep.setAutor("manuel");
        rep.setTipo("reporte de factura");
        rep.setFechaGeneracion(LocalDate.of(2002, 2, 2));
        this.reporteService.guardar(rep);

        // C) Guardar Mail 
        Mail mail = new Mail();
        mail.setAsunto("Nuevo mail");
        mail.setCuerpo("Asunto pendiente");
        mail.setDestinatario("jose");
        this.mailService.guardar(mail);

        long tiempoFinTotal = System.currentTimeMillis();
        System.out.println("\n Resultado Final = TIEMPO TOTAL DEL PROCESO EN CADENA: " + (tiempoFinTotal - tiempoInicioTotal) + " ms");


    }


    //se utiliza para ejecutar hilos especificos, 

    public Factura buscarPorId(Integer id) {

        return this.facturaRepositoryImpl.findById(id);
    }

}
