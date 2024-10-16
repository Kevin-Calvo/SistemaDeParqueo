package com.mycompany.sistemaparqueo.GUI;

import com.mycompany.sistemaparqueo.Clases.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {

    public Menu(Persona persona) {
        // Configuraciones de la ventana
        setTitle("Menú");
        setSize(300, 400); // Ajustado para permitir más botones
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana

        // Crear el panel principal con Layout nulo
        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        // Mostrar botones dependiendo del tipo de usuario
        if (persona.getTipo().equals("Administrador")) {
            // Crear y agregar botones para el tipo 1
            crearBoton(panel, "Información de usuario", 10, 20, e -> informacionUsuario(persona));
            crearBoton(panel, "Crear nuevo administrador", 10, 60, e -> crearNuevoAdministrador(persona));
            crearBoton(panel, "Configurar Parqueos", 10, 100, e -> configurarParqueos());
            crearBoton(panel, "Agregar Parqueos", 10, 140, e -> agregarParqueos());
            crearBoton(panel, "Reportes", 10, 180, e -> mostrarReportes());
            crearBoton(panel, "Crear Inspector", 10, 220, e -> crearInspector(persona));
            crearBoton(panel, "Inspectores", 10, 260, e -> verInspectores());
            crearBoton(panel, "Cerrar Sesión",10, 300, e -> cerrarSesion()); 
        } else if (persona.getTipo().equals("Usuario")) {
            // Crear y agregar botones para el tipo 2
            crearBoton(panel, "Información de usuario", 10, 20, e -> informacionUsuario(persona));
            crearBoton(panel, "Lista de Carros", 10, 60, e -> mostrarListaDeCarros(persona));
            crearBoton(panel, "Parquear", 10, 100, e -> parquear());
            crearBoton(panel, "Agregar tiempo", 10, 140, e -> agregarTiempo());
            crearBoton(panel, "Desaparcar", 10, 180, e -> desaparecer());
            crearBoton(panel, "Reportes", 10, 220, e -> mostrarReportes());
            crearBoton(panel, "Cerrar Sesión",10, 260, e -> cerrarSesion()); 
        } else if (persona.getTipo().equals("Inspector")) {
            // Mostrar opciones para el tipo 3
            crearBoton(panel, "Informacion de Usuario", 10, 20, e -> informacionUsuario(persona));
            crearBoton(panel, "Revisar parqueo", 10, 60, e -> revisarParqueo());
            crearBoton(panel, "Reportes", 10, 100, e -> mostrarReportes());
            crearBoton(panel, "Cerrar Sesión",10, 140, e -> cerrarSesion()); 
        }

        // Mostrar la ventana
        setVisible(true);
    }

    // Método para crear un botón y añadirlo al panel con posiciones ajustadas
    private void crearBoton(JPanel panel, String texto, int x, int y, ActionListener actionListener) {
        JButton boton = new JButton(texto);
        boton.setBounds(x, y, 200, 30); // Ajustar posición y tamaño del botón
        boton.addActionListener(actionListener);
        panel.add(boton);
    }

    // Métodos para las acciones de los botones
    private void crearNuevoAdministrador(Persona persona) {
        dispose();
        Persona nuevaPersona = new Admin();
        new Registrar(nuevaPersona, persona);
    }
    
    private void cerrarSesion() {
        dispose();
        new PantallaInicio();
    }

    private void informacionUsuario(Persona persona) {
        dispose();
        new ModificarUsuario(persona);
    }

    private void configurarParqueos() {
        // Lógica para configurar parqueos
        JOptionPane.showMessageDialog(this, "Configurar parqueos");
    }

    private void agregarParqueos() {
        // Lógica para agregar parqueos
        JOptionPane.showMessageDialog(this, "Agregar parqueos");
    }

    private void mostrarReportes() {
        // Lógica para mostrar reportes
        JOptionPane.showMessageDialog(this, "Mostrar reportes");
    }

    private void crearInspector(Persona persona) {
        dispose();
        Persona nuevaPersona = new Inspector();
        new Registrar(nuevaPersona, persona);
    }

    private void verInspectores() {
        // Lógica para ver inspectores
        JOptionPane.showMessageDialog(this, "Ver inspectores");
    }

    private void mostrarListaDeCarros(Persona persona) {
       dispose();
       new CarroGUI(persona);
    }

    private void parquear() {
        // Lógica para parquear
        JOptionPane.showMessageDialog(this, "Parquear");
    }

    private void agregarTiempo() {
        // Lógica para agregar tiempo
        JOptionPane.showMessageDialog(this, "Agregar tiempo");
    }

    private void desaparecer() {
        // Lógica para desaparecer un carro
        JOptionPane.showMessageDialog(this, "Desaparcar");
    }

    private void revisarParqueo() {
        // Lógica para revisar parqueo
        JOptionPane.showMessageDialog(this, "Revisar parqueo");
    }

}

