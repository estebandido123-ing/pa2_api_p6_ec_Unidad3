
import java.time.LocalDate;

import ec.com.uce.Application.service.FacturaService;
import ec.com.uce.Application.service.FacturaServiceParalelo;
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
        
        @Inject
        private FacturaServiceParalelo facturaServiceParalelo;

        @Override
        public int run(String... args) throws Exception {

            String nombreHilo = Thread.currentThread().getName();
            System.out.println("Nombre del hilo Main " + nombreHilo); 

            System.out.println("ID" + Thread.currentThread().threadId());


            System.out.println("Conexion a la base de datos POSTGRES!");

            Factura factura = new Factura();
            factura.setFecha(LocalDate.of(2023, 12, 1));
            factura.setNumero("1521254124");
            factura.setRUC("12131451");

            //this.facturaService.guardar(factura);

            this.facturaServiceParalelo.guardar(factura);

            //Factura fac = this.facturaService.buscarPorId(1);
            //System.err.println("Numero: "+ fac.getNumero());
            
            Quarkus.waitForExit();
            return 0;
            
            

            //para cierto tipos de logica, pq no es para todo, unicamente cuando las 
            //dos cosas no dependen del uno del otro, solo ahi es cuando se puede ejecuar 
            //en paralelo
            //, en paralelo, se dice que es cuando se ejecutan dos o mas hilos en el 
            //mismo momento
            //la programacion en paralelo, si necesita ejecutar un metodo, ejecuta un hilo 
            //pero si quiere ejecutar otro metodo, ejecutara otro hilo
            // cuando necesito optimizar tiempo de ejecucuion, cuando se tiene escenarios 
            // altamente demandantes (alata concurrencia), para evitar o mejorar los tiemmpos de demora en recibir l
            // la respuesta    
        }
    }
}