package ec.com.uce.Application.interceptor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import jakarta.annotation.Priority;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@AuditarArchivo
@Interceptor
@Priority(Interceptor.Priority.APPLICATION + 1) 
public class ArchivoInterceptor {

    private static final String RUTA_ARCHIVO = "registro_estudiantes.txt";

    @AroundInvoke
    public Object escribirEnArchivo(InvocationContext context) throws Exception {
        
        // 1. Dejamos que el método original se ejecute (que se guarde en PostgreSQL)
        Object resultado = context.proceed();

        // 2. Extraemos el objeto Estudiante que se acaba de guardar
        String datosEstudiante = "Sin datos";
        if (context.getParameters() != null && context.getParameters().length > 0) {
            datosEstudiante = context.getParameters()[0].toString();
        }

        // 3. Escribimos en el archivo de texto (el 'true' significa que no borra lo anterior, sino que agrega líneas nuevas)
        try (FileWriter fw = new FileWriter(RUTA_ARCHIVO, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            
            out.println("[" + LocalDateTime.now() + "] ESTUDIANTE CREADO -> " + datosEstudiante);
            System.out.println("ArchivoInterceptor Datos del estudiante guardados en archivo TXT.");
            System.out.println("");

        } catch (Exception e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }

        return resultado;
    }
}
