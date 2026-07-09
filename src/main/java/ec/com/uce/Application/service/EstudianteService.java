package ec.com.uce.Application.service;

import ec.com.uce.Application.interceptor.AuditarArchivo;
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

    @MedidorTiempo   
    @AuditarArchivo 
    public void crear(Estudiante estudiante) {
        this.estudianteRepository.persist(estudiante);
    }

    @MedidorTiempo 
    public void actualizar(Estudiante estudiante) {
        // En Panache, usamos el EntityManager para hacer un Merge (Actualizar)
        this.estudianteRepository.getEntityManager().merge(estudiante);
    }

    @MedidorTiempo 
    public void eliminar(Integer idEstudiante) {
        this.estudianteRepository.deleteById(idEstudiante);
    }

    
    public Estudiante buscarPorId(Integer id) {
        return this.estudianteRepository.findById(id);
    }

    @MedidorTiempo 
    public void guardarlistaestudiantes(java.util.List<Estudiante> estudiantes) {
        
        // Panache procesa toda la lista automáticamente
        this.estudianteRepository.persist(estudiantes);
        
        System.out.println("Se ha guardado un bloque de " + estudiantes.size() + " estudiantes en la base de datos.");
    }

}