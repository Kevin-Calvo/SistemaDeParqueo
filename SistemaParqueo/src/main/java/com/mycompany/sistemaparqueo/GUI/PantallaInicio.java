package com.mycompany.sistemaparqueo.GUI;

import com.mycompany.sistemaparqueo.Clases.*;
import com.mycompany.sistemaparqueo.SistemaParqueo;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
                String[] data = verificarUsuario(codigoUsuario, password);
                if (data == null) {
                    JOptionPane.showMessageDialog(null, "Usuario no existe");
                } else {
                    // Cierra la ventana de inicio antes de abrir el menú
                    dispose();
                    Persona persona = null;
                    
                    if (data[0].equals("Usuario")){
                        persona = new Usuario(data[0], data[1], data[2], data[3], 
                                data[4], data[5], data[6], data[7], data[8]);
                    } else if (data[0].equals("Administrador")){
                        persona = new Admin(data[0], data[1], data[2], data[3], 
                                data[4], data[5], data[6], data[7], data[8]);
                    } else if (data[0].equals("Inspector")){
                        persona = new Inspector(data[0], data[1], data[2], data[3], 
                                data[4], data[5], data[6], data[7], data[8], data[9]);
                    }
                    dispose();
                    new Menu(persona);
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
                dispose();
                Persona NuevoUsuario = new Usuario();
                new Registrar(NuevoUsuario, NuevoUsuario);
            }
        });


        // Mostrar la ventana
        setVisible(true);
    }
    
    private String[] verificarUsuario(String correo, String password){
        for (int i = 0; i < SistemaParqueo.ListaDeUsuarios.size(); i ++){

            if ((SistemaParqueo.ListaDeUsuarios.get(i)[5].equals(password)) && (SistemaParqueo.ListaDeUsuarios.get(i)[7].equals(correo))) {
                return SistemaParqueo.ListaDeUsuarios.get(i);}
        }
        return null;
    }
}

