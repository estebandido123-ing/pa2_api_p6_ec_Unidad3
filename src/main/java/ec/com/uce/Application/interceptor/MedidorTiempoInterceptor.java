package ec.com.uce.Application.interceptor;

import java.time.LocalDateTime;

import ec.com.uce.Application.service.AuditoriaService;
import ec.com.uce.Domain.model.Auditoria;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@MedidorTiempo
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class MedidorTiempoInterceptor {

    @Inject
    private AuditoriaService auditoriaService; // Inyectamos el servicio para guardar en BD

    @AroundInvoke
    public Object medir(InvocationContext context) throws Exception {
        
        long inicio = System.currentTimeMillis();
        
        // Ejecuta el método original (crear, actualizar o eliminar)
        Object resultado = context.proceed();
        
        long fin = System.currentTimeMillis();
        long tiempoTotal = fin - inicio;

        // 1. Obtener el nombre del método
        String nombreMetodo = context.getMethod().getName();

        // 2. Extraer los argumentos recibidos y usar su toString()
        StringBuilder argumentosStr = new StringBuilder();
        if (context.getParameters() != null && context.getParameters().length > 0) {
            for (Object param : context.getParameters()) {
                if (param != null) {
                    argumentosStr.append(param.toString()).append(" | ");
                } else {
                    argumentosStr.append("null | ");
                }
            }
        } else {
            argumentosStr.append("Sin argumentos");
        }

        // 3. Crear y llenar la entidad Auditoria
        Auditoria audit = new Auditoria();
        audit.setNombreMetodo(nombreMetodo);
        audit.setArgumentos(argumentosStr.toString());
        audit.setFechaHoraEjecucion(LocalDateTime.now());
        audit.setTiempoEjecucionMs(tiempoTotal);

        // 4. Guardar en Base de Datos
        auditoriaService.guardar(audit);

        System.out.println("Auditoría] Registro guardado en BD -> Método: " + nombreMetodo + " | Tiempo: " + tiempoTotal + " ms");

        return resultado;
    }
}