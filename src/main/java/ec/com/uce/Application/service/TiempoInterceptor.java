package ec.com.uce.Application.service;

import jakarta.annotation.Priority;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@MedidorTiempo
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class TiempoInterceptor {

    @AroundInvoke
    public Object medir(InvocationContext context) throws Exception {
        // 1. Iniciamos el cronómetro
        long inicio = System.currentTimeMillis();

        // 2. Ejecutamos el método original
        Object resultado = context.proceed();

        // 3. Detenemos el cronómetro y calculamos
        long fin = System.currentTimeMillis();
        long tiempoTotal = fin - inicio;

        // Limpiamos el nombre de la clase (Quarkus añade "_Subclass" en tiempo de ejecución)
        String nombreClase = context.getTarget().getClass().getSuperclass().getSimpleName();
        String nombreMetodo = context.getMethod().getName();

        System.out.println("⏱️ [INTERCEPTOR] -> " + nombreClase + "." + nombreMetodo + 
                           " | Tiempo de ejecución: " + tiempoTotal + " ms");

        return resultado;
    }
}