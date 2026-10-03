package taller;

import java.io.Serializable;

public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nombre;
    private String telefono;
    private String correo;

    public Cliente(
            int id,
            String nombre,
            String telefono,
            String correo) {

        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    @Override
    public String toString() {

        return nombre
                + " - Tel: "
                + telefono
                + " - "
                + correo;
    }
}