package ec.com.uce;

import ec.com.uce.Application.service.EstudianteService;
import ec.com.uce.Domain.model.Estudiante;
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
        private EstudianteService estudianteService;

        @Override
        public int run(String... args) throws Exception {

            System.out.println(" TALLER: AUDITORÍA CON INTERCEPTORES CRUD");
            

            // 1. PROBAR CREACIÓN
            System.out.println("\n-> 1. Ejecutando método: crear()");
            Estudiante estudiante = new Estudiante();
            estudiante.setNombre("Esteban");
            estudiante.setMatricula("MATRICULA1");
            estudianteService.crear(estudiante); 
            // Al terminar, el interceptor guardará en Auditoria: "Estudiante 

            // 2. PROBAR ACTUALIZACIÓN
            System.out.println("\n Ejecutando método: actualizar");
            estudiante.setNombre("Esteban Modificado");
            estudianteService.actualizar(estudiante);
            // El interceptor guardará en Auditoria: "Estudiante 

            // 3. PROBAR ELIMINACIÓN
            System.out.println("\n Ejecutando método: eliminar");
            Integer idParaEliminar = estudiante.getId();
            estudianteService.eliminar(idParaEliminar);
            // El interceptor guardará en Auditoria: "1 |" 

            
            System.out.println("Todos los métodos fueron auditados correctamente en PostgreSQL.");
            

            Quarkus.waitForExit();
            return 0;        
        }
    }
}