package ec.com.uce.Application.interceptor;

import jakarta.annotation.Priority;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@MedirTiempo
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class MedirTiempoInterceptor {

    @AroundInvoke
    public Object medir(InvocationContext context) throws Exception {

        long inicio = System.currentTimeMillis();

        try {
            return context.proceed();
        } finally {
            long fin = System.currentTimeMillis();

            System.out.println("⏱️ [MedirTiempo] " 
                + context.getMethod().getName() 
                + " tardó " 
                + (fin - inicio) 
                + " ms");
        }
    }
}