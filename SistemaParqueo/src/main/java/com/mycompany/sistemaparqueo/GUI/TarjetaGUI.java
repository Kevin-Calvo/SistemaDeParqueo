package com.mycompany.sistemaparqueo.GUI;

import com.mycompany.sistemaparqueo.Clases.*;
import com.mycompany.sistemaparqueo.SistemaParqueo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Iterator;

public class TarjetaGUI extends JFrame {

    public TarjetaGUI(Persona persona) {
        //Obtener tarjeta del usuario
        Tarjeta tarjeta = null;
        for (String[] data : SistemaParqueo.ListaDeTarjetas){
            if (data[0].equals(persona.getIdentificacion())){
                tarjeta = new Tarjeta(data[0], data[1], data[2], data[3]);
            }
        }
        // Configuración de la ventana
        setTitle("Tarjeta de Crédito");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Usamos un layout nulo para controlar las coordenadas manualmente
        setVisible(true);

        // Crear y configurar los JLabel y JTextField
        JLabel numeroLabel = new JLabel("Número:");
        numeroLabel.setBounds(20, 50, 100, 25);
        add(numeroLabel);

        JTextField numeroField = new JTextField();
        numeroField.setBounds(150, 50, 200, 25);
        add(numeroField);

        JLabel fechaVencimientoLabel = new JLabel("Fecha Vencimiento (MM/YY):");
        fechaVencimientoLabel.setBounds(20, 90, 180, 25);
        add(fechaVencimientoLabel);

        JTextField fechaVencimientoField = new JTextField();
        fechaVencimientoField.setBounds(200, 90, 150, 25);
        add(fechaVencimientoField);

        JLabel validacionLabel = new JLabel("Código de Validación:");
        validacionLabel.setBounds(20, 130, 150, 25);
        add(validacionLabel);

        JTextField validacionField = new JTextField();
        validacionField.setBounds(200, 130, 150, 25);
        add(validacionField);
        
        if (tarjeta != null){
            numeroField.setText(tarjeta.getNumero());
            fechaVencimientoField.setText(tarjeta.getFechaVencimiento());
            validacionField.setText(tarjeta.getCodigoValidacion());
        }

        // Botón Guardar
        JButton guardarButton = new JButton("Guardar");
        guardarButton.setBounds(50, 200, 100, 30);
        add(guardarButton);

        // Botón Volver
        JButton volverButton = new JButton("Volver");
        volverButton.setBounds(200, 200, 100, 30);
        add(volverButton);

        // Acción de Guardar
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numero = numeroField.getText();
                String fechaVencimiento = fechaVencimientoField.getText();
                String validacion = validacionField.getText();
                
                if (!validarNumeroFormato(numero, persona)){
                    JOptionPane.showMessageDialog(null, "El numero no puede ser repetido y tiene que ser de 16 caracteres");
                } else if (!validarFecha(fechaVencimiento)){
                    JOptionPane.showMessageDialog(null, "La tarjeta no puede estar vencida y debe ser MM/YY");
                } else if (!validarCodigo(validacion)){
                    JOptionPane.showMessageDialog(null, "El codigo debe ser de 3 digitos exactamente");
                } else {
                    
                    Tarjeta Ntarjeta = new Tarjeta(persona.getIdentificacion(), numero, fechaVencimiento, validacion);
                   
                    Iterator<String[]> iterador = SistemaParqueo.ListaDeTarjetas.iterator();

                    while (iterador.hasNext()) {
                        String[] elemento = iterador.next();
                        if (elemento[0].equals(persona.getIdentificacion())) {
                            iterador.remove(); // Eliminar el elemento "B"
                        }
                    }
                    
                    SistemaParqueo.ListaDeTarjetas.add(Ntarjeta.toArray());
                    SistemaParqueo.controladorArchivos.escribirArchivo(SistemaParqueo.ListaDeTarjetas, "Tarjetas.txt");
                    dispose();
                    new ModificarUsuario(persona);
                }

            }
        });

        // Acción de Volver
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar la ventana actual y volver a la pantalla anterior (puedes modificar esto según tus necesidades)
                dispose(); // Cerrar la ventana
                new ModificarUsuario(persona); // Volver a la ventana principal (puedes ajustarlo a la clase que quieras)
            }
        });
    }
    
    // Validar número de tarjeta de 16 dígitos
    private boolean validarNumeroFormato(String numero, Persona persona) {
        for (String[] data : SistemaParqueo.ListaDeTarjetas){
            if (data[1].equals(numero)) {
                if (!data[0].equals(persona.getIdentificacion())) return false;
            }
        }
        // El número debe ser un valor numérico de 16 dígitos
        return numero.matches("\\d{16}");
    }
 

    // Validar fecha de vencimiento en formato MM/YY
    private boolean validarFecha(String fecha) {
        // La fecha debe tener el formato MM/YY y ser válida
        if (!fecha.matches("(0[1-9]|1[0-2])/\\d{2}")) {
            return false;
        }

        // Extraer el mes y el año
        String[] partes = fecha.split("/");
        int mes = Integer.parseInt(partes[0]);
        int anio = Integer.parseInt(partes[1]) + 2000; // Convierte "YY" a "YYYY"

        // Obtener el año y mes actuales
        LocalDate fechaActual = LocalDate.now();
        int mesActual = fechaActual.getMonthValue();
        int anioActual = fechaActual.getYear();

        // Verificar que la fecha no haya expirado
        return (anio > anioActual) || (anio == anioActual && mes >= mesActual);
    }

    // Validar código de validación de 3 dígitos
    private boolean validarCodigo(String codigo) {
        // El código debe ser un número de exactamente 3 dígitos
        return codigo.matches("\\d{3}");
    }

}

