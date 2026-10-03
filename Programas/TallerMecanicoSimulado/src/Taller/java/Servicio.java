package taller;

import java.io.Serializable;

public class Servicio implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nombre;
    private String complejidad;
    private int tiempoEstimado;
    private double costo;

    public Servicio(
            int id,
            String nombre,
            String complejidad,
            int tiempoEstimado,
            double costo) {

        this.id = id;
        this.nombre = nombre;
        this.complejidad = complejidad;
        this.tiempoEstimado = tiempoEstimado;
        this.costo = costo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getComplejidad() {
        return complejidad;
    }

    public int getTiempoEstimado() {
        return tiempoEstimado;
    }

    public double getCosto() {
        return costo;
    }

    @Override
    public String toString() {

        return nombre;
    }
}