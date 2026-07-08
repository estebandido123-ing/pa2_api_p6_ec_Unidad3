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

            System.out.println("==========================================");
            System.out.println(" TALLER: AUDITORÍA CON INTERCEPTORES CRUD");
            System.out.println("==========================================");

            // 1. PROBAR CREACIÓN
            System.out.println("\n-> 1. Ejecutando método: crear()");
            Estudiante estudiante = new Estudiante();
            estudiante.setNombre("Esteban");
            estudiante.setMatricula("MAT-2026-A");
            estudianteService.crear(estudiante); 
            // Al terminar, el interceptor guardará en Auditoria: "Estudiante [id=null, nombre=Esteban...]"

            // 2. PROBAR ACTUALIZACIÓN
            System.out.println("\n-> 2. Ejecutando método: actualizar()");
            estudiante.setNombre("Esteban Modificado");
            estudianteService.actualizar(estudiante);
            // El interceptor guardará en Auditoria: "Estudiante [id=1, nombre=Esteban Modificado...]"

            // 3. PROBAR ELIMINACIÓN
            System.out.println("\n-> 3. Ejecutando método: eliminar()");
            Integer idParaEliminar = estudiante.getId();
            estudianteService.eliminar(idParaEliminar);
            // El interceptor guardará en Auditoria: "1 |" (Ya que el argumento enviado fue solo el Integer ID)

            System.out.println("\n==========================================");
            System.out.println("Todos los métodos fueron auditados correctamente en PostgreSQL.");
            System.out.println("==========================================");

            Quarkus.waitForExit();
            return 0;        
        }
    }
}