package com.mycompany.sistemaparqueo.GUI;

import com.mycompany.sistemaparqueo.Clases.Persona;
import com.mycompany.sistemaparqueo.SistemaParqueo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Parquear extends JFrame {

    public Parquear(Persona persona, String espacio) {
        
        String parqueo = null;
        for (String[] data: SistemaParqueo.ListaDeEspacios){
            if (data[1].equals(espacio)) parqueo = data[0];
        }
        
        String minutos = null;
        String precio = null;
        for (String[] data : SistemaParqueo.ListaDeParqueos){
            if (data[0].equals(parqueo)) {
                minutos = data[4];
                precio = data[3];
            }
        }
        
        // Configuración de la ventana
        setTitle("Parquear");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana

        // Crear el panel para añadir componentes
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10)); // Filas, columnas y espacios

        // Etiqueta del espacio
        JLabel espacioLabel = new JLabel("Espacio: " + espacio);
        panel.add(espacioLabel);

        // Etiqueta del precio
        JLabel precioLabel = new JLabel("Precio: " + precio);
        panel.add(precioLabel);

        // Etiqueta y caja de opciones para elegir tiempo
        JLabel tiempoLabel = new JLabel("Tiempo (en minutos):");
        panel.add(tiempoLabel);
        
        int min = Integer.parseInt(minutos);
        
        String[] opcionesTiempo = { String.valueOf(min*1),
                                    String.valueOf(min*2),
                                    String.valueOf(min*3),
                                    String.valueOf(min*4),
                                    String.valueOf(min*5),
                                    String.valueOf(min*6),
                                    String.valueOf(min*7),
                                    String.valueOf(min*8),
                                    String.valueOf(min*9),
                                    String.valueOf(min*10)};
        JComboBox<String> tiempoCombo = new JComboBox<>(opcionesTiempo);
        panel.add(tiempoCombo);

        // Etiqueta y caja de opciones para elegir carro
        JLabel carroLabel = new JLabel("Carro:");
        panel.add(carroLabel);
        
        ArrayList<String> listaopciones = new ArrayList<>();
        for (String[] data : SistemaParqueo.ListaDeCarros){
            if (data[0].equals(persona.getIdentificacion())){
                if(data[4].equals("false")){
                    listaopciones.add(data[1]);
                }
            }
        }
        String[] opcionesCarro = listaopciones.toArray(new String[0]);
        JComboBox<String> carroCombo = new JComboBox<>(opcionesCarro);
        panel.add(carroCombo);

        // Botón "Volver"
        JButton volverButton = new JButton("Volver");
        panel.add(volverButton);
        
        // Acción para el botón Volver
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para volver a la pantalla anterior
                dispose(); 
                new ParquearInicio(persona);
            }
        });

        // Botón "Parquear"
        JButton parquearButton = new JButton("Parquear");
        panel.add(parquearButton);
        String preciofinal = precio;
        parquearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //Logica para ir a pagar y parquear el carro
                dispose();
                PagoVentana pagoVentana = new PagoVentana(persona, espacio, (String) carroCombo.getSelectedItem(),
                        (String) tiempoCombo.getSelectedItem(), preciofinal);
            }
        });

        // Añadir el panel a la ventana
        add(panel);
        setVisible(true);
    }

}
