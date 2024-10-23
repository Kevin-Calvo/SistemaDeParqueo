package com.mycompany.sistemaparqueo.GUI;

import com.mycompany.sistemaparqueo.Clases.CarroParqueo;
import com.mycompany.sistemaparqueo.Clases.Persona;
import com.mycompany.sistemaparqueo.SistemaParqueo;
import java.awt.Component;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

public class PagoVentana extends JFrame {
    
    public PagoVentana(Persona persona, String espacio, String carro, String minutos, String precio) {
        setTitle("PAGO"); // Título de la ventana
        setSize(300, 250); // Tamaño de la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cerrar ventana al hacer clic en cerrar

        // Crear un panel para agregar los componentes
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Diseño vertical

        // Etiqueta de título
        JLabel titleLabel = new JLabel("PAGO");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar etiqueta
        panel.add(titleLabel);
        
         // Etiqueta de total
         float min = Float.parseFloat(minutos) / 60;
         int price = Integer.parseInt(precio);
         float total = min * price;
        JLabel totalLabel = new JLabel("Total a pagar: " + String.valueOf(total));
        totalLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar etiqueta
        panel.add(totalLabel);

        // Etiqueta de minutos disponibles
        JLabel minutosLabel = new JLabel("Minutos disponibles: " + persona.getDinero()); // Mostrar minutos disponibles
        minutosLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar etiqueta
        panel.add(minutosLabel);

        // Crear botones
        JButton pagarConMinutosButton = new JButton("Pagar con minutos");
        JButton pagarConTarjetaButton = new JButton("Pagar solo con tarjeta");
        JButton volverButton = new JButton("Volver");

        // Acción para pagar con minutos
        pagarConMinutosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para pagar con minutos 
                Float disponible = Float.parseFloat(persona.getDinero());
                if(disponible != 0){
                    if (disponible > total) persona.setDinero(String.valueOf(disponible - total));
                    else persona.setDinero(String.valueOf(0));
                    
                    for (String[] data: SistemaParqueo.ListaDeUsuarios){
                        if (data[7].equals(persona.getIdentificacion())){
                            data[9] = persona.getDinero();
                        }
                    }
                    SistemaParqueo.controladorArchivos.escribirArchivo(SistemaParqueo.ListaDeUsuarios, "usuarios.txt");
                    
                    JOptionPane.showMessageDialog(PagoVentana.this, "Parte del pago realizado con tarjeta.");
                    parquear(carro,espacio,minutos,total);
                    dispose();
                    new Menu(persona);
                    // Logica para confirmar carro parqueado
                }
                else{
                    JOptionPane.showMessageDialog(PagoVentana.this, "No tiene minutos disponibles");
                }
            }
        });

        // Acción para pagar solo con tarjeta
        pagarConTarjetaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para pagar solo con tarjeta 
                parquear(carro, espacio, minutos, total);
                JOptionPane.showMessageDialog(PagoVentana.this, "Pago realizado con tarjeta.");
                dispose();
                new Menu(persona);
            }
        });

        // Acción para volver
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cerrar la ventana de pago
                new Parquear(persona, espacio);
            }
        });

        // Agregar botones al panel
        pagarConMinutosButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        pagarConTarjetaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        volverButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(pagarConMinutosButton);
        panel.add(pagarConTarjetaButton);
        panel.add(volverButton);

        // Agregar el panel a la ventana
        add(panel);
        setVisible(true); // Hacer la ventana visible
    }
    
    public void parquear(String carro, String espacio, String minutos, float total){
       // Convertimos los minutos de String a int
        int minutosASumar = Integer.parseInt(minutos);
        LocalTime finalHora = LocalTime.now().plusMinutes(minutosASumar);
        
        // Crea una instancia CarroParqueo
        CarroParqueo relacion = new CarroParqueo(carro, espacio, 
            LocalTime.now(), finalHora, true, total);
        
        SistemaParqueo.ListaDeCarroParqueo.add(relacion.toArray());
        SistemaParqueo.controladorArchivos.escribirArchivo(SistemaParqueo.ListaDeCarroParqueo, "carroparqueo.txt");
        
        // Marca carro como parqueado
        for (String[] data: SistemaParqueo.ListaDeCarros){
            if (data[1].equals(carro)) data[4] = "true";
        }
        
        SistemaParqueo.controladorArchivos.escribirArchivo(SistemaParqueo.ListaDeCarros, "carros.txt");
        
        // Marca parqueo como ocupado y pone hora final
        for (String[] data: SistemaParqueo.ListaDeEspacios){
            if (data[1].equals(espacio)){
                data[2] = "false";
                data[3] = finalHora.toString();
            }
        }
        SistemaParqueo.controladorArchivos.escribirArchivo(SistemaParqueo.ListaDeEspacios, "espacios.txt");
    }
}
