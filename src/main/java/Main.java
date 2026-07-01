
import ec.com.uce.Application.service.PedidoService;
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
        private PedidoService pedidoService;

        @Override
        public int run(String... args) throws Exception {

            System.out.println(" CONEXION A BASE DE DATOS");
            System.out.println(" DEBER 18: PRUEBA DE INTERCEPTORES, HILOS");

            // Ejecutamos el método 
            pedidoService.procesarPedidoCompleto();

            System.out.println(" FLUJO DE PROCESAMIENTO FINALIZADO EXITOSAMENTE");

            Quarkus.waitForExit();
            return 0;        
        }
    }
}