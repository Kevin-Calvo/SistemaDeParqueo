package com.mycompany.sistemaparqueo.GUI;

import com.mycompany.sistemaparqueo.Clases.Persona;
import com.mycompany.sistemaparqueo.SistemaParqueo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Pantalla de inicio para seleccionar un parqueo.
 * Presenta un campo de texto para ingresar el número de parqueo,
 * y dos botones: uno para seleccionar y otro para volver.
 */
public class ParquearInicio extends JFrame {

    private JTextField parqueoField;
    private JButton seleccionarButton;
    private JButton volverButton;

    /**
     * Constructor que inicializa la interfaz gráfica.
     */
    public ParquearInicio(Persona persona) {
        // Configuración básica de la ventana
        setTitle("Selección de Parqueo");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana
        setVisible(true);
        
        // Crear los componentes
        JLabel label = new JLabel("Seleccione un parqueo:");
        parqueoField = new JTextField(10); // Campo para ingresar el número de parqueo
        seleccionarButton = new JButton("Seleccionar");
        volverButton = new JButton("Volver");

        // Crear un panel para organizar los componentes
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10)); // Layout con 3 filas y 2 columnas

        // Añadir los componentes al panel
        panel.add(label);
        panel.add(parqueoField);
        panel.add(seleccionarButton);
        panel.add(volverButton);

        // Añadir el panel al frame
        add(panel);

        // Acción para el botón Seleccionar
        seleccionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String parqueo = parqueoField.getText();
                if (parqueo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese un número de parqueo.");
                } else {
                    // Lógica para seleccionar el parqueo
                    if (!parqueoExiste(parqueo)){
                        JOptionPane.showMessageDialog(null, "Parqueo no existe.");
                    }
                    else if (!parqueoDisponible(parqueo)){
                        JOptionPane.showMessageDialog(null, "Parqueo no disponible");
                    }
                    else{
                        dispose();
                        new Parquear(persona, parqueo);
                    }
                }
            }
        });

        // Acción para el botón Volver
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para volver a la pantalla anterior
                dispose(); 
                new Menu(persona);
            }
        });
    }
        
    /**
     * Valida si un espacio de parqueo existe.
     * 
     * @param id el identificador del parqueo a validar
     * @return {@code true} si el parqueo existe, {@code false} en caso contrario
     */
    public boolean parqueoExiste(String id) {
        for (String[] data : SistemaParqueo.ListaDeEspacios) {
            if (data[1].equals(id)) { // Comparación correcta de cadenas
                return true;
            }
        }
        return false;
    }
    
    public boolean parqueoDisponible(String id){
        for (String[] data : SistemaParqueo.ListaDeEspacios){
            if (data[1].equals(id)){
                if(data[2].equals("true")){
                    return true;
                }
                else{
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                    try{
                        LocalTime horaFinal = LocalTime.parse(data[3], formatter);
                        if (horaFinal.isBefore(LocalTime.now())) return true;
                        else return false;
                    }
                    catch(DateTimeParseException e){
                        JOptionPane.showMessageDialog(null, "Formato incorrecto.");
                    }
                }
            }
        }
        return false;
    }

}

