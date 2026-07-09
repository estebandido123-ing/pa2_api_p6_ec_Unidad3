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

            for (int i = 1; i <= 10; i++) {
                Estudiante estDinamico = new Estudiante();
                estDinamico.setNombre("Estudiante Individual " + i);
                estDinamico.setMatricula("MAT-IND-" + i);
                
                // Esto llamará a tu ArchivoInterceptor 10 veces y hará 10 inserts separados
                estudianteService.crear(estDinamico); 
            }

            System.out.println("Todos los métodos fueron auditados correctamente en PostgreSQL.");
            
            //paralaleStrim, tiene un enfoque en procesar en paralelo, una misma accion para un 
            // connjuto de datos, un conjunto de elementos sobre el cual vamos a ejecutar un 
            // alugnas operaciones de procesamiento 


            Quarkus.waitForExit();
            return 0;        
        }
    }
}