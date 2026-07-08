package ec.com.uce.Application.service;

import ec.com.uce.Application.interceptor.MedidorTiempo;
import ec.com.uce.Domain.model.Estudiante;
import ec.com.uce.Infraestructure.repository.EstudianteRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class EstudianteService {

    @Inject
    private EstudianteRepositoryImpl estudianteRepository;

    @MedidorTiempo // <--- El interceptor atrapará este método
    public void crear(Estudiante estudiante) {
        this.estudianteRepository.persist(estudiante);
    }

    @MedidorTiempo // <--- El interceptor atrapará este método
    public void actualizar(Estudiante estudiante) {
        // En Panache, usamos el EntityManager para hacer un Merge (Actualizar)
        this.estudianteRepository.getEntityManager().merge(estudiante);
    }

    @MedidorTiempo // <--- El interceptor atrapará este método
    public void eliminar(Integer idEstudiante) {
        this.estudianteRepository.deleteById(idEstudiante);
    }

    // Método extra solo para buscar (No le ponemos interceptor para no llenar la BD)
    public Estudiante buscarPorId(Integer id) {
        return this.estudianteRepository.findById(id);
    }
}