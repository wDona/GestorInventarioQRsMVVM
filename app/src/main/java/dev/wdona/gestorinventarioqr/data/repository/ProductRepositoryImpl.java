package dev.wdona.gestorinventarioqr.data.repository;

import dev.wdona.gestorinventarioqr.data.datasource.LocalProductDataSource;
import dev.wdona.gestorinventarioqr.data.datasource.RemoteProductDataSource;
import dev.wdona.gestorinventarioqr.domain.model.Estanteria;
import dev.wdona.gestorinventarioqr.domain.model.Producto;

public class ProductRepositoryImpl implements ProductRepository {
    RemoteProductDataSource remote;
    LocalProductDataSource local;

    ProductRepositoryImpl(RemoteProductDataSource remote, LocalProductDataSource local) {
        this.remote = remote;
        this.local = local;
    }

    @Override
    public void addUndsProduct(Producto producto) {
        try {
            remote.addUndsProduct(producto);
        } catch (Exception e) {
            local.addPendienteProduct(producto);
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void removeUndsProduct(Producto producto) {
        try {
            remote.removeUndsProduct(producto);
        } catch (Exception e) {
            local.addPendienteProduct(producto);
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void assignProductToEstanteria(Producto producto, Estanteria estanteria) {
        try {
            remote.assignProductToEstanteria(producto, estanteria);
        } catch (Exception e) {
            local.addPendienteProduct(producto);
            System.out.println("Error: " + e.getMessage());
        }
    }
}
