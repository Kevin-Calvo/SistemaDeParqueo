package com.mycompany.sistemaparqueo.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Registrar extends JFrame {

    public Registrar() {
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
        pinText.setEchoChar('*'); // Configurar el carácter que se mostrará
        panel.add(pinText);

        // Botón de registro
        JButton registrarButton = new JButton("Registrar");
        registrarButton.setBounds(120, 300, 250, 25);
        panel.add(registrarButton);

        // Acción del botón de registrar
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                    // Si todas las validaciones son correctas
                    JOptionPane.showMessageDialog(null, "Usuario registrado correctamente.");
                    new Menu(2);
                    dispose();
                }
            }
        });
        
    }
    // Métodos de validación

    private boolean validarNombre(String nombre) {
        return nombre.length() >= 2 && nombre.length() <= 20;
    }

    private boolean validarApellido(String apellido) {
        return apellido.length() >= 1 && apellido.length() <= 40;
    }

    private boolean validarTelefono(String telefono) {
        return telefono.matches("\\d{8}"); // Verifica que tenga exactamente 8 dígitos
    }

    private boolean validarCorreo(String correo) {
        String[] partes = correo.split("@");
        if (partes.length != 2) return false;
        return partes[0].length() >= 3 && partes[1].length() >= 3;
    }

    private boolean validarDireccion(String direccion) {
        return direccion.length() >= 5 && direccion.length() <= 60;
    }

    private boolean validarIdentificacion(String id) {
        return id.length() >= 2 && id.length() <= 25;
    }

    private boolean validarPin(String pin) {
        return pin.matches("\\d{4}"); // Verifica que tenga exactamente 4 dígitos
    }

}

