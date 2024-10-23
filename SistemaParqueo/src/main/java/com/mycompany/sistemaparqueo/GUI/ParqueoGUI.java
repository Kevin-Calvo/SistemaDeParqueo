package com.mycompany.sistemaparqueo.GUI;

import com.mycompany.sistemaparqueo.Controller.ArchivoController;
import com.mycompany.sistemaparqueo.Clases.Carro;
import com.mycompany.sistemaparqueo.Clases.Parqueo;
import com.mycompany.sistemaparqueo.Clases.Persona;
import com.mycompany.sistemaparqueo.Controller.ArchivoController;
import com.mycompany.sistemaparqueo.SistemaParqueo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.table.DefaultTableModel;

/**
 * Clase que representa la interfaz gráfica para la gestión de parqueos.
 * Permite visualizar, eliminar y agregar carros asociados a la persona.
 * 
 * @author Kevin Yadir Calvo Rodríguez
 */
public class ParqueoGUI extends JFrame {

    /**
     * Constructor que crea la ventana de la GUI para mostrar la información de los carros de una persona.
     * 
     * @param persona La persona que posee los carros a mostrar en la GUI.
     */
    public ParqueoGUI(Persona persona) {
        setTitle("Información de Parqueos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Crear el modelo de la tabla con las columnas "Placa", "Modelo", y "Marca"
        String[] columnNames = {"Parqueo", "Inicio", "Final", "Precio", "Minutos", "Multa"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        // Crear una tabla basada en ese modelo
        JTable carTable = new JTable(model);

        for (String[] data : SistemaParqueo.ListaDeParqueos){
                model.addRow(new Object[]{data[0], data[1], data[2], data[3], data[4], data[5]});
            
        }

        // Panel para la tabla
        JScrollPane scrollPane = new JScrollPane(carTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Panel para los botones y el campo de texto
        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));

        // Panel de información con GridLayout
        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 5, 5)); // 3 filas, 2 columnas, con espacio entre componentes

        // Crear y agregar los JLabels y JTextFields
        infoPanel.add(new JLabel("Parqueo: "));
        JTextField parqueoField = new JTextField(20);
        infoPanel.add(parqueoField);

        infoPanel.add(new JLabel("HoraInicio: "));
        JTextField inicioField = new JTextField(20);
        infoPanel.add(inicioField);

        infoPanel.add(new JLabel("HoraFinal: "));
        JTextField finalField = new JTextField(20);
        infoPanel.add(finalField);
        
        infoPanel.add(new JLabel("Precio: "));
        JTextField precioField = new JTextField(20);
        infoPanel.add(precioField);
        
        infoPanel.add(new JLabel("Minutos: "));
        JTextField minutosField = new JTextField(20);
        infoPanel.add(minutosField);
        
        infoPanel.add(new JLabel("Multa: "));
        JTextField multaField = new JTextField(20);
        infoPanel.add(multaField);

        // Añadir el infoPanel al panel inferior
        bottomPanel.add(infoPanel);
        
        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton deleteButton = new JButton("Eliminar");
        
        deleteButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String id = parqueoField.getText();
                int respuesta = JOptionPane.showConfirmDialog(
                null,
                "¿Confirmar Borrado?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
            );

