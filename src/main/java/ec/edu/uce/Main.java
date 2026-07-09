package ec.edu.uce;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ec.edu.uce.application.service.FacturaServiceCompletaFuture;
import ec.edu.uce.application.service.FacturaServiceParalelo;
import ec.edu.uce.application.service.ReporteService;
import ec.edu.uce.domain.model.Factura;
import ec.edu.uce.domain.model.Reporte;
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

        @Inject
        private ReporteService rs;
        @Override
        public int run(String... args) throws Exception {

            List<Reporte> list = new ArrayList<>();
            for(int i = 0; i< 10; i++){
                Reporte r1 = new Reporte();
                r1.setTitulo("Reportar");
                r1.setFecha(LocalDate.now());
                r1.setFormato("Incrustinio");
                r1.setDescripcion("Reporte de ....");
                list.add(r1);
            }
            this.rs.guardarListadeReporte(list);
            return 0;
        }
    }
}