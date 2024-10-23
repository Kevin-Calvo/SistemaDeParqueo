package com.mycompany.sistemaparqueo.GUI;

import com.mycompany.sistemaparqueo.Clases.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Esta clase representa el menú principal del sistema de parqueo,
 * mostrando diferentes opciones basadas en el tipo de usuario.
 */
public class Menu extends JFrame {

    /**
     * Constructor que inicializa el menú según el tipo de persona.
     *
     * @param persona La persona que ha iniciado sesión, determina las opciones disponibles en el menú.
     */
    public Menu(Persona persona) {
        setTitle("Menú");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        // Mostrar botones dependiendo del tipo de usuario
        if (persona.getTipo().equals("Administrador")) {
            crearBoton(panel, "Información de usuario", 10, 20, e -> informacionUsuario(persona));
            crearBoton(panel, "Crear nuevo administrador", 10, 60, e -> crearNuevoAdministrador(persona));
            crearBoton(panel, "Configurar Parqueos", 10, 100, e -> configurarParqueos(persona));
            crearBoton(panel, "Configurar Espacios", 10, 140, e -> agregarParqueos(persona));
            crearBoton(panel, "Reportes", 10, 180, e -> mostrarReportes());
            crearBoton(panel, "Crear Inspector", 10, 220, e -> crearInspector(persona));
            crearBoton(panel, "Inspectores", 10, 260, e -> verInspectores(persona));
            crearBoton(panel, "Cerrar Sesión",10, 300, e -> cerrarSesion()); 
        } else if (persona.getTipo().equals("Usuario")) {
            crearBoton(panel, "Información de usuario", 10, 20, e -> informacionUsuario(persona));
            crearBoton(panel, "Lista de Carros", 10, 60, e -> mostrarListaDeCarros(persona));
            crearBoton(panel, "Parquear", 10, 100, e -> parquear(persona));
            crearBoton(panel, "Agregar tiempo", 10, 140, e -> agregarTiempo(persona));
            crearBoton(panel, "Desaparcar", 10, 180, e -> desaparecer(persona));
            crearBoton(panel, "Reportes", 10, 220, e -> mostrarReportes());
            crearBoton(panel, "Cerrar Sesión",10, 260, e -> cerrarSesion()); 
        } else if (persona.getTipo().equals("Inspector")) {
            crearBoton(panel, "Información de Usuario", 10, 20, e -> informacionUsuario(persona));
            crearBoton(panel, "Revisar parqueo", 10, 60, e -> revisarParqueo());
            crearBoton(panel, "Reportes", 10, 100, e -> mostrarReportes());
            crearBoton(panel, "Cerrar Sesión",10, 140, e -> cerrarSesion()); 
        }

        setVisible(true);
    }

    /**
     * Crea un botón con texto y lo añade al panel en la posición indicada.
     *
     * @param panel El panel al que se añadirá el botón.
     * @param texto El texto que mostrará el botón.
     * @param x La posición horizontal del botón.
     * @param y La posición vertical del botón.
     * @param actionListener El listener que define la acción al presionar el botón.
     */
    private void crearBoton(JPanel panel, String texto, int x, int y, ActionListener actionListener) {
        JButton boton = new JButton(texto);
        boton.setBounds(x, y, 200, 30);
        boton.addActionListener(actionListener);
        panel.add(boton);
    }

    /**
     * Crea un nuevo administrador y registra su información.
     *
     * @param persona La persona que está registrando al nuevo administrador.
     */
    private void crearNuevoAdministrador(Persona persona) {
        dispose();
        Persona nuevaPersona = new Admin();
        new Registrar(nuevaPersona, persona);
    }

    /**
     * Cierra la sesión del usuario actual y redirige a la pantalla de inicio.
     */
    private void cerrarSesion() {
        dispose();
        new PantallaInicio();
    }

    /**
     * Muestra la interfaz para modificar la información del usuario.
     *
     * @param persona La persona cuya información será modificada.
     */
    private void informacionUsuario(Persona persona) {
        dispose();
        new ModificarUsuario(persona);
    }

    /**
     * Abre la interfaz para configurar los parqueos.
     */
    private void configurarParqueos(Persona persona) {
        new ParqueoGUI(persona);
    }

    /**
     * Abre la interfaz para agregar nuevos parqueos.
     */
    private void agregarParqueos(Persona persona) {
        new EspaciosGUI(persona);
    }

    /**
     * Muestra los reportes del sistema.
     */
    private void mostrarReportes() {
        JOptionPane.showMessageDialog(this, "Mostrar reportes");
    }

    /**
     * Crea un nuevo inspector y registra su información.
     *
     * @param persona La persona que está registrando al nuevo inspector.
     */
    private void crearInspector(Persona persona) {
        dispose();
        Persona nuevaPersona = new Inspector();
        new Registrar(nuevaPersona, persona);
    }

    /**
     * Muestra la interfaz gráfica con la lista de inspectores disponibles.
     *
     * @param persona La persona que está visualizando la lista de inspectores.
     */
    private void verInspectores(Persona persona) {
        new InspectoresGUI(persona);
    }

    /**
     * Muestra la lista de carros en una interfaz gráfica.
     *
     * @param persona La persona que está visualizando la lista de carros.
     */
    private void mostrarListaDeCarros(Persona persona) {
        dispose();
        new CarroGUI(persona);
    }

    /**
     * Abre la interfaz para parquear un vehículo.
     */
    private void parquear(Persona persona) {
        dispose();
        new ParquearInicio(persona);
    }

    /**
     * Abre la interfaz para agregar tiempo de parqueo a un vehículo.
     */
    private void agregarTiempo(Persona persona) {
        dispose();
        new AgregarTiempoInicio(persona);
    }

    /**
     * Abre la interfaz para desaparcar un vehículo.
     */
    private void desaparecer(Persona persona) {
        dispose();
        new Desaparcar(persona);
    }

    /**
     * Abre la interfaz para revisar el estado de los parqueos.
     */
    private void revisarParqueo() {
        JOptionPane.showMessageDialog(this, "Revisar parqueo");
    }
}
