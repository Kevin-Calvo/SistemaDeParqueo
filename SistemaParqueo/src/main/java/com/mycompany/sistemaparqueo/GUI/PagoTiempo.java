package com.mycompany.sistemaparqueo.GUI;

import com.mycompany.sistemaparqueo.Clases.CarroParqueo;
import com.mycompany.sistemaparqueo.Clases.Persona;
import com.mycompany.sistemaparqueo.SistemaParqueo;
import java.awt.Component;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


/**
 *
 * @author kevin
 */
public class PagoTiempo extends JFrame{
    
    public PagoTiempo(Persona persona, String carro, String espacio, String minutos, String precio){
        setTitle("PAGO TIEMPO ADICIONAL"); // Título de la ventana
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
                    
                    JOptionPane.showMessageDialog(null, "Parte del pago realizado con tarjeta.");
                    parquear(carro,espacio,minutos,total);
                    dispose();
                    new Menu(persona);
                    // Logica para confirmar carro parqueado
                }
                else{
                    JOptionPane.showMessageDialog(null, "No tiene minutos disponibles");
                }
            }
        });

        // Acción para pagar solo con tarjeta
        pagarConTarjetaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parquear(carro, espacio, minutos, total);
                JOptionPane.showMessageDialog(null, "Pago realizado con tarjeta.");
                dispose();
                new Menu(persona);
            }
        });

        // Acción para volver
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cerrar la ventana de pago
                new AgregarTiempo(persona, espacio);
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
        LocalTime horaFinalVieja = null;
        int minutosASumar = Integer.parseInt(minutos);
        
        for (String[] data: SistemaParqueo.ListaDeCarroParqueo){
            if (data[1].equals(espacio)){
                if (data[4].equals("true")){
                    // Convertir el string de la fecha en formato de hora con nanosegundos
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.nnnnnnnnn");
                    horaFinalVieja = LocalTime.parse(data[3], formatter);
                    data[3] = horaFinalVieja.plusMinutes(minutosASumar).toString();
                }
            }
        }
        
       // Convertimos los minutos de String a int
        LocalTime finalHora = horaFinalVieja.plusMinutes(minutosASumar);
        // Crea una instancia CarroParqueo   
        SistemaParqueo.controladorArchivos.escribirArchivo(SistemaParqueo.ListaDeCarroParqueo, "carroparqueo.txt");
       
        // Marca parqueo como ocupado y pone hora final
        for (String[] data: SistemaParqueo.ListaDeEspacios){
            if (data[1].equals(espacio)){
                data[3] = finalHora.toString();
            }
        }
        
        SistemaParqueo.controladorArchivos.escribirArchivo(SistemaParqueo.ListaDeEspacios, "espacios.txt");
    }
}
