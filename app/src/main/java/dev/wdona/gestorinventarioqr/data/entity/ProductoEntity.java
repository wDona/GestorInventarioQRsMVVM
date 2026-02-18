package dev.wdona.gestorinventarioqr.data.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "Producto",
        foreignKeys = @ForeignKey(
                entity = EstanteriaEntity.class,
                parentColumns = "id",
                childColumns = "FK_estanteriaId",
                onDelete = ForeignKey.SET_NULL
        ),
        indices = @Index("FK_estanteriaId")
)
public class ProductoEntity {
    @PrimaryKey
    private Long id;
    private String nombre;
    private double precio;
    private int cantidad;
    private Long FK_estanteriaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Long getFK_estanteriaId() {
        return FK_estanteriaId;
    }

    public void setFK_estanteriaId(Long FK_estanteriaId) {
        this.FK_estanteriaId = FK_estanteriaId;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
