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

            System.out.println(" INICIANDO INSERCIÓN MASIVA (500 REGISTROS)");

            List<ActaElectoral> listaActas = new ArrayList<>();
            
            // Bucle para generar exactamente 500 registros
            for(int i = 1; i <= 500; i++){
                ActaElectoral acta = new ActaElectoral();
                acta.setProvincia("Pichincha");
                acta.setCanton("Canton " + i);
                acta.setParroquia("Parroquia " + i);
                acta.setVotosValidos(1500 + i); 
                acta.setVotosNulos(45);
                
                listaActas.add(acta);
            }
            
            this.actaService.guardarListaDeActas(listaActas);
            
            System.out.println(" PROCESO FINALIZADO CON ÉXITO");

            Quarkus.waitForExit();
            return 0;
        }
    }
}