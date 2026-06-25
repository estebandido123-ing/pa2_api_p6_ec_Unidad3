
import java.time.LocalDate;

import ec.com.uce.Application.service.FacturaService;
import ec.com.uce.Domain.model.Factura;
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
        private FacturaService facturaService;
        
        

        @Override
        public int run(String... args) throws Exception {

            System.out.println("Conexion a la base de datos POSTGRES!");

            Factura factura = new Factura();
            factura.setFecha(LocalDate.of(2023, 12, 1));
            factura.setNumero("1521254124");
            factura.setRUC("12131451");

            this.facturaService.guardar(factura);

            //Factura fac = this.facturaService.buscarPorId(1);
            //System.err.println("Numero: "+ fac.getNumero());
            
            Quarkus.waitForExit();
            return 0;        
        }
    }
}