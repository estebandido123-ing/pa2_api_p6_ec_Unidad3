package ec.edu.uce.application.service;

import java.time.LocalDate;

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
public class FacturaService {

    @Inject
    private FacturaRepositoryImpl fr;

    @Inject
    private ReporteService rs;
    @Inject
    private MailService ms;

    @MedirTiempo
    public void guardar(Factura factura) {

        String nombreHilo = Thread.currentThread().getName();
        System.out.println("nombre del hilo FACTURASERVICE:" + nombreHilo);
        System.out.println("ID: " + Thread.currentThread().threadId());

        this.fr.persist(factura);

        Reporte r = new Reporte();
        r.setTitulo("Reporte sobre la Factura");
        r.setFormato("PDF");
        r.setDescripcion("El siguiente reporte se da sobre xxxxxx");
        r.setFecha(LocalDate.now());
        this.rs.guardarReporte(r);

        Mail m = new Mail();
        m.setAsunto("Factura");
        m.setDestinatario("psaguas@uce.edu.es");
        m.setFechaenvio(LocalDate.now());
        this.ms.guardarMail(m);

    }


    public Factura buscarPorId(Integer id) {

        return Factura.findById(id);
    }

}
