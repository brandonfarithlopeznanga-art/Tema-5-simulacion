package taller;

import java.io.Serializable;

public class OrdenServicio implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private Servicio servicio;
    private Mecanico mecanico;
    private String estado;

    private boolean clienteEspera;
    private int tiempoEsperaEstimado;
    private String horaEntrega;

    public OrdenServicio(
            int id,
            Cliente cliente,
            Vehiculo vehiculo,
            Servicio servicio,
            Mecanico mecanico) {

        this.id = id;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.servicio = servicio;
        this.mecanico = mecanico;

        this.estado = "En servicio";

        this.clienteEspera = false;
        this.tiempoEsperaEstimado = 0;
        this.horaEntrega = "";
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public Mecanico getMecanico() {
        return mecanico;
    }

    public String getEstado() {
        return estado;
    }

    public boolean isClienteEspera() {
        return clienteEspera;
    }

    public int getTiempoEsperaEstimado() {
        return tiempoEsperaEstimado;
    }

    public String getHoraEntrega() {
        return horaEntrega;
    }

    public void setEstado(
            String estado) {

        this.estado = estado;
    }

    public void setMecanico(
            Mecanico mecanico) {

        this.mecanico = mecanico;
    }

    public void setClienteEspera(
            boolean clienteEspera) {

        this.clienteEspera = clienteEspera;
    }

    public void setTiempoEsperaEstimado(
            int tiempo) {

        this.tiempoEsperaEstimado = tiempo;
    }

    public void setHoraEntrega(
            String horaEntrega) {

        this.horaEntrega = horaEntrega;
    }

    @Override
    public String toString() {

        return "Orden #"
                + id
                + " - "
                + vehiculo
                + " - "
                + estado;
    }
}