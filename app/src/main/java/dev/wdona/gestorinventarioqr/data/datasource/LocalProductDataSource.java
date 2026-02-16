package dev.wdona.gestorinventarioqr.data.datasource;

import dev.wdona.gestorinventarioqr.data.db.ProductDao;
import dev.wdona.gestorinventarioqr.domain.model.Estanteria;
import dev.wdona.gestorinventarioqr.domain.model.Producto;

public class LocalProductDataSource implements ProductDataSource {
    ProductDao dao;

    LocalProductDataSource(ProductDao dao) {
        this.dao  = dao;
    }

    @Override
    public void addUndsProduct(Producto producto) {
        try {
            // Actualizar el producto y el producto de la lista de la estanteria
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void removeUndsProduct(Producto producto) {
        try {
            // Actualizar el producto y el producto de la lista de la estanteria
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void assignProductToEstanteria(Producto producto, Estanteria estanteria) {
        try {
            // Actualizar el producto y el producto de la lista de la estanteria
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void addPendienteProduct(Producto producto) {
        try {
            // Agregar el producto a la lista de pendientes
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
