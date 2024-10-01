package com.mycompany.sistemaparqueo.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PantallaInicio extends JFrame {

    public PantallaInicio() {
        // Configuraciones de la ventana
        setTitle("Pantalla de Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana

        // Crear el panel principal con Layout nulo
        JPanel panel = new JPanel();
        panel.setLayout(null); // Layout nulo para control manual de posiciones
        add(panel);

        // Etiqueta para el código de usuario
        JLabel usuarioLabel = new JLabel("Código de Usuario:");
        usuarioLabel.setBounds(30, 30, 120, 25); // Ajustamos la posición y tamaño
        panel.add(usuarioLabel);

        // Campo de texto para el código de usuario
        JTextField usuarioText = new JTextField(20);
        usuarioText.setBounds(160, 30, 160, 25); // Ajustamos la posición y tamaño
        panel.add(usuarioText);

        // Etiqueta para la contraseña
        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setBounds(30, 70, 120, 25); // Ajustamos la posición y tamaño
        panel.add(passwordLabel);

        // Campo de contraseña
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(160, 70, 160, 25); // Ajustamos la posición y tamaño
        panel.add(passwordText);

        // Botón de iniciar sesión
        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setBounds(160, 110, 160, 25); // Ajustamos la posición y tamaño
        panel.add(loginButton);

        // Acción del botón de iniciar sesión
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigoUsuario = usuarioText.getText();
                String password = new String(passwordText.getPassword());

                if (!codigoUsuario.equals("aaa")) {
                    JOptionPane.showMessageDialog(null, "Usuario no existe");
                } else {
                    // Cierra la ventana de inicio antes de abrir el menú
                    dispose();
                    new Menu(1);
                }
            }
        });

        // Botón para registrarse
        JButton registerButton = new JButton("Registrarse");
        registerButton.setBounds(160, 150, 160, 25); // Ajustamos la posición y tamaño
        panel.add(registerButton);

        // Acción del botón de registrarse
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cierra la ventana de inicio al abrir la ventana de registro
                dispose();
                new Registrar();
            }
        });


        // Mostrar la ventana
        setVisible(true);
    }
}

