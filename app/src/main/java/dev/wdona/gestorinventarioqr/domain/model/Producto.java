package dev.wdona.gestorinventarioqr.domain.model;

public class Producto {
    private Long id;
    private String nombre;
    private double precio;
    private int cantidad;

    private Estanteria estanteria;

    public Producto(Long id, String nombre, double precio, int cantidad, Estanteria estanteria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.estanteria = estanteria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Estanteria getEstanteria() {
        return estanteria;
    }

    public void setEstanteria(Estanteria estanteria) {
        this.estanteria = estanteria;
    }

    public Long getId() {
        return this.id;
    }
}
