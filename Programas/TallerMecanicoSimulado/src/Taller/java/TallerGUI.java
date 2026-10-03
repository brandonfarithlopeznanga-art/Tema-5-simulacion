package taller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TallerGUI extends JFrame {

    // =========================================================
    // BASE DE DATOS
    // =========================================================

    private final BaseDatos baseDatos;

    // =========================================================
    // COLORES
    // =========================================================

    private final Color AZUL = new Color(25, 55, 90);
    private final Color AZUL2 = new Color(39, 92, 140);
    private final Color AZUL3 = new Color(225, 238, 250);

    private final Color VERDE = new Color(35, 150, 90);
    private final Color VERDE_CLARO = new Color(229, 246, 236);

    private final Color ROJO = new Color(190, 55, 55);
    private final Color ROJO_CLARO = new Color(252, 232, 232);

    private final Color NARANJA = new Color(225, 135, 35);
    private final Color MORADO = new Color(117, 76, 172);

    private final Color FONDO = new Color(243, 246, 249);
    private final Color TEXTO = new Color(45, 55, 65);
    private final Color GRIS = new Color(110, 120, 130);

    // =========================================================
    // CONTENEDORES
    // =========================================================

    private JPanel contenido;

    // =========================================================
    // ESTADÍSTICAS DEL DASHBOARD
    // =========================================================

    private JLabel lblClientes;
    private JLabel lblVehiculos;
    private JLabel lblOrdenes;
    private JLabel lblMecanicos;

    // =========================================================
    // CONTADORES
    // =========================================================

    private int contadorCliente;
    private int contadorVehiculo;
    private int contadorOrden;

    // =========================================================
    // COMPONENTES DE ORDEN
    // =========================================================

    private JComboBox<Vehiculo> comboVehiculo;
    private JComboBox<Servicio> comboServicio;
    private JComboBox<Mecanico> comboMecanico;

    private JComboBox<OrdenServicio> comboSimulacion;
    private JComboBox<OrdenServicio> comboEntrega;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public TallerGUI() {

        baseDatos = new BaseDatos();

        prepararDatos();

        actualizarContadores();

        setTitle(
                "Taller Mecánico | Sistema de Gestión y Simulación"
        );

        setSize(1350, 800);

        setMinimumSize(
                new Dimension(1150, 700)
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        construirInterfaz();

        mostrarInicio();
    }

    // =========================================================
    // DATOS INICIALES
    // =========================================================

    private void prepararDatos() {

        if (baseDatos.getServicios().isEmpty()) {

            baseDatos.getServicios().add(
                    new Servicio(
                            1,
                            "Cambio de aceite",
                            "Baja",
                            30,
                            650
                    )
            );

            baseDatos.getServicios().add(
                    new Servicio(
                            2,
                            "Frenos",
                            "Media",
                            60,
                            1200
                    )
            );

            baseDatos.getServicios().add(
                    new Servicio(
                            3,
                            "Trabajo de motor",
                            "Alta",
                            120,
                            3000
                    )
            );
        }

        if (baseDatos.getMecanicos().isEmpty()) {

            baseDatos.getMecanicos().add(
                    new Mecanico(
                            1,
                            "Carlos Hernández",
                            "Servicio general"
                    )
            );

            baseDatos.getMecanicos().add(
                    new Mecanico(
                            2,
                            "Miguel López",
                            "Frenos"
                    )
            );

            baseDatos.getMecanicos().add(
                    new Mecanico(
                            3,
                            "José Martínez",
                            "Motor"
                    )
            );
        }

        baseDatos.guardar();
    }

    private void actualizarContadores() {

        contadorCliente =
                baseDatos.getClientes().size() + 1;

        contadorVehiculo =
                baseDatos.getVehiculos().size() + 1;

        contadorOrden =
                baseDatos.getOrdenes().size() + 1;
    }

    // =========================================================
    // INTERFAZ PRINCIPAL
    // =========================================================

    private void construirInterfaz() {

        getContentPane().setLayout(
                new BorderLayout()
        );

        getContentPane().setBackground(
                FONDO
        );

        add(
                crearMenu(),
                BorderLayout.WEST
        );

        contenido =
                new JPanel(
                        new BorderLayout()
                );

        contenido.setBackground(
                FONDO
        );

        contenido.setBorder(
                new EmptyBorder(
                        25,
                        30,
                        25,
                        30
                )
        );

        add(
                contenido,
                BorderLayout.CENTER
        );
    }

    // =========================================================
    // MENÚ
    // =========================================================

    private JPanel crearMenu() {

        JPanel menu = new JPanel();

        menu.setPreferredSize(
                new Dimension(
                        255,
                        0
                )
        );

        menu.setBackground(
                AZUL
        );

        menu.setLayout(
                new BoxLayout(
                        menu,
                        BoxLayout.Y_AXIS
                )
        );

        menu.setBorder(
                new EmptyBorder(
                        25,
                        18,
                        20,
                        18
                )
        );

        JLabel logo =
                new JLabel("AUTO");
        logo.setForeground(Color.WHITE);
        logo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );
        logo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel subtitulo =
                new JLabel(
                        "TALLER MECÁNICO"
                );

        subtitulo.setForeground(
                new Color(
                        190,
                        210,
                        230
                )
        );

        subtitulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        11
                )
        );

        subtitulo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        menu.add(logo);
        menu.add(
                Box.createVerticalStrut(3)
        );
        menu.add(subtitulo);

        menu.add(
                Box.createVerticalStrut(30)
        );

        agregarTituloMenu(
                menu,
                "PRINCIPAL"
        );

        menu.add(
                botonMenu(
                        "⌂   Inicio",
                        AZUL2,
                        e -> mostrarInicio()
                )
        );

        menu.add(
                Box.createVerticalStrut(7)
        );

        agregarTituloMenu(
                menu,
                "REGISTROS"
        );

        menu.add(
                botonMenu(
                        "👤   Registrar cliente",
                        VERDE,
                        e -> mostrarRegistrarCliente()
                )
        );

        menu.add(
                Box.createVerticalStrut(7)
        );

        menu.add(
                botonMenu(
                        "🚗   Registrar vehículo",
                        VERDE,
                        e -> mostrarRegistrarVehiculo()
                )
        );

        menu.add(
                Box.createVerticalStrut(7)
        );

        menu.add(
                botonMenu(
                        "🗑   Eliminar vehículo",
                        ROJO,
                        e -> eliminarVehiculo()
                )
        );

        menu.add(
                Box.createVerticalStrut(7)
        );

        agregarTituloMenu(
                menu,
                "PROCESO DEL TALLER"
        );

        menu.add(
                botonMenu(
                        "⚙   Nueva orden",
                        NARANJA,
                        e -> mostrarNuevaOrden()
                )
        );

        menu.add(
                Box.createVerticalStrut(7)
        );

        menu.add(
                botonMenu(
                        "▶   Simulación",
                        MORADO,
                        e -> mostrarSimulacion()
                )
        );

        menu.add(
                Box.createVerticalStrut(7)
        );

        menu.add(
                botonMenu(
                        "✓   Entrega",
                        VERDE,
                        e -> mostrarEntrega()
                )
        );

        menu.add(
                Box.createVerticalStrut(7)
        );

        menu.add(
                botonMenu(
                        "📋   Historial",
                        AZUL2,
                        e -> mostrarHistorial()
                )
        );

        menu.add(
                Box.createVerticalStrut(7)
        );

        agregarTituloMenu(
                menu,
                "CONTROL"
        );

        menu.add(
                botonMenu(
                        "🔧   Mecánicos",
                        new Color(70, 85, 100),
                        e -> mostrarMecanicos()
                )
        );

        menu.add(
                Box.createVerticalGlue()
        );

        JLabel estado =
                new JLabel(
                        "● Sistema activo"
                );

        estado.setForeground(
                new Color(
                        120,
                        220,
                        160
                )
        );

        estado.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        estado.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        menu.add(estado);

        menu.add(
                Box.createVerticalStrut(12)
        );

        menu.add(
                botonMenu(
                        "X   Salir",
                        new Color(
                                100,
                                110,
                                120
                        ),
                        e -> {

                            baseDatos.guardar();

                            System.exit(0);
                        }
                )
        );

        return menu;
    }

    private void agregarTituloMenu(
            JPanel menu,
            String texto) {

        JLabel titulo =
                new JLabel(
                        texto
                );

        titulo.setForeground(
                new Color(
                        150,
                        175,
                        200
                )
        );

        titulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        10
                )
        );

        titulo.setBorder(
                new EmptyBorder(
                        5,
                        8,
                        8,
                        0
                )
        );

        titulo.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        menu.add(titulo);
    }

    private JButton botonMenu(
            String texto,
            Color color,
            java.awt.event.ActionListener accion) {

        RoundedButton boton =
                new RoundedButton(
                        texto,
                        color
                );

        boton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        boton.addActionListener(
                accion
        );

        return boton;
    }

    // =========================================================
    // INICIO
    // =========================================================

    private void mostrarInicio() {

        contenido.removeAll();

        JPanel principal =
                new JPanel();

        principal.setOpaque(false);

        principal.setLayout(
                new BoxLayout(
                        principal,
                        BoxLayout.Y_AXIS
                )
        );

        principal.add(
                encabezado(
                        "Panel principal",
                        "Control general del taller"
                )
        );

        principal.add(
                Box.createVerticalStrut(20)
        );

        JPanel tarjetas =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                15,
                                15
                        )
                );

        tarjetas.setOpaque(false);

        lblClientes =
                new JLabel(
                        String.valueOf(
                                baseDatos
                                        .getClientes()
                                        .size()
                        )
                );

        lblVehiculos =
                new JLabel(
                        String.valueOf(
                                baseDatos
                                        .getVehiculos()
                                        .size()
                        )
                );

        lblOrdenes =
                new JLabel(
                        String.valueOf(
                                baseDatos
                                        .getOrdenes()
                                        .size()
                        )
                );

        lblMecanicos =
                new JLabel(
                        String.valueOf(
                                contarDisponibles()
                        )
                );

        tarjetas.add(
                tarjetaEstadistica(
                        "CLIENTES",
                        lblClientes,
                        VERDE
                )
        );

        tarjetas.add(
                tarjetaEstadistica(
                        "VEHÍCULOS",
                        lblVehiculos,
                        AZUL2
                )
        );

        tarjetas.add(
                tarjetaEstadistica(
                        "ÓRDENES",
                        lblOrdenes,
                        NARANJA
                )
        );

        tarjetas.add(
                tarjetaEstadistica(
                        "MECÁNICOS DISPONIBLES",
                        lblMecanicos,
                        MORADO
                )
        );

        principal.add(tarjetas);

        principal.add(
                Box.createVerticalStrut(20)
        );

        principal.add(
                crearResumenProceso()
        );

        principal.add(
                Box.createVerticalStrut(20)
        );

        principal.add(
                crearPanelMecanicosInicio()
        );

        JScrollPane scroll =
                new JScrollPane(
                        principal
                );

        scroll.setBorder(null);
        scroll.getVerticalScrollBar()
                .setUnitIncrement(15);

        contenido.add(
                scroll,
                BorderLayout.CENTER
        );

        actualizarPantalla();
    }

    private JPanel encabezado(
            String titulo,
            String descripcion) {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setOpaque(false);

        JLabel t =
                new JLabel(
                        titulo
                );

        t.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        t.setForeground(
                AZUL
        );

        JLabel d =
                new JLabel(
                        descripcion
                );

        d.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        d.setForeground(
                GRIS
        );

        JPanel textos =
                new JPanel();

        textos.setOpaque(false);

        textos.setLayout(
                new BoxLayout(
                        textos,
                        BoxLayout.Y_AXIS
                )
        );

        textos.add(t);
        textos.add(
                Box.createVerticalStrut(4)
        );
        textos.add(d);

        JLabel fecha =
                new JLabel(
                        new SimpleDateFormat(
                                "dd/MM/yyyy HH:mm"
                        ).format(
                                new Date()
                        )
                );

        fecha.setForeground(
                GRIS
        );

        panel.add(
                textos,
                BorderLayout.WEST
        );

        panel.add(
                fecha,
                BorderLayout.EAST
        );

        return panel;
    }

    private JPanel tarjetaEstadistica(
            String titulo,
            JLabel numero,
            Color color) {

        RoundedPanel panel =
                new RoundedPanel(
                        Color.WHITE
                );

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setBorder(
                new EmptyBorder(
                        18,
                        20,
                        18,
                        20
                )
        );

        JLabel t =
                new JLabel(
                        titulo
                );

        t.setForeground(
                GRIS
        );

        t.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        11
                )
        );

        numero.setForeground(
                color
        );

        numero.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        32
                )
        );

        panel.add(t);

        panel.add(
                Box.createVerticalStrut(8)
        );

        panel.add(numero);

        return panel;
    }

    private JPanel crearResumenProceso() {

        RoundedPanel panel =
                new RoundedPanel(
                        Color.WHITE
                );

        panel.setLayout(
                new BorderLayout()
        );

        panel.setBorder(
                new EmptyBorder(
                        20,
                        25,
                        20,
                        25
                )
        );

        JLabel titulo =
                new JLabel(
                        "Flujo de atención"
                );

        titulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        titulo.setForeground(
                AZUL
        );

        panel.add(
                titulo,
                BorderLayout.NORTH
        );

        JPanel pasos =
                new JPanel(
                        new GridLayout(
                                1,
                                6,
                                8,
                                8
                        )
                );

        pasos.setOpaque(false);

        String[] nombres = {
                "Cliente",
                "Vehículo",
                "Orden",
                "Servicio",
                "Simulación",
                "Entrega"
        };

        for (int i = 0; i < nombres.length; i++) {

            JPanel paso =
                    new JPanel();

            paso.setBackground(
                    i == 0
                            ? VERDE_CLARO
                            : AZUL3
            );

            paso.setBorder(
                    new EmptyBorder(
                            15,
                            8,
                            15,
                            8
                    )
            );

            JLabel numero =
                    new JLabel(
                            String.valueOf(
                                    i + 1
                            ),
                            SwingConstants.CENTER
                    );

            numero.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            20
                    )
            );

            numero.setForeground(
                    i == 0
                            ? VERDE
                            : AZUL2
            );

            JLabel nombre =
                    new JLabel(
                            nombres[i],
                            SwingConstants.CENTER
                    );

            nombre.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            12
                    )
            );

            paso.setLayout(
                    new BoxLayout(
                            paso,
                            BoxLayout.Y_AXIS
                    )
            );

            numero.setAlignmentX(
                    Component.CENTER_ALIGNMENT
            );

            nombre.setAlignmentX(
                    Component.CENTER_ALIGNMENT
            );

            paso.add(numero);

            paso.add(
                    Box.createVerticalStrut(6)
            );

            paso.add(nombre);

            pasos.add(paso);
        }

        panel.add(
                pasos,
                BorderLayout.CENTER
        );

        return panel;
    }

    // =========================================================
    // REGISTRAR CLIENTE
    // =========================================================

    private void mostrarRegistrarCliente() {

        contenido.removeAll();

        contenido.add(
                encabezado(
                        "Registrar cliente",
                        "Paso 1 · Datos del propietario"
                ),
                BorderLayout.NORTH
        );

        JPanel formulario =
                formularioBase();

        JTextField nombre =
                campoTexto();

        JTextField telefono =
                campoTexto();

        JTextField correo =
                campoTexto();

        agregarCampo(
                formulario,
                0,
                "Nombre completo",
                nombre
        );

        agregarCampo(
                formulario,
                1,
                "Teléfono",
                telefono
        );

        agregarCampo(
                formulario,
                2,
                "Correo electrónico",
                correo
        );

        JLabel ayuda =
                new JLabel(
                        "<html>"
                                + "<b>Siguiente paso:</b> después de registrar "
                                + "al cliente podrás asociarle uno o más vehículos."
                                + "</html>"
                );

        ayuda.setForeground(
                GRIS
        );

        GridBagConstraints gbc =
                constraints(
                        3
                );

        gbc.gridwidth = 2;

        formulario.add(
                ayuda,
                gbc
        );

        RoundedButton guardar =
                new RoundedButton(
                        "✓  Guardar cliente",
                        VERDE
                );

        gbc =
                constraints(
                        4
                );

        gbc.gridwidth = 2;

        formulario.add(
                guardar,
                gbc
        );

        guardar.addActionListener(
                e -> {

                    if (nombre.getText()
                            .trim()
                            .isEmpty()) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Escribe el nombre del cliente.",
                                "Dato requerido",
                                JOptionPane.WARNING_MESSAGE
                        );

                        return;
                    }

                    Cliente cliente =
                            new Cliente(
                                    contadorCliente++,
                                    nombre.getText()
                                            .trim(),
                                    telefono.getText()
                                            .trim(),
                                    correo.getText()
                                            .trim()
                            );

                    baseDatos
                            .getClientes()
                            .add(cliente);

                    baseDatos.guardar();

                    JOptionPane.showMessageDialog(
                            this,
                            "Cliente registrado correctamente.\n\n"
                                    + "Cliente: "
                                    + cliente.getNombre(),
                            "Registro exitoso",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    mostrarRegistrarVehiculo();
                }
        );

        contenido.add(
                formulario,
                BorderLayout.CENTER
        );

        actualizarPantalla();
    }

    // =========================================================
    // REGISTRAR VEHÍCULO
    // =========================================================

    private void mostrarRegistrarVehiculo() {

        contenido.removeAll();

        contenido.add(
                encabezado(
                        "Registrar vehículo",
                        "Paso 2 · Asociar vehículo con un cliente"
                ),
                BorderLayout.NORTH
        );

        if (baseDatos.getClientes().isEmpty()) {

            JPanel aviso =
                    crearAviso(
                            "Primero debes registrar un cliente.",
                            ROJO
                    );

            RoundedButton irCliente =
                    new RoundedButton(
                            "Registrar cliente",
                            VERDE
                    );

            aviso.add(
                    Box.createVerticalStrut(15)
            );

            aviso.add(irCliente);

            irCliente.addActionListener(
                    e -> mostrarRegistrarCliente()
            );

            contenido.add(
                    aviso,
                    BorderLayout.CENTER
            );

            actualizarPantalla();

            return;
        }

        JPanel formulario =
                formularioBase();

        JComboBox<Cliente> clientes =
                new JComboBox<>();

        for (Cliente cliente :
                baseDatos.getClientes()) {

            clientes.addItem(
                    cliente
            );
        }

        JComboBox<String> marcas =
                new JComboBox<>(
                        new String[]{
                                "Toyota",
                                "Nissan",
                                "Chevrolet",
                                "Ford",
                                "Honda",
                                "Volkswagen",
                                "Mazda",
                                "Kia",
                                "Hyundai",
                                "BMW",
                                "Mercedes-Benz",
                                "Audi",
                                "Otra"
                        }
                );

        JTextField modelo =
                campoTexto();

        JTextField anio =
                campoTexto();

        JTextField placas =
                campoTexto();

        agregarCampo(
                formulario,
                0,
                "Cliente propietario",
                clientes
        );

        agregarCampo(
                formulario,
                1,
                "Marca",
                marcas
        );

        agregarCampo(
                formulario,
                2,
                "Modelo",
                modelo
        );

        agregarCampo(
                formulario,
                3,
                "Año",
                anio
        );

        agregarCampo(
                formulario,
                4,
                "Placas",
                placas
        );

        RoundedButton guardar =
                new RoundedButton(
                        "🚗  Registrar vehículo",
                        VERDE
                );

        GridBagConstraints gbc =
                constraints(
                        5
                );

        gbc.gridwidth = 2;

        formulario.add(
                guardar,
                gbc
        );

        guardar.addActionListener(
                e -> {

                    try {

                        if (modelo.getText()
                                .trim()
                                .isEmpty()
                                || placas.getText()
                                .trim()
                                .isEmpty()
                                || anio.getText()
                                .trim()
                                .isEmpty()) {

                            JOptionPane.showMessageDialog(
                                    this,
                                    "Completa los datos del vehículo.",
                                    "Datos incompletos",
                                    JOptionPane.WARNING_MESSAGE
                            );

                            return;
                        }

                        int año =
                                Integer.parseInt(
                                        anio.getText()
                                                .trim()
                                );

                        Cliente cliente =
                                (Cliente)
                                        clientes
                                                .getSelectedItem();

                        String marca =
                                marcas
                                        .getSelectedItem()
                                        .toString();

                        Vehiculo vehiculo =
                                new Vehiculo(
                                        contadorVehiculo++,
                                        marca,
                                        modelo.getText()
                                                .trim(),
                                        año,
                                        placas.getText()
                                                .trim()
                                                .toUpperCase(),
                                        cliente
                                );

                        baseDatos
                                .getVehiculos()
                                .add(vehiculo);

                        baseDatos.guardar();

                        JOptionPane.showMessageDialog(
                                this,
                                "Vehículo registrado correctamente.\n\n"
                                        + marca
                                        + " "
                                        + vehiculo.getModelo()
                                        + "\nPlacas: "
                                        + vehiculo.getPlacas(),
                                "Registro exitoso",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                        mostrarNuevaOrden();

                    } catch (
                            NumberFormatException ex) {

                        JOptionPane.showMessageDialog(
                                this,
                                "El año debe ser numérico.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
        );

        contenido.add(
                formulario,
                BorderLayout.CENTER
        );

        actualizarPantalla();
    }

    // =========================================================
    // ELIMINAR VEHÍCULO
    // =========================================================

    private void eliminarVehiculo() {

        if (baseDatos
                .getVehiculos()
                .isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No existen vehículos registrados.",
                    "Eliminar vehículo",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }

        JComboBox<Vehiculo> combo =
                new JComboBox<>();

        for (Vehiculo v :
                baseDatos.getVehiculos()) {

            combo.addItem(v);
        }

        JPanel panel =
                new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.add(
                new JLabel(
                        "Selecciona el vehículo:"
                )
        );

        panel.add(
                Box.createVerticalStrut(10)
        );

        panel.add(combo);

        int resultado =
                JOptionPane.showConfirmDialog(
                        this,
                        panel,
                        "Eliminar vehículo",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

        if (resultado !=
                JOptionPane.OK_OPTION) {

            return;
        }

        Vehiculo seleccionado =
                (Vehiculo)
                        combo.getSelectedItem();

        if (seleccionado == null) {
            return;
        }

        for (Mecanico mecanico :
                baseDatos.getMecanicos()) {

            if (mecanico.isOcupado()
                    && mecanico.getVehiculoActual()
                    == seleccionado) {

                JOptionPane.showMessageDialog(
                        this,
                        "No puedes eliminar este vehículo porque "
                                + "se encuentra actualmente en servicio.",
                        "Vehículo ocupado",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }
        }

        int confirmar =
                JOptionPane.showConfirmDialog(
                        this,
                        "¿Deseas eliminar este vehículo?\n\n"
                                + seleccionado.getMarca()
                                + " "
                                + seleccionado.getModelo()
                                + "\nPlacas: "
                                + seleccionado.getPlacas(),
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

        if (confirmar !=
                JOptionPane.YES_OPTION) {

            return;
        }

        baseDatos
                .getVehiculos()
                .remove(seleccionado);

        List<OrdenServicio> ordenesEliminar =
                new ArrayList<>();

        for (OrdenServicio orden :
                baseDatos.getOrdenes()) {

            if (orden.getVehiculo()
                    == seleccionado) {

                ordenesEliminar.add(
                        orden
                );
            }
        }

        baseDatos
                .getOrdenes()
                .removeAll(
                        ordenesEliminar
                );

        baseDatos.guardar();

        actualizarContadores();

        JOptionPane.showMessageDialog(
                this,
                "Vehículo eliminado correctamente.",
                "Operación completada",
                JOptionPane.INFORMATION_MESSAGE
        );

        mostrarInicio();
    }

    // =========================================================
    // NUEVA ORDEN
    // =========================================================

    private void mostrarNuevaOrden() {

        contenido.removeAll();

        contenido.add(
                encabezado(
                        "Nueva orden de servicio",
                        "Paso 3 · Selección del servicio y mecánico"
                ),
                BorderLayout.NORTH
        );

        if (baseDatos.getVehiculos().isEmpty()) {

            contenido.add(
                    crearAviso(
                            "Registra primero un vehículo.",
                            NARANJA
                    ),
                    BorderLayout.CENTER
            );

            actualizarPantalla();

            return;
        }

        JPanel formulario =
                formularioBase();

        comboVehiculo =
                new JComboBox<>();

        for (Vehiculo v :
                baseDatos.getVehiculos()) {

            comboVehiculo.addItem(v);
        }

        comboServicio =
                new JComboBox<>();

        for (Servicio s :
                baseDatos.getServicios()) {

            comboServicio.addItem(s);
        }

        comboMecanico =
                new JComboBox<>();

        cargarMecanicos();

        agregarCampo(
                formulario,
                0,
                "Vehículo",
                comboVehiculo
        );

        agregarCampo(
                formulario,
                1,
                "Servicio",
                comboServicio
        );

        agregarCampo(
                formulario,
                2,
                "Mecánico",
                comboMecanico
        );

        JLabel descripcion =
                new JLabel(
                        "<html>"
                                + "<b>Servicios disponibles:</b><br>"
                                + "• Cambio de aceite — servicio rápido<br>"
                                + "• Frenos — mantenimiento de sistema de frenado<br>"
                                + "• Trabajo de motor — reparación de mayor complejidad"
                                + "</html>"
                );

        descripcion.setForeground(
                GRIS
        );

        GridBagConstraints gbc =
                constraints(
                        3
                );

        gbc.gridwidth = 2;

        formulario.add(
                descripcion,
                gbc
        );

        RoundedButton crear =
                new RoundedButton(
                        "⚙  Crear orden",
                        NARANJA
                );

        gbc =
                constraints(
                        4
                );

        gbc.gridwidth = 2;

        formulario.add(
                crear,
                gbc
        );

        crear.addActionListener(
                e -> crearOrden()
        );

        contenido.add(
                formulario,
                BorderLayout.CENTER
        );

        actualizarPantalla();
    }

    private void cargarMecanicos() {

        comboMecanico.removeAllItems();

        for (Mecanico m :
                baseDatos.getMecanicos()) {

            if (!m.isOcupado()) {

                comboMecanico.addItem(m);
            }
        }
    }

    private void crearOrden() {

        if (comboMecanico
                .getItemCount() == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Los tres mecánicos están ocupados.\n\n"
                            + "No existe espacio inmediato para "
                            + "asignar el vehículo.",
                    "Sin mecánicos disponibles",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        Vehiculo vehiculo =
                (Vehiculo)
                        comboVehiculo
                                .getSelectedItem();

        Servicio servicio =
                (Servicio)
                        comboServicio
                                .getSelectedItem();

        Mecanico mecanico =
                (Mecanico)
                        comboMecanico
                                .getSelectedItem();

        if (vehiculo == null
                || servicio == null
                || mecanico == null) {

            return;
        }

        int espera =
                JOptionPane.showConfirmDialog(
                        this,
                        "¿El cliente desea esperar en el taller?",
                        "Atención al cliente",
                        JOptionPane.YES_NO_OPTION
                );

        boolean clienteEspera =
                espera ==
                        JOptionPane.YES_OPTION;

        mecanico.asignarVehiculo(
                vehiculo,
                servicio.getTiempoEstimado()
        );

        OrdenServicio orden =
                new OrdenServicio(
                        contadorOrden++,
                        vehiculo.getPropietario(),
                        vehiculo,
                        servicio,
                        mecanico
                );

        orden.setClienteEspera(
                clienteEspera
        );

        orden.setEstado(
                "En servicio"
        );

        baseDatos
                .getOrdenes()
                .add(orden);

        baseDatos.guardar();

        mostrarTicket(orden);

        mostrarInicio();
    }

    // =========================================================
    // TICKET
    // =========================================================

    private void mostrarTicket(
            OrdenServicio orden) {

        JTextArea area =
                new JTextArea();

        area.setEditable(false);

        area.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        13
                )
        );

        area.setText(
                "========================================\n"
                        + "           TALLER MECÁNICO\n"
                        + "            ORDEN DE SERVICIO\n"
                        + "========================================\n\n"
                        + "ORDEN: #"
                        + orden.getId()
                        + "\n"
                        + "CLIENTE: "
                        + orden.getCliente()
                        .getNombre()
                        + "\n"
                        + "VEHÍCULO: "
                        + orden.getVehiculo()
                        .getMarca()
                        + " "
                        + orden.getVehiculo()
                        .getModelo()
                        + "\n"
                        + "PLACAS: "
                        + orden.getVehiculo()
                        .getPlacas()
                        + "\n\n"
                        + "SERVICIO: "
                        + orden.getServicio()
                        .getNombre()
                        + "\n"
                        + "COMPLEJIDAD: "
                        + orden.getServicio()
                        .getComplejidad()
                        + "\n"
                        + "MECÁNICO: "
                        + orden.getMecanico()
                        .getNombre()
                        + "\n"
                        + "TIEMPO ESTIMADO: "
                        + orden.getServicio()
                        .getTiempoEstimado()
                        + " minutos\n\n"
                        + "CLIENTE ESPERA: "
                        + (
                        orden.isClienteEspera()
                                ? "SÍ"
                                : "NO"
                )
                        + "\n\n"
                        + "El vehículo será revisado por el\n"
                        + "supervisor antes de la entrega.\n"
                        + "========================================"
        );

        JScrollPane scroll =
                new JScrollPane(area);

        scroll.setPreferredSize(
                new Dimension(
                        520,
                        430
                )
        );

        JOptionPane.showMessageDialog(
                this,
                scroll,
                "Ficha de servicio",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // =========================================================
    // SIMULACIÓN
    // =========================================================

    private void mostrarSimulacion() {

        contenido.removeAll();

        contenido.add(
                encabezado(
                        "Simulación del proceso",
                        "Paso 4 · Ejecución visual de la atención"
                ),
                BorderLayout.NORTH
        );

        JPanel panel =
                formularioBase();

        comboSimulacion =
                new JComboBox<>();

        for (OrdenServicio orden :
                baseDatos.getOrdenes()) {

            if (!orden.getEstado()
                    .equals("Entregado")) {

                comboSimulacion
                        .addItem(orden);
            }
        }

        JTextArea resultados =
                new JTextArea();

        resultados.setEditable(false);

        resultados.setFont(
                new Font(
                        "Consolas",
                        Font.PLAIN,
                        14
                )
        );

        resultados.setLineWrap(true);

        resultados.setWrapStyleWord(true);

        JScrollPane scroll =
                new JScrollPane(
                        resultados
                );

        scroll.setPreferredSize(
                new Dimension(
                        700,
                        330
                )
        );

        agregarCampo(
                panel,
                0,
                "Orden a simular",
                comboSimulacion
        );

        GridBagConstraints gbc =
                constraints(
                        1
                );

        gbc.gridwidth = 2;

        panel.add(
                scroll,
                gbc
        );

        RoundedButton iniciar =
                new RoundedButton(
                        "▶  Iniciar simulación",
                        MORADO
                );

        gbc =
                constraints(
                        2
                );

        gbc.gridwidth = 2;

        panel.add(
                iniciar,
                gbc
        );

        iniciar.addActionListener(
                e -> ejecutarSimulacion(
                        resultados
                )
        );

        contenido.add(
                panel,
                BorderLayout.CENTER
        );

        actualizarPantalla();
    }

    private void ejecutarSimulacion(
            JTextArea area) {

        if (comboSimulacion
                .getSelectedItem() == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "No hay órdenes para simular.",
                    "Simulación",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        OrdenServicio orden =
                (OrdenServicio)
                        comboSimulacion
                                .getSelectedItem();

        area.setText("");

        List<String> pasos =
                new ArrayList<>();

        pasos.add(
                "INICIANDO SIMULACIÓN..."
        );

        pasos.add(
                "Vehículo: "
                        + orden.getVehiculo()
                        .getMarca()
                        + " "
                        + orden.getVehiculo()
                        .getModelo()
        );

        pasos.add(
                "Servicio: "
                        + orden.getServicio()
                        .getNombre()
        );

        pasos.add(
                "Mecánico: "
                        + orden.getMecanico()
                        .getNombre()
        );

        String servicio =
                orden.getServicio()
                        .getNombre();

        if (servicio.equals(
                "Cambio de aceite")) {

            pasos.add(
                    "1. Recepción del vehículo."
            );

            pasos.add(
                    "2. Elevación del vehículo."
            );

            pasos.add(
                    "3. Retiro del aceite usado."
            );

            pasos.add(
                    "4. Cambio de filtro."
            );

            pasos.add(
                    "5. Colocación de aceite nuevo."
            );

            pasos.add(
                    "6. Revisión de niveles."
            );

            pasos.add(
                    "7. Prueba de calidad."
            );

        } else if (servicio.equals(
                "Frenos")) {

            pasos.add(
                    "1. Recepción del vehículo."
            );

            pasos.add(
                    "2. Inspección de frenos."
            );

            pasos.add(
                    "3. Revisión de pastillas."
            );

            pasos.add(
                    "4. Revisión de discos."
            );

            pasos.add(
                    "5. Mantenimiento del sistema."
            );

            pasos.add(
                    "6. Ajuste de componentes."
            );

            pasos.add(
                    "7. Prueba de frenado."
            );

        } else {

            pasos.add(
                    "1. Recepción del vehículo."
            );

            pasos.add(
                    "2. Diagnóstico del motor."
            );

            pasos.add(
                    "3. Revisión de componentes."
            );

            pasos.add(
                    "4. Desmontaje."
            );

            pasos.add(
                    "5. Reparación."
            );

            pasos.add(
                    "6. Montaje."
            );

            pasos.add(
                    "7. Prueba del motor."
            );

            pasos.add(
                    "8. Prueba de calidad."
            );
        }

        pasos.add(
                "9. Revisión del supervisor."
        );

        pasos.add(
                "10. Vehículo listo para entrega."
        );

        final int[] posicion =
                {0};

        javax.swing.Timer timer =
                new javax.swing.Timer(
                        850,
                        null
                );

        timer.addActionListener(
                e -> {

                    if (posicion[0]
                            < pasos.size()) {

                        area.append(
                                pasos.get(
                                        posicion[0]
                                )
                                        + "\n\n"
                        );

                        posicion[0]++;

                    } else {

                        timer.stop();

                        orden.setEstado(
                                "Listo para entregar"
                        );

                        orden.getMecanico()
                                .liberar();

                        baseDatos.guardar();

                        area.append(
                                "\n================================\n"
                        );

                        area.append(
                                "SIMULACIÓN TERMINADA\n"
                        );

                        area.append(
                                "El vehículo está listo para entrega.\n"
                        );

                        area.append(
                                "================================\n"
                        );

                        JOptionPane.showMessageDialog(
                                this,
                                "Simulación completada correctamente.",
                                "Proceso terminado",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                        actualizarPantalla();
                    }
                }
        );

        timer.start();
    }

    // =========================================================
    // ENTREGA
    // =========================================================

    private void mostrarEntrega() {

        contenido.removeAll();

        contenido.add(
                encabezado(
                        "Entrega de vehículo",
                        "Paso 5 · Finalización del servicio"
                ),
                BorderLayout.NORTH
        );

        JPanel panel =
                formularioBase();

        comboEntrega =
                new JComboBox<>();

        for (OrdenServicio orden :
                baseDatos.getOrdenes()) {

            if (orden.getEstado()
                    .equals(
                            "Listo para entregar"
                    )) {

                comboEntrega.addItem(
                        orden
                );
            }
        }

        agregarCampo(
                panel,
                0,
                "Orden lista",
                comboEntrega
        );

        RoundedButton entregar =
                new RoundedButton(
                        "✓  Confirmar entrega",
                        VERDE
                );

        GridBagConstraints gbc =
                constraints(
                        1
                );

        gbc.gridwidth = 2;

        panel.add(
                entregar,
                gbc
        );

        entregar.addActionListener(
                e -> {

                    OrdenServicio orden =
                            (OrdenServicio)
                                    comboEntrega
                                            .getSelectedItem();

                    if (orden == null) {

                        JOptionPane.showMessageDialog(
                                this,
                                "No hay vehículos listos para entregar.",
                                "Entrega",
                                JOptionPane.WARNING_MESSAGE
                        );

                        return;
                    }

                    orden.setEstado(
                            "Entregado"
                    );

                    orden.setHoraEntrega(
                            new SimpleDateFormat(
                                    "dd/MM/yyyy HH:mm"
                            ).format(
                                    new Date()
                            )
                    );

                    baseDatos.guardar();

                    JOptionPane.showMessageDialog(
                            this,
                            "ENTREGA COMPLETADA\n\n"
                                    + "Orden: #"
                                    + orden.getId()
                                    + "\nVehículo: "
                                    + orden.getVehiculo()
                                    .getMarca()
                                    + " "
                                    + orden.getVehiculo()
                                    .getModelo()
                                    + "\nHora: "
                                    + orden.getHoraEntrega(),
                            "Vehículo entregado",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    mostrarEntrega();
                }
        );

        contenido.add(
                panel,
                BorderLayout.CENTER
        );

        actualizarPantalla();
    }

    // =========================================================
    // HISTORIAL
    // =========================================================

    private void mostrarHistorial() {

        contenido.removeAll();

        contenido.add(
                encabezado(
                        "Historial de órdenes",
                        "Registro de servicios realizados"
                ),
                BorderLayout.NORTH
        );

        JPanel lista =
                new JPanel();

        lista.setOpaque(false);

        lista.setLayout(
                new BoxLayout(
                        lista,
                        BoxLayout.Y_AXIS
                )
        );

        if (baseDatos
                .getOrdenes()
                .isEmpty()) {

            lista.add(
                    crearAviso(
                            "Todavía no existen órdenes registradas.",
                            AZUL2
                    )
            );

        } else {

            for (OrdenServicio orden :
                    baseDatos.getOrdenes()) {

                lista.add(
                        tarjetaOrden(
                                orden
                        )
                );

                lista.add(
                        Box.createVerticalStrut(10)
                );
            }
        }

        JScrollPane scroll =
                new JScrollPane(
                        lista
                );

        scroll.setBorder(null);

        contenido.add(
                scroll,
                BorderLayout.CENTER
        );

        actualizarPantalla();
    }

    private JPanel tarjetaOrden(
            OrdenServicio orden) {

        RoundedPanel panel =
                new RoundedPanel(
                        Color.WHITE
                );

        panel.setBorder(
                new EmptyBorder(
                        15,
                        20,
                        15,
                        20
                )
        );

        panel.setLayout(
                new BorderLayout()
        );

        JLabel titulo =
                new JLabel(
                        "ORDEN #"
                                + orden.getId()
                                + "   |   "
                                + orden.getServicio()
                                .getNombre()
                );

        titulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        titulo.setForeground(
                AZUL
        );

        JLabel info =
                new JLabel(
                        "<html>"
                                + "Cliente: "
                                + orden.getCliente()
                                .getNombre()
                                + "<br>"
                                + "Vehículo: "
                                + orden.getVehiculo()
                                .getMarca()
                                + " "
                                + orden.getVehiculo()
                                .getModelo()
                                + "<br>"
                                + "Mecánico: "
                                + orden.getMecanico()
                                .getNombre()
                                + "</html>"
                );

        JLabel estado =
                new JLabel(
                        orden.getEstado()
                );

        estado.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        estado.setForeground(
                orden.getEstado()
                        .equals("Entregado")
                        ? VERDE
                        : NARANJA
        );

        panel.add(
                titulo,
                BorderLayout.NORTH
        );

        panel.add(
                info,
                BorderLayout.CENTER
        );

        panel.add(
                estado,
                BorderLayout.EAST
        );

        return panel;
    }

    // =========================================================
    // MECÁNICOS
    // =========================================================

    private void mostrarMecanicos() {

        contenido.removeAll();

        contenido.add(
                encabezado(
                        "Control de mecánicos",
                        "Disponibilidad y asignación actual"
                ),
                BorderLayout.NORTH
        );

        JPanel lista =
                new JPanel(
                        new GridLayout(
                                0,
                                1,
                                10,
                                10
                        )
                );

        lista.setOpaque(false);

        for (Mecanico m :
                baseDatos.getMecanicos()) {

            RoundedPanel tarjeta =
                    new RoundedPanel(
                            m.isOcupado()
                                    ? ROJO_CLARO
                                    : VERDE_CLARO
                    );

            tarjeta.setBorder(
                    new EmptyBorder(
                            20,
                            25,
                            20,
                            25
                    )
            );

            tarjeta.setLayout(
                    new BorderLayout()
            );

            JLabel nombre =
                    new JLabel(
                            m.getNombre()
                    );

            nombre.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            20
                    )
            );

            nombre.setForeground(
                    AZUL
            );

            String texto =
                    "<html>"
                            + "Especialidad: "
                            + m.getEspecialidad();

            if (m.isOcupado()
                    && m.getVehiculoActual()
                    != null) {

                texto +=
                        "<br>Vehículo: "
                                + m.getVehiculoActual()
                                .getPlacas();
            }

            texto += "</html>";

            JLabel informacion =
                    new JLabel(
                            texto
                    );

            JPanel centro =
                    new JPanel();

            centro.setOpaque(false);

            centro.setLayout(
                    new BoxLayout(
                            centro,
                            BoxLayout.Y_AXIS
                    )
            );

            centro.add(nombre);
            centro.add(
                    Box.createVerticalStrut(8)
            );
            centro.add(informacion);

            JLabel estado =
                    new JLabel(
                            m.isOcupado()
                                    ? "● OCUPADO"
                                    : "● DISPONIBLE"
                    );

            estado.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            14
                    )
            );

            estado.setForeground(
                    m.isOcupado()
                            ? ROJO
                            : VERDE
            );

            tarjeta.add(
                    centro,
                    BorderLayout.CENTER
            );

            tarjeta.add(
                    estado,
                    BorderLayout.EAST
            );

            if (m.isOcupado()) {

                RoundedButton liberar =
                        new RoundedButton(
                                "Liberar",
                                ROJO
                        );

                liberar.addActionListener(
                        e -> {

                            m.liberar();

                            baseDatos.guardar();

                            mostrarMecanicos();
                        }
                );

                tarjeta.add(
                        liberar,
                        BorderLayout.SOUTH
                );
            }

            lista.add(tarjeta);
        }

        contenido.add(
                new JScrollPane(
                        lista
                ),
                BorderLayout.CENTER
        );

        actualizarPantalla();
    }

    // =========================================================
    // COMPONENTES AUXILIARES
    // =========================================================

    private JPanel formularioBase() {

        JPanel panel =
                new RoundedPanel(
                        Color.WHITE
                );

        panel.setLayout(
                new GridBagLayout()
        );

        panel.setBorder(
                new EmptyBorder(
                        35,
                        50,
                        35,
                        50
                )
        );

        return panel;
    }

    private JTextField campoTexto() {

        JTextField campo =
                new JTextField();

        campo.setPreferredSize(
                new Dimension(
                        400,
                        38
                )
        );

        campo.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        return campo;
    }

    private void agregarCampo(
            JPanel panel,
            int fila,
            String etiqueta,
            JComponent componente) {

        GridBagConstraints gbc =
                constraints(
                        fila
                );

        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.25;

        JLabel label =
                new JLabel(
                        etiqueta
                );

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        label.setForeground(
                TEXTO
        );

        panel.add(
                label,
                gbc
        );

        gbc.gridx = 1;
        gbc.weightx = 0.75;

        componente.setPreferredSize(
                new Dimension(
                        400,
                        38
                )
        );

        panel.add(
                componente,
                gbc
        );
    }

    private GridBagConstraints constraints(
            int fila) {

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridy = fila;

        gbc.insets =
                new Insets(
                        10,
                        10,
                        10,
                        10
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        return gbc;
    }

    private JPanel crearAviso(
            String mensaje,
            Color color) {

        JPanel panel =
                new RoundedPanel(
                        Color.WHITE
                );

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setBorder(
                new EmptyBorder(
                        40,
                        40,
                        40,
                        40
                )
        );

        JLabel texto =
                new JLabel(
                        mensaje
                );

        texto.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        texto.setForeground(
                color
        );

        texto.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panel.add(texto);

        return panel;
    }

    private JPanel crearPanelMecanicosInicio() {

        RoundedPanel panel =
                new RoundedPanel(
                        Color.WHITE
                );

        panel.setBorder(
                new EmptyBorder(
                        20,
                        25,
                        20,
                        25
                )
        );

        panel.setLayout(
                new BorderLayout()
        );

        JLabel titulo =
                new JLabel(
                        "Estado de mecánicos"
                );

        titulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        19
                )
        );

        titulo.setForeground(
                AZUL
        );

        panel.add(
                titulo,
                BorderLayout.NORTH
        );

        JPanel lista =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                12,
                                12
                        )
                );

        lista.setOpaque(false);

        for (Mecanico m :
                baseDatos.getMecanicos()) {

            JPanel tarjeta =
                    new JPanel();

            tarjeta.setBackground(
                    m.isOcupado()
                            ? ROJO_CLARO
                            : VERDE_CLARO
            );

            tarjeta.setBorder(
                    new EmptyBorder(
                            15,
                            15,
                            15,
                            15
                    )
            );

            tarjeta.setLayout(
                    new BoxLayout(
                            tarjeta,
                            BoxLayout.Y_AXIS
                    )
            );

            JLabel nombre =
                    new JLabel(
                            m.getNombre()
                    );

            nombre.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            14
                    )
            );

            JLabel estado =
                    new JLabel(
                            m.isOcupado()
                                    ? "OCUPADO"
                                    : "DISPONIBLE"
                    );

            estado.setForeground(
                    m.isOcupado()
                            ? ROJO
                            : VERDE
            );

            estado.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            12
                    )
            );

            tarjeta.add(nombre);
            tarjeta.add(
                    Box.createVerticalStrut(6)
            );
            tarjeta.add(estado);

            lista.add(tarjeta);
        }

        panel.add(
                lista,
                BorderLayout.CENTER
        );

        return panel;
    }

    private int contarDisponibles() {

        int total = 0;

        for (Mecanico m :
                baseDatos.getMecanicos()) {

            if (!m.isOcupado()) {
                total++;
            }
        }

        return total;
    }

    private void actualizarPantalla() {

        if (lblClientes != null) {

            lblClientes.setText(
                    String.valueOf(
                            baseDatos
                                    .getClientes()
                                    .size()
                    )
            );
        }

        if (lblVehiculos != null) {

            lblVehiculos.setText(
                    String.valueOf(
                            baseDatos
                                    .getVehiculos()
                                    .size()
                    )
            );
        }

        if (lblOrdenes != null) {

            lblOrdenes.setText(
                    String.valueOf(
                            baseDatos
                                    .getOrdenes()
                                    .size()
                    )
            );
        }

        if (lblMecanicos != null) {

            lblMecanicos.setText(
                    String.valueOf(
                            contarDisponibles()
                    )
            );
        }

        contenido.revalidate();
        contenido.repaint();
    }

    // =========================================================
    // PANEL REDONDEADO
    // =========================================================

    static class RoundedPanel
            extends JPanel {

        private final Color color;

        public RoundedPanel(
                Color color) {

            this.color = color;

            setOpaque(false);
        }

        @Override
        protected void paintComponent(
                Graphics g) {

            Graphics2D g2 =
                    (Graphics2D)
                            g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(color);

            g2.fillRoundRect(
                    0,
                    0,
                    getWidth() - 1,
                    getHeight() - 1,
                    18,
                    18
            );

            g2.dispose();

            super.paintComponent(g);
        }
    }

    // =========================================================
    // BOTÓN REDONDEADO
    // =========================================================

    static class RoundedButton
            extends JButton {

        private final Color color;

        public RoundedButton(
                String texto,
                Color color) {

            super(texto);

            this.color = color;

            setForeground(Color.WHITE);

            setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            13
                    )
            );

            setFocusPainted(false);

            setBorderPainted(false);

            setContentAreaFilled(false);

            setCursor(
                    new Cursor(
                            Cursor.HAND_CURSOR
                    )
            );

            setMaximumSize(
                    new Dimension(
                            220,
                            43
                    )
            );

            setPreferredSize(
                    new Dimension(
                            220,
                            43
                    )
            );
        }

        @Override
        protected void paintComponent(
                Graphics g) {

            Graphics2D g2 =
                    (Graphics2D)
                            g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(color);

            g2.fillRoundRect(
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    13,
                    13
            );

            g2.dispose();

            super.paintComponent(g);
        }
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(
            String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    TallerGUI ventana =
                            new TallerGUI();

                    ventana.setVisible(
                            true
                    );
                }
        );
    }
}