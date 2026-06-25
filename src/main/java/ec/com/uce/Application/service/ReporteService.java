package ec.com.uce.Application.service;

import ec.com.uce.Domain.model.Reporte;
import ec.com.uce.Infraestructure.repository.ReporteRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class ReporteService {

    @Inject
    private ReporteRepositoryImpl reporteRepositoryImpl;

    public void guardar(Reporte reporte) {
        this.reporteRepositoryImpl.persist(reporte);
    }

    public Reporte buscarPorId(Integer id) {
        return this.reporteRepositoryImpl.findById(id);
    }
}
