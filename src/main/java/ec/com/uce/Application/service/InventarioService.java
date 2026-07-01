package ec.com.uce.Application.service;

import ec.com.uce.Domain.model.Producto;
import ec.com.uce.Infraestructure.repository.ProductoRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class InventarioService {

    @Inject
    private ProductoRepositoryImpl productoRepository;

    @MedidorTiempo
    public void guardarProducto(Producto producto) {
        System.out.println("Inventario Hilo: " + Thread.currentThread().getName() + " | ID: " + Thread.currentThread().getId());
        productoRepository.persist(producto);
    }
}