            if (respuesta == JOptionPane.YES_OPTION) {
               if(parqueoExiste(id)) eliminarParqueo(persona, id);
               else{
                   JOptionPane.showMessageDialog(null, "Parqueo no existe");
               }
            }
            }
        });
        
        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String parqueo = parqueoField.getText();
                String horaInicio = inicioField.getText();
                String horaFinal = finalField.getText();
                String precio = precioField.getText();
                String minuto = minutosField.getText();
                String multa = multaField.getText();

                // Verificar que ningún campo esté vacío
                if (parqueo.isEmpty() || horaInicio.isEmpty() || horaFinal.isEmpty() || precio.isEmpty() || minuto.isEmpty() || multa.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos");
                    return;
                }

                // Validar formato "HH:mm" usando expresión regular
                if (!horaInicio.matches("^\\d{2}:\\d{2}$") || !horaFinal.matches("^\\d{2}:\\d{2}$")) {
                    JOptionPane.showMessageDialog(null, "Hora inicio y final deben estar en formato HH:MM");
                    return;
                }

                // Intentar convertir a LocalTime
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime Inicio, Final;

                try {
                    Inicio = LocalTime.parse(horaInicio, formatter);
                    Final = LocalTime.parse(horaFinal, formatter);

                    // Validar que precio, multa y minutos puedan convertirse a enteros
                    int precioInt, minutosInt, multaInt;
                    try {
                        precioInt = Integer.parseInt(precio);
                        minutosInt = Integer.parseInt(minuto);
                        multaInt = Integer.parseInt(multa);

                        // Comparar las horas y otras validaciones
                        if (Inicio.isAfter(Final)) {
                            JOptionPane.showMessageDialog(null, "Hora Inicio debe ser menor a Hora Final");
                        } else if (!esPar(precioInt)) {
                            JOptionPane.showMessageDialog(null, "Precio debe ser un número par");
                        } else {
                            guardarParqueo(persona, parqueo, Inicio, Final, precio, minuto, multa);
                        }

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Precio, minutos y multa deben ser números enteros");
                    }

                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(null, "Hora Inicio o Hora Final en formato incorrecto");
                }
            }
        });

        
        JButton backButton = new JButton("Volver");
        backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });
        
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(backButton);
        bottomPanel.add(buttonPanel);

        // Añadir el bottomPanel al panel principal en la parte inferior (SOUTH)
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        // Añadir el panel principal al JFrame
        add(mainPanel);
    }
    
     /**
     * Método que valida si un número es par.
     *
     * @param numero El número a validar.
     * @return true si el número es par, false si es impar.
     */
    public static boolean esPar(int numero) {
        // Verifica si el residuo de dividir el número entre 2 es 0.
        return numero % 2 == 0;
    }
    
    public boolean parqueoExiste(String id){
        Iterator<String[]> iterador = SistemaParqueo.ListaDeParqueos.iterator();
        while (iterador.hasNext()) {
            String[] elemento = iterador.next();
            if (elemento[0].equals(id)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Guarda un nuevo carro para la persona en el sistema.
     * Si el carro ya existe, se reemplaza la entrada anterior.
     * 
     * @param persona La persona que posee el carro.
     * @param parqueo identificador de parqueo
     * @param inicio hora de inicio de cobro
     * @param final hora final de cobor
     * @param precio Precio por hora
     * @param minuto Cantidad minima de minutos que se pueden comprar.
     * @param multa Precio de las multas
     */
    public void guardarParqueo(Persona persona, String id, LocalTime inicio, LocalTime horaFinal, String precio, 
        String minuto, String multa){
            
        Iterator<String[]> iterador = SistemaParqueo.ListaDeParqueos.iterator();

        while (iterador.hasNext()) {
            String[] elemento = iterador.next();
            if (elemento[0].equals(id)) {
                iterador.remove(); 
            }
        }
                
        Parqueo parqueo = new Parqueo(id, inicio, horaFinal, Integer.parseInt(precio), Integer.parseInt(minuto), Integer.parseInt(multa));
        
        SistemaParqueo.ListaDeParqueos.add(parqueo.toArray());
        ArchivoController.escribirArchivo(SistemaParqueo.ListaDeParqueos, "parqueos.txt");
        dispose();
        new ParqueoGUI(persona);
    }
    
    /**
     * Elimina un carro de la lista de carros de la persona.
     * 
     * @param persona La persona que posee el carro.
     * @param placa La placa del carro a eliminar.
     */
    public void eliminarParqueo(Persona persona, String id){
        Iterator<String[]> iterador = SistemaParqueo.ListaDeParqueos.iterator();
        while (iterador.hasNext()) {
            String[] elemento = iterador.next();
            if (elemento[0].equals(id)) {
                iterador.remove(); 
            }
        }
        iterador = SistemaParqueo.ListaDeEspacios.iterator();
        while (iterador.hasNext()) {
            String[] elemento = iterador.next();
            if (elemento[0].equals(id)) {
                iterador.remove(); 
            }
        }
        
        ArchivoController.escribirArchivo(SistemaParqueo.ListaDeParqueos, "parqueos.txt");
        ArchivoController.escribirArchivo(SistemaParqueo.ListaDeEspacios, "espacios.txt");
        dispose();
        new ParqueoGUI(persona);
    }
    
}

