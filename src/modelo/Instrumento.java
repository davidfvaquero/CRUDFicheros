package modelo;

import java.io.Serializable;

public class Instrumento implements Serializable {
    private String nombre, tipo, origen, material;
    private double precio;

    public Instrumento(String nombre, String tipo, String origen, String material, double precio) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.origen = origen;
        this.material = material;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Instrumento: " +
                "nombre = " + nombre + ", tipo = " + tipo +
                ", origen = " + origen + ", material = " + material +
                ", precio = " + precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
