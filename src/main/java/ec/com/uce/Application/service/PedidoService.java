package ec.com.uce.Application.service;

import ec.com.uce.Domain.model.Envio;
import ec.com.uce.Domain.model.Notificacion;
import ec.com.uce.Domain.model.Pago;
import ec.com.uce.Domain.model.Producto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PedidoService {

    @Inject private InventarioService inventarioService;
    @Inject private PagoService pagoService;
    @Inject private LogisticaService logisticaService;
    @Inject private NotificacionService notificacionService;

    @MedidorTiempo 
    public void procesarPedidoCompleto() {
        System.out.println("\nPedido Principal Iniciando... Hilo: " + Thread.currentThread().getName() + " | ID: " + Thread.currentThread().getId());

        Producto prod = new Producto();
        prod.setNombre("Laptop Gamer");
        prod.setStock(9);
        inventarioService.guardarProducto(prod);

        Pago pago = new Pago();
        pago.setMonto(1500.00);
        pago.setEstado("APROBADO");
        pagoService.procesarCobro(pago);

        Envio envio = new Envio();
        envio.setDireccion("Av. Naciones Unidas");
        logisticaService.prepararDespacho(envio);

        Notificacion notif = new Notificacion();
        notif.setCelular("0991234567");
        notificacionService.enviarSms(notif);

        System.out.println("Pedido Principal Procesamiento en Base de Datos finalizado.\n");
    }
}