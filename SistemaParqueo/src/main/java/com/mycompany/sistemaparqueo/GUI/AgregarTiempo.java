package com.mycompany.sistemaparqueo.GUI;

import com.mycompany.sistemaparqueo.Clases.Persona;
import com.mycompany.sistemaparqueo.SistemaParqueo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AgregarTiempo extends JFrame {

    public AgregarTiempo(Persona persona, String espacio) {
        
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
        
        String placa = null;
        for (String[] data: SistemaParqueo.ListaDeCarroParqueo){
            if(data[1].equals(espacio)) placa = data[0];
        }
        
        // Configuración de la ventana
        setTitle("Agregar Tiempo");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana

        // Crear el panel para añadir componentes
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10)); // Filas, columnas y espacios

        // Etiqueta del espacio
        JLabel espacioLabel = new JLabel("Espacio: " + espacio);
        panel.add(espacioLabel);

        // Etiqueta de la placa
        JLabel placaLabel = new JLabel("Placa: " + placa);
        panel.add(placaLabel);
        
        // Etiqueta de la placa
        JLabel rellenoLabel = new JLabel("");
        panel.add(rellenoLabel);
        
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

        // Botón "Volver"
        JButton volverButton = new JButton("Volver");
        panel.add(volverButton);
        
        // Acción para el botón Volver
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para volver a la pantalla anterior
                dispose(); 
                new AgregarTiempoInicio(persona);
            }
        });

        // Botón "Parquear"
        JButton parquearButton = new JButton("AgregarTiempo");
        panel.add(parquearButton);
        String preciofinal = precio;
        String placaR = placa;
        parquearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //Logica para ir a pagar y parquear el carro
                dispose();
                String minute = (String) tiempoCombo.getSelectedItem();
                new PagoTiempo(persona, placaR, espacio, minute, preciofinal);
            }
        });

        // Añadir el panel a la ventana
        add(panel);
        setVisible(true);
    }

}
