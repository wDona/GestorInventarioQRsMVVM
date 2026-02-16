package dev.wdona.gestorinventarioqr.data.datasource;

import dev.wdona.gestorinventarioqr.data.api.ProductApi;
import dev.wdona.gestorinventarioqr.domain.model.Estanteria;
import dev.wdona.gestorinventarioqr.domain.model.Producto;

public class RemoteProductDataSource implements ProductDataSource {
    ProductApi api;
    RemoteProductDataSource(ProductApi api) {
        this.api = api;
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
}
