package dev.wdona.gestorinventarioqr.data.api.impl;

import android.provider.ContactsContract;

import dev.wdona.gestorinventarioqr.data.api.ProductoApi;
import dev.wdona.gestorinventarioqr.domain.model.Estanteria;
import dev.wdona.gestorinventarioqr.domain.model.Producto;
import dev.wdona.gestorinventarioqr.mock.DatabaseController;
import dev.wdona.gestorinventarioqr.mock.MockDatabaseOperations;

public class ProductoApiImpl implements ProductoApi {
    MockDatabaseOperations mockDatabaseOperations = new DatabaseController();
    @Override
    public void addUndsProduct(Producto producto, int cantidad) {
        if (producto == null || cantidad <= 0) {
            System.out.println("Error, cantidad no válida o producto nulo");
            return;
        }

        try {
            mockDatabaseOperations.addUndsProduct(producto, cantidad);
        } catch (Exception e) {
            System.out.println("Error al agregar unidades del producto: " + e.getMessage());
        }
    }

    @Override
    public void removeUndsProduct(Producto producto, int cantidad) {
        if (producto == null || cantidad <= 0 || producto.getCantidad() - cantidad < 0) {
            System.out.println("Error, cantidad no válida o producto nulo");
            return;
        }

        try {
            mockDatabaseOperations.removeUndsProduct(producto, cantidad);
        } catch (Exception e) {
            System.out.println("Error al agregar unidades del producto: " + e.getMessage());
        }
    }

    @Override
    public void assignProductToEstanteria(Producto producto, Estanteria estanteria) {
        if (producto == null || estanteria == null) {
            System.out.println("Error, producto o estanteria nulo");
            return;
        }

        try {
            mockDatabaseOperations.assignProductToEstanteria(producto, estanteria);
        } catch (Exception e) {
            System.out.println("Error al asignar producto a estanteria: " + e.getMessage());
        }
    }
}
