package dev.wdona.gestorinventarioqr.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import dev.wdona.gestorinventarioqr.data.entity.EstanteriaEntity;
import dev.wdona.gestorinventarioqr.data.entity.ProductoEntity;
import dev.wdona.gestorinventarioqr.domain.model.Estanteria;
import dev.wdona.gestorinventarioqr.domain.model.Producto;

@Dao
public interface ProductoDao {
    @Query("UPDATE Producto SET cantidad = cantidad + :cantidad WHERE id = :productoId")
    void addUndsProduct(Long productoId, int cantidad);

    @Query("UPDATE Producto SET cantidad = cantidad - :cantidad WHERE id = :productoId")
    void removeUndsProduct(Long productoId, int cantidad);
    @Query("UPDATE Producto SET FK_estanteriaId = :estanteriaId WHERE id = :productoId")
    void assignProductToEstanteria(Long productoId, Long estanteriaId);

    @Query("SELECT * FROM Producto WHERE id = :id")
    ProductoEntity getProductoById(Long id);

}
