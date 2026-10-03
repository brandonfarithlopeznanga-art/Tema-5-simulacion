package taller;

import java.io.Serializable;

public class Vehiculo implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String marca;
    private String modelo;
    private int anio;
    private String placas;
    private Cliente propietario;

    public Vehiculo(
            int id,
            String marca,
            String modelo,
            int anio,
            String placas,
            Cliente propietario) {

        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.placas = placas;
        this.propietario = propietario;
    }

    public int getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAnio() {
        return anio;
    }

    public String getPlacas() {
        return placas;
    }

    public Cliente getPropietario() {
        return propietario;
    }

    @Override
    public String toString() {

        return id
                + " - "
                + marca
                + " "
                + modelo
                + " ("
                + placas
                + ")";
    }
}