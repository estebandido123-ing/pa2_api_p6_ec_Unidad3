package ec.edu.uce;

import java.time.LocalDate;

import ec.edu.uce.application.service.FacturaServiceCompletaFuture;
import ec.edu.uce.application.service.FacturaServiceParalelo;
import ec.edu.uce.domain.model.Factura;
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
        private FacturaServiceParalelo fsp;

        @Inject
        private FacturaServiceCompletaFuture fscf;

        @Override
        public int run(String... args) throws Exception {


            
            String nombreHilo = Thread.currentThread().getName();
            System.out.println("nombre del hilo MAIN:" + nombreHilo);
            System.out.println("ID: "+ Thread.currentThread().threadId());

            Factura f1 = new Factura();
            f1.setFecha(LocalDate.now());
            f1.setNumero("0004-6969");
            f1.setRuc("172755555");
            this.fscf.guardar(f1);

            return 0;
        }
    }
}