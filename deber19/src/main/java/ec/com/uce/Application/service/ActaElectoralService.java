package ec.com.uce.Application.service;

import java.util.List;

import ec.com.uce.Application.interceptor.MedirTiempo;
import ec.com.uce.Domain.model.ActaElectoral;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class ActaElectoralService {

    // 1. Mide el tiempo de inserción de cada acta de forma INDIVIDUAL
    @MedirTiempo
    public void guardarActa(ActaElectoral acta) {
        acta.persist(); 
    }

    // 2. Mide el tiempo TOTAL de las 500 actas y lo guarda en la tabla auditoria
    @Auditar 
    public void guardarListaDeActas(List<ActaElectoral> listaActas) {
        
        System.out.println("Procesando " + listaActas.size() + " registros. Por favor espera...");
        
        // Iteramos la lista y guardamos una por una
        for(ActaElectoral acta : listaActas){
            this.guardarActa(acta);
        }
        
    }

    public ActaElectoral buscarActaPorId(Integer id) {
        return ActaElectoral.findById(id);
    }
}