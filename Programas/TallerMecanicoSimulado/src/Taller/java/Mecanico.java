package taller;

import java.io.Serializable;

public class Mecanico implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nombre;
    private String especialidad;
    private boolean ocupado;
    private Vehiculo vehiculoActual;
    private int tiempoRestante;

    public Mecanico(
            int id,
            String nombre,
            String especialidad) {

        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.ocupado = false;
        this.vehiculoActual = null;
        this.tiempoRestante = 0;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public Vehiculo getVehiculoActual() {
        return vehiculoActual;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public void asignarVehiculo(
            Vehiculo vehiculo) {

        this.vehiculoActual = vehiculo;
        this.ocupado = true;
        this.tiempoRestante = 0;
    }

    public void asignarVehiculo(
            Vehiculo vehiculo,
            int tiempoRestante) {

        this.vehiculoActual = vehiculo;
        this.ocupado = true;
        this.tiempoRestante = tiempoRestante;
    }

    public void actualizarTiempo(
            int tiempo) {

        this.tiempoRestante = tiempo;

        if (this.tiempoRestante <= 0) {
            liberar();
        }
    }

    public void liberar() {

        this.vehiculoActual = null;
        this.ocupado = false;
        this.tiempoRestante = 0;
    }

    @Override
    public String toString() {

        return nombre
                + " - "
                + especialidad
                + " - "
                + (
                ocupado
                        ? "OCUPADO"
                        : "DISPONIBLE"
        );
    }
}