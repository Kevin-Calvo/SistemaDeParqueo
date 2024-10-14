package com.mycompany.sistemaparqueo.GUI;

import com.mycompany.sistemaparqueo.Clases.Persona;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Registrar extends JFrame {

    public Registrar(Persona persona) {
        // Configuraciones de la ventana
        setTitle("Pantalla de Registro");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null); // Centrar la ventana

        // Crear el panel principal
        JPanel panel = new JPanel();
        panel.setLayout(null); // Layout nulo para control manual de posiciones
        add(panel);

        // Etiqueta y campo para el nombre
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(10, 20, 100, 25);
        panel.add(nombreLabel);

        JTextField nombreText = new JTextField(20);
        nombreText.setBounds(120, 20, 250, 25);
        panel.add(nombreText);

        // Etiqueta y campo para el apellido
        JLabel apellidoLabel = new JLabel("Apellido:");
        apellidoLabel.setBounds(10, 60, 100, 25);
        panel.add(apellidoLabel);

        JTextField apellidoText = new JTextField(20);
        apellidoText.setBounds(120, 60, 250, 25);
        panel.add(apellidoText);

        // Etiqueta y campo para el teléfono
        JLabel telefonoLabel = new JLabel("Teléfono:");
        telefonoLabel.setBounds(10, 100, 100, 25);
        panel.add(telefonoLabel);

        JTextField telefonoText = new JTextField(20);
        telefonoText.setBounds(120, 100, 250, 25);
        panel.add(telefonoText);

        // Etiqueta y campo para el correo
        JLabel correoLabel = new JLabel("Correo:");
        correoLabel.setBounds(10, 140, 100, 25);
        panel.add(correoLabel);

        JTextField correoText = new JTextField(20);
        correoText.setBounds(120, 140, 250, 25);
        panel.add(correoText);

        // Etiqueta y campo para la dirección física
        JLabel direccionLabel = new JLabel("Dirección:");
        direccionLabel.setBounds(10, 180, 100, 25);
        panel.add(direccionLabel);

        JTextField direccionText = new JTextField(20);
        direccionText.setBounds(120, 180, 250, 25);
        panel.add(direccionText);

        // Etiqueta y campo para la identificación
        JLabel idLabel = new JLabel("Identificación:");
        idLabel.setBounds(10, 220, 100, 25);
        panel.add(idLabel);

        JTextField idText = new JTextField(20);
        idText.setBounds(120, 220, 250, 25);
        panel.add(idText);

        // Etiqueta y campo para el PIN
        JLabel pinLabel = new JLabel("PIN:");
        pinLabel.setBounds(10, 260, 100, 25);
        panel.add(pinLabel);

        JPasswordField pinText = new JPasswordField(20); // El campo de PIN oculta el texto
        pinText.setBounds(120, 260, 250, 25);
        panel.add(pinText);

        // Botón de registro
        JButton registrarButton = new JButton("Registrar");
        registrarButton.setBounds(120, 380, 250, 25);
        panel.add(registrarButton);

        // Acciones basadas en el tipo de persona
        if (persona.getTipo().equals("Usuario")) {
            registrarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    registrarUsuario(nombreText, apellidoText, telefonoText, correoText, direccionText, idText, pinText, "Usuario", persona);
                }
            });
        } else if (persona.getTipo().equals("Administrador")) {
            agregarCampoFechaIngreso(panel);
            registrarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    registrarAdministrador(nombreText, apellidoText, telefonoText, correoText, direccionText, idText, pinText, panel, persona);
                }
            });
        } else if (persona.getTipo().equals("Inspector")) {
            agregarCampoFechaIngreso(panel);
            agregarCampoTerminal(panel);
            registrarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    registrarInspector(nombreText, apellidoText, telefonoText, correoText, direccionText, idText, pinText, panel, persona);
                }
            });
        }
    }

    private void registrarUsuario(JTextField nombreText, JTextField apellidoText, JTextField telefonoText,
                                  JTextField correoText, JTextField direccionText, JTextField idText, JPasswordField pinText, String tipo,
                                  Persona persona) {
        String nombre = nombreText.getText();
        String apellido = apellidoText.getText();
        String telefono = telefonoText.getText();
        String correo = correoText.getText();
        String direccion = direccionText.getText();
        String id = idText.getText();
        String pin = new String(pinText.getPassword());

        // Validaciones
        if (!validarNombre(nombre)) {
            JOptionPane.showMessageDialog(null, "El nombre debe tener entre 2 y 20 caracteres.");
        } else if (!validarApellido(apellido)) {
            JOptionPane.showMessageDialog(null, "El apellido debe tener entre 1 y 40 caracteres.");
        } else if (!validarTelefono(telefono)) {
            JOptionPane.showMessageDialog(null, "El teléfono debe tener 8 caracteres numéricos.");
        } else if (!validarCorreo(correo)) {
            JOptionPane.showMessageDialog(null, "El correo debe estar en formato parte1@parte2, donde parte1 y parte2 tienen al menos 3 caracteres.");
        } else if (!validarDireccion(direccion)) {
            JOptionPane.showMessageDialog(null, "La dirección debe tener entre 5 y 60 caracteres.");
        } else if (!validarIdentificacion(id)) {
            JOptionPane.showMessageDialog(null, "La identificación debe tener entre 2 y 25 caracteres.");
        } else if (!validarPin(pin)) {
            JOptionPane.showMessageDialog(null, "El PIN debe tener exactamente 4 caracteres.");
        } else {
            JOptionPane.showMessageDialog(null, "Usuario registrado correctamente.");
            dispose();
            new Menu(persona); // Suponiendo que Menu es otra pantalla
        }
    }

    private void registrarAdministrador(JTextField nombreText, JTextField apellidoText, JTextField telefonoText,
                                        JTextField correoText, JTextField direccionText, JTextField idText, JPasswordField pinText, JPanel panel, Persona persona) {
        registrarUsuario(nombreText, apellidoText, telefonoText, correoText, direccionText, idText, pinText, "Administrador", persona);

        JTextField fechaIngresoText = (JTextField) panel.getComponent(16);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            LocalDate fechaIngreso = LocalDate.parse(fechaIngresoText.getText(), formato);
            if (!validarFecha(fechaIngreso)) {
                JOptionPane.showMessageDialog(null, "La fecha de ingreso debe ser menor o igual a la fecha actual.");
            }
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto. Debe ser dd-MM-yyyy.");
        }
    }

    private void registrarInspector(JTextField nombreText, JTextField apellidoText, JTextField telefonoText,
                                    JTextField correoText, JTextField direccionText, JTextField idText, JPasswordField pinText, JPanel panel, Persona persona) {
        registrarAdministrador(nombreText, apellidoText, telefonoText, correoText, direccionText, idText, pinText, panel, persona);

        JTextField terminalText = (JTextField) panel.getComponent(18);
        String terminal = terminalText.getText();
        if (!validarTerminal(terminal)) {
            JOptionPane.showMessageDialog(null, "La terminal debe tener exactamente 6 caracteres.");
        }
    }

    // Métodos adicionales para agregar campos dinámicos
    private void agregarCampoFechaIngreso(JPanel panel) {
        JLabel fechaIngresoLabel = new JLabel("Fecha Ingreso:");
        fechaIngresoLabel.setBounds(10, 300, 100, 25);
        panel.add(fechaIngresoLabel);

        JTextField fechaIngresoText = new JTextField(20);
        fechaIngresoText.setBounds(120, 300, 250, 25);
        panel.add(fechaIngresoText);
    }

    private void agregarCampoTerminal(JPanel panel) {
        JLabel terminalLabel = new JLabel("Terminal:");
        terminalLabel.setBounds(10, 340, 100, 25);
        panel.add(terminalLabel);

        JTextField terminalText = new JTextField(20);
        terminalText.setBounds(120, 340, 250, 25);
        panel.add(terminalText);
    }

    // Métodos de validación de datos
    private boolean validarNombre(String nombre) {
        return nombre.length() >= 2 && nombre.length() <= 20;
    }

    private boolean validarApellido(String apellido) {
        return apellido.length() >= 1 && apellido.length() <= 40;
    }

    private boolean validarTelefono(String telefono) {
        return telefono.matches("\\d{8}");
    }

    private boolean validarCorreo(String correo) {
        return correo.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }

    private boolean validarDireccion(String direccion) {
        return direccion.length() >= 5 && direccion.length() <= 60;
    }

    private boolean validarIdentificacion(String id) {
        return id.length() >= 2 && id.length() <= 25;
    }

    private boolean validarPin(String pin) {
        return pin.length() == 4 && pin.matches("\\d{4}");
    }

    private boolean validarFecha(LocalDate fechaIngreso) {
        return !fechaIngreso.isAfter(LocalDate.now());
    }

    private boolean validarTerminal(String terminal) {
        return terminal.length() == 6;
    }
}
