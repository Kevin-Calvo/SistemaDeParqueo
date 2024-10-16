package com.mycompany.sistemaparqueo.GUI;

import com.mycompany.sistemaparqueo.Clases.Persona;
import com.mycompany.sistemaparqueo.SistemaParqueo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CambioPin extends JFrame {

    public CambioPin(Persona persona) {
        // Configuración de la ventana principal
        setTitle("Cambio de Pin");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana
        setVisible(true);

        // Crear el panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10)); // Usar un GridLayout para alinear componentes en una cuadrícula

        // Crear etiquetas y campos de entrada
        JLabel tempPinLabel = new JLabel("Pin Temporal:");
        JPasswordField tempPinField = new JPasswordField();

        JLabel newPinLabel = new JLabel("Nuevo Pin:");
        JPasswordField newPinField = new JPasswordField();

        // Botón para confirmar el cambio de pin
        JButton cambiarButton = new JButton("Cambiar Pin");

        // Agregar los componentes al panel
        panel.add(tempPinLabel);
        panel.add(tempPinField);
        panel.add(newPinLabel);
        panel.add(newPinField);

        // Crear un panel inferior para el botón
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(cambiarButton);

        // Agregar los paneles a la ventana
        add(panel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Acción del botón para capturar los pines ingresados
        cambiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los pines ingresados
                String tempPin = new String(tempPinField.getPassword());
                String newPin = new String(newPinField.getPassword());

                if (!persona.getPassword().equals(tempPin)){
                    JOptionPane.showMessageDialog(null, "PIN TEMPORAL INCORRECTO");
                } else if (!validarPin(newPin)){
                    JOptionPane.showMessageDialog(null, "NUEVO PIN DEBE CONTENER 4 CARACTERES NUMERICOS");
                } else {
                    persona.setPassword(String.valueOf(newPin));
                    
                for (String[] data : SistemaParqueo.ListaDeUsuarios){
                    if (data[7].equals(persona.getIdentificacion())) data[5] = newPin; 
                }
                
                SistemaParqueo.controladorArchivos.escribirArchivo(SistemaParqueo.ListaDeUsuarios, "usuarios.txt");
                    dispose();
                    new ModificarUsuario(persona);
                }
            }
        });
        
        
    }
    
     private boolean validarPin(String pin) {
        return pin.length() == 4 && pin.matches("\\d{4}");
    }
}

