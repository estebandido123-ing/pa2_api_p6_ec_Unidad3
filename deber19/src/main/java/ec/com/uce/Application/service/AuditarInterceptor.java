package ec.com.uce.Application.service;

import java.time.LocalDateTime;
import java.util.Arrays;

import ec.com.uce.Domain.model.Auditoria;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Interceptor
@Auditar
@Priority(Interceptor.Priority.APPLICATION)
public class AuditarInterceptor {

    @Inject
    private AuditoriaService auditoriaService;

    @AroundInvoke
    public Object medirTiempoYAuditar(InvocationContext context) throws Exception {

        long inicio = System.currentTimeMillis();

        try {
            return context.proceed();
        } finally {

            long fin = System.currentTimeMillis();

            String nombreMetodo = context.getMethod().getDeclaringClass().getSimpleName()
                    + "." + context.getMethod().getName();

            String argumentos = Arrays.toString(context.getParameters());

            long tiempo = fin - inicio;

            // Mensaje en consola
            System.out.println("\n--- Iniciando Auditoria ---");
            System.out.println("Método de la Auditoria: " + nombreMetodo);
            System.out.println("Argumentos: " + argumentos);
            System.out.println("Tiempo de ejecución TOTAL: " + tiempo + " ms");
            System.out.println("--- Finalizando Auditoria ---\n");

            Auditoria auditoria = new Auditoria();
            auditoria.setNombreMetodo(nombreMetodo);
            // Si el texto de los 500 argumentos es muy largo, cortamos para que no colapse la BD
            if (argumentos.length() > 250) {
                auditoria.setArgumentos(argumentos.substring(0, 247) + "...");
            } else {
                auditoria.setArgumentos(argumentos);
            }
            auditoria.setFechaHoraEjecucion(LocalDateTime.now());
            auditoria.setTiempoEjecucionMs(tiempo);

            auditoriaService.guardar(auditoria);
        }
    }
}
