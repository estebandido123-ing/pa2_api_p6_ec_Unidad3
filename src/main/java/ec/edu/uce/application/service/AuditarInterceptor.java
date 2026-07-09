package ec.edu.uce.application.service;

import java.time.LocalDateTime;
import java.util.Arrays;

import ec.edu.uce.application.AuditoriaService;
import ec.edu.uce.domain.model.Auditoria;
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
            System.out.println("Iniciando Auditoria...");
            System.out.println("Método de la Auditoria: " + nombreMetodo);
            System.out.println("Argumentos: " + argumentos.toString());
            System.out.println("Tiempo de ejecución: " + tiempo + " ms");
            System.out.println("Finalizando Auditoria...");

            Auditoria auditoria = new Auditoria();
            auditoria.setNombreMetodo(nombreMetodo);
            auditoria.setArgumentos(argumentos);
            auditoria.setFechaHoraEjecucion(LocalDateTime.now());
            auditoria.setTiempoEjecucionMs(tiempo);

            auditoriaService.guardar(auditoria);
        }
    }
}