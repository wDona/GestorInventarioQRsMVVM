package dev.wdona.gestorinventarioqr.data.repository;

import dev.wdona.gestorinventarioqr.data.datasource.local.impl.ProductoLocalDataSourceImpl;
import dev.wdona.gestorinventarioqr.data.datasource.remote.impl.ProductoRemoteDataSourceImpl;
import dev.wdona.gestorinventarioqr.domain.repository.ProductoRepository;
import dev.wdona.gestorinventarioqr.domain.model.Estanteria;
import dev.wdona.gestorinventarioqr.domain.model.Operacion;
import dev.wdona.gestorinventarioqr.domain.model.Producto;

public class ProductoRepositoryImpl implements ProductoRepository {
    ProductoRemoteDataSourceImpl remote;
    ProductoLocalDataSourceImpl local;
    OperacionRepositoryImpl registro;


    public ProductoRepositoryImpl(ProductoRemoteDataSourceImpl remote, ProductoLocalDataSourceImpl local) {
        this.remote = remote;
        this.local = local;
    }

    @Override
    public void addUndsProduct(Producto producto, int cantidad) {
        try {
            remote.addUndsProduct(producto, cantidad);
        } catch (Exception e) {
            registro.agregarOperacionPendiente(
                    new Operacion(
                         registro.getUltimoIdOperacionPendiente() + 1,
                            System.currentTimeMillis(),
                            "ADD",
                            producto.getId(),
                            producto.getEstanteria().getId(),
                            cantidad,
                            "PENDIENTE"
                    )
            );
            System.out.println("Error: " + e.getMessage());
        } finally {
            local.addUndsProduct(producto, cantidad);
        }
    }

    @Override
    public void removeUndsProduct(Producto producto, int cantidad) {
        try {
            remote.removeUndsProduct(producto, cantidad);
        } catch (Exception e) {
            registro.agregarOperacionPendiente(
                    new Operacion(
                            registro.getUltimoIdOperacionPendiente() + 1,
                            System.currentTimeMillis(),
                            "REMOVE",
                            producto.getId(),
                            producto.getEstanteria().getId(),
                            cantidad,
                            "PENDIENTE"
                    )
            );
            System.out.println("Error: " + e.getMessage());
        } finally {
            local.removeUndsProduct(producto, cantidad);
        }
    }

    @Override
    public void assignProductToEstanteria(Producto producto, Estanteria estanteria) {
        try {
            remote.assignProductToEstanteria(producto, estanteria);
        } catch (Exception e) {
            registro.agregarOperacionPendiente(
                    new Operacion(
                            registro.getUltimoIdOperacionPendiente() + 1,
                            System.currentTimeMillis(),
                            "MOVE",
                            producto.getId(),
                            estanteria.getId(),
                            producto.getCantidad(),
                            "PENDIENTE"
                    )
            );
            System.out.println("Error: " + e.getMessage());
        } finally {
            local.assignProductToEstanteria(producto, estanteria);
        }
    }

    @Override
    public Producto getProductoById(Long id) {
        Producto producto = null;
        try {
            producto = remote.getProductoById(id);
            if (producto != null) {
                return producto;
            } else {
                System.out.println("Producto null");
            }
        } catch (Exception e) {
            try {
                producto = local.getProductoById(id);
            } catch (Exception e1) {
                System.out.println("Error: " + e1.getMessage());
            }
        } finally {
            return local.getProductoById(id);
        }
    }
}
