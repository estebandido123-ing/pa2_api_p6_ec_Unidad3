package ec.com.uce.Application.service;

import java.util.List;

import ec.com.uce.Application.interceptor.MedirTiempo;
import ec.com.uce.Domain.model.ActaElectoral;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
@Transactional
@ApplicationScoped
public class ActaElectoralService {

    // REQUIRES_NEW es obligatorio aquí para que el Parallel Stream no rompa la base de datos
    @MedirTiempo
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void guardarActa(ActaElectoral acta) {
        acta.persist(); 
    }

    //Procesamiento Secuencial
    @Auditar 
    public void guardarListaDeActasSecuencial(List<ActaElectoral> listaActas) {
        System.out.println("Procesando " + listaActas.size() + " registros de forma SECUENCIAL...");
        for(ActaElectoral acta : listaActas){
            this.guardarActa(acta);
        }
    }

    //Procesamiento en Paralelo (Fork-Join)
    @Auditar
    public void guardarListaDeActasParalelo(List<ActaElectoral> listaActas) {
        System.out.println("Procesando " + listaActas.size() + " registros en modo PARALELO (Fork-Join)...");
        listaActas.parallelStream().forEach(acta -> {
            this.guardarActa(acta);
        });
    }

    public ActaElectoral buscarActaPorId(Integer id) {
        return ActaElectoral.findById(id);
    }
}