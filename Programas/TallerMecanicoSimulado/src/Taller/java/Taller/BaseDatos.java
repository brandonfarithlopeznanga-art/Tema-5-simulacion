package taller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDatos {

    private static final String ARCHIVO =
            "taller.dat";

    private List<Cliente> clientes;
    private List<Vehiculo> vehiculos;
    private List<Servicio> servicios;
    private List<Mecanico> mecanicos;
    private List<OrdenServicio> ordenes;

    public BaseDatos() {

        clientes = new ArrayList<>();
        vehiculos = new ArrayList<>();
        servicios = new ArrayList<>();
        mecanicos = new ArrayList<>();
        ordenes = new ArrayList<>();

        cargar();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public List<Mecanico> getMecanicos() {
        return mecanicos;
    }

    public List<OrdenServicio> getOrdenes() {
        return ordenes;
    }

    public void guardar() {

        try (
                ObjectOutputStream salida =
                        new ObjectOutputStream(
                                new FileOutputStream(ARCHIVO)
                        )
        ) {

            salida.writeObject(clientes);
            salida.writeObject(vehiculos);
            salida.writeObject(servicios);
            salida.writeObject(mecanicos);
            salida.writeObject(ordenes);

        } catch (IOException e) {

            System.out.println(
                    "No se pudo guardar la información: "
                            + e.getMessage()
            );
        }
    }

    @SuppressWarnings("unchecked")
    private void cargar() {

        File archivo =
                new File(ARCHIVO);

        if (!archivo.exists()) {

            return;
        }

        try (
                ObjectInputStream entrada =
                        new ObjectInputStream(
                                new FileInputStream(archivo)
                        )
        ) {

            clientes =
                    (List<Cliente>)
                            entrada.readObject();

            vehiculos =
                    (List<Vehiculo>)
                            entrada.readObject();

            servicios =
                    (List<Servicio>)
                            entrada.readObject();

            mecanicos =
                    (List<Mecanico>)
                            entrada.readObject();

            ordenes =
                    (List<OrdenServicio>)
                            entrada.readObject();

        } catch (
                IOException |
                ClassNotFoundException e
        ) {

            System.out.println(
                    "No se pudo cargar la información: "
                            + e.getMessage()
            );

            clientes = new ArrayList<>();
            vehiculos = new ArrayList<>();
            servicios = new ArrayList<>();
            mecanicos = new ArrayList<>();
            ordenes = new ArrayList<>();
        }
    }
}