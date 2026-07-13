package ec.com.uce;

import java.util.ArrayList;
import java.util.List;

import ec.com.uce.Application.service.ActaElectoralService;
import ec.com.uce.Domain.model.ActaElectoral;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;

@QuarkusMain
public class Main {
public static void main(String... args) {
        Quarkus.run(App.class, args);
    }

    public static class App implements QuarkusApplication {

        @Inject
        private ActaElectoralService actaService;

        @Override
        public int run(String... args) throws Exception {

            System.out.println(" INICIANDO GENERACIÓN DE 500,000 REGISTROS");
   

            List<ActaElectoral> listaActas = new ArrayList<>(500000);
            
            // Generar medio millón de actas en memoria
            for(int i = 1; i <= 500000; i++){
                ActaElectoral acta = new ActaElectoral();
                acta.setProvincia("Pichincha");
                acta.setCanton("Canton " + (i % 100)); // Para no hacer strings inmensos
                acta.setParroquia("Parroquia " + (i % 100));
                acta.setVotosValidos(1500); 
                acta.setVotosNulos(45);
                
                listaActas.add(acta);
            }
            
            System.out.println(" Registros generados en memoria. Iniciando inserción en BD...");

            
            //1. Descomenta la línea de abajo, ejecuta y toma captura del tiempo.
            this.actaService.guardarListaDeActasSecuencial(listaActas);
            
            // 2. Luego, comenta la de arriba, descomenta la de abajo, ejecuta y toma la 2da captura.
            //this.actaService.guardarListaDeActasParalelo(listaActas);
            

            System.out.println(" PROCESO FINALIZADO CON ÉXITO");


            Quarkus.waitForExit();
            return 0;
        }
    }
}