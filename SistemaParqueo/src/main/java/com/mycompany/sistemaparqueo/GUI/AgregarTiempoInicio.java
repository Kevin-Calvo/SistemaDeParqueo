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

public class AgregarTiempoInicio extends JFrame {
    
    // Constructor de la pantalla
    public AgregarTiempoInicio(Persona persona) {
        // Configuración de la ventana
        setTitle("Agregar más tiempo");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout()); // Usamos GridBagLayout para un layout flexible
        setVisible(true);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Márgenes alrededor de los componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta "Agregar más tiempo"
        JLabel tituloLabel = new JLabel("Agregar más tiempo");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(tituloLabel, gbc);

        // Etiqueta "Espacio" y campo de texto
        JLabel espacioLabel = new JLabel("Espacio:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(espacioLabel, gbc);

        JTextField espacioField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(espacioField, gbc);

        // Botón "Volver"
        JButton volverButton = new JButton("Volver");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(volverButton, gbc);

        // Botón "Seleccionar"
        JButton seleccionarButton = new JButton("Seleccionar");
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(seleccionarButton, gbc);

        // Acción para el botón "Volver"
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cierra la ventana actual
                dispose();
                new Menu(persona);
            }
        });

        // Acción para el botón "Seleccionar"
        seleccionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí iría el código que maneja la selección del espacio
                String espacio = espacioField.getText();
                
                if (validarCarroParqueo(persona, espacio))
                {
                    dispose();
                    new AgregarTiempo(persona, espacio);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Parqueo vencido, no valido");
                }
            }
        });
    }
    
    /**
     * Valida si un carro está parqueado en el espacio asignado a una persona.
     *
     * @param persona La persona dueña del carro.
     * @param espacio El espacio en el que se encuentra parqueado el carro.
     * @return true si el carro está parqueado en el espacio y pertenece a la persona, de lo contrario false.
     */
    public boolean validarCarroParqueo(Persona persona, String espacio){
        for (String[] data: SistemaParqueo.ListaDeCarroParqueo){
            if (data[1].equals(espacio)){
                if (data[4].equals("true")){
                    if(validarCarro(persona, data[0])){
                        if(!validarFecha(data[3])){
                            return true;
                        }
                    }
                }
            }
        }
        
        return false;
    }
    
     /**
     * Valida si un carro pertenece a una persona.
     *
     * @param persona La persona que es dueña del carro.
     * @param carro   El ID o referencia del carro.
     * @return true si el carro pertenece a la persona, de lo contrario false.
     */
    public boolean validarCarro(Persona persona, String carro){
        for (String[] data: SistemaParqueo.ListaDeCarros){
            if(data[1].equals(carro)){
                if(data[0].equals(persona.getIdentificacion())){
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * Valida si la fecha y hora proporcionada es menor que la hora actual.
     * 
     * @param fecha La fecha en formato de hora 
     * @return true si la hora es válida y menor que la hora actual, de lo contrario false.
     */
    public boolean validarFecha(String fecha) {
        try {
            // Convertir el string de la fecha en formato de hora con nanosegundos
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.nnnnnnnnn");
            LocalTime horaIngresada = LocalTime.parse(fecha, formatter);

            // Obtener la hora actual con nanosegundos
            LocalTime horaActual = LocalTime.now();

            // Comparar si la hora ingresada es menor que la hora actual
            return horaIngresada.isBefore(horaActual);
        } catch (DateTimeParseException e) {
            // Si el formato de la fecha es incorrecto, retornamos false
            System.out.println("Formato de fecha incorrecto. Use HH:mm:ss.nnnnnnnnn.");
            return false;
        }
    }    
}


