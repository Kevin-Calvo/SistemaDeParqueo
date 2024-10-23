package com.mycompany.sistemaparqueo.GUI;

import com.mycompany.sistemaparqueo.Controller.ArchivoController;
import com.mycompany.sistemaparqueo.Clases.Carro;
import com.mycompany.sistemaparqueo.Clases.Espacio;
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
 * Clase que representa la interfaz gráfica para la gestión de carros de una persona en el sistema de parqueo.
 * Permite visualizar, eliminar y agregar carros asociados a la persona.
 * 
 * @author Kevin Yadir Calvo Rodríguez
 */
public class EspaciosGUI extends JFrame {

    /**
     * Constructor que crea la ventana de la GUI para mostrar la información de los espacios de todos los parqueos.
     * 
     * @param persona La persona que posee los carros a mostrar en la GUI.
     */
    public EspaciosGUI(Persona persona) {
        setTitle("Información de Espacios");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Crear el modelo de la tabla con las columnas "Parqueo, Espacio, Disponibilidad
        String[] columnNames = {"Parqueo", "Espacio", "Disponibilidad"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        // Crear una tabla basada en ese modelo
        JTable carTable = new JTable(model);

        for (String[] data : SistemaParqueo.ListaDeEspacios){
                model.addRow(new Object[]{data[0], data[1], data[2]});
            
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

        infoPanel.add(new JLabel("Rango Inicio: "));
        JTextField inicioField = new JTextField(20);
        infoPanel.add(inicioField);

        infoPanel.add(new JLabel("Rango Final: "));
        JTextField finalField = new JTextField(20);
        infoPanel.add(finalField);
      

        // Añadir el infoPanel al panel inferior
        bottomPanel.add(infoPanel);
        
        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton deleteButton = new JButton("Eliminar");
        
        deleteButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String inicio = inicioField.getText();
                String finalr = finalField.getText();
                if (finalr.equals("")) finalr = inicio;
                
                int inicioR = Integer.parseInt(inicio);
                int finalR = Integer.parseInt(finalr);
                
                int respuesta = JOptionPane.showConfirmDialog(
                null,
                "¿Confirmar Borrado?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
            );

            if (respuesta == JOptionPane.YES_OPTION) {
                for (int i = inicioR; i <= finalR; i ++){
                    
                    if (espacioExiste(String.valueOf(i))) {
                        eliminarEspacio(String.valueOf(i));
                    }
                    else {
                        JOptionPane.showMessageDialog(null, i + " no existe" );
                    }
                }
                dispose();
                new EspaciosGUI(persona);
            }
            }
        });
        
        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String parqueo = parqueoField.getText();
                String inicio = inicioField.getText();
                String finalr = finalField.getText();

                // Si el campo finalr está vacío, igualarlo a inicio
                if (finalr.equals("")) finalr = inicio;

                int inicioR, finalR;

                // Validar que inicio y finalr puedan convertirse a enteros
                try {
                    inicioR = Integer.parseInt(inicio);
                    finalR = Integer.parseInt(finalr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Los valores de inicio y final deben ser números enteros.");
                    return;
                }

                // Validar que inicio sea menor o igual a finalr
                if (inicioR > finalR) {
                    JOptionPane.showMessageDialog(null, "El valor de inicio debe ser menor o igual al valor de final.");
                    return;
                }

                // Validar que inicio y finalr sean menores o iguales a 99999
                if (inicioR > 99999 || finalR > 99999) {
                    JOptionPane.showMessageDialog(null, "Los valores de inicio y final deben ser menores o iguales a 99999.");
                    return;
                }

                // Verificar si el parqueo existe
                if (!parqueoExiste(parqueo)) {
                    JOptionPane.showMessageDialog(null, parqueo + " no existe");
                } else {
                    // Crear espacios entre inicioR y finalR
                    for (int i = inicioR; i <= finalR; i++) {
                        if (espacioExiste(String.valueOf(i))) {
                            JOptionPane.showMessageDialog(null, i + " ya existe");
                        } else {
                            crearEspacio(parqueo, String.valueOf(i));
                        }
                    }
                }

                // Cerrar la ventana actual y abrir EspaciosGUI
                dispose();
                new EspaciosGUI(persona);
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
     * Elimina un carro de la lista de carros de la persona.
     * 
     * @param id ID del espacio
     */
    public void eliminarEspacio(String id){
       
        Iterator<String[]> iterador = SistemaParqueo.ListaDeEspacios.iterator();
        while (iterador.hasNext()) {
            String[] elemento = iterador.next();
            if (elemento[1].equals(id)) {
                iterador.remove(); 
            }
        }
        
        ArchivoController.escribirArchivo(SistemaParqueo.ListaDeEspacios, "espacios.txt");
        
    }
    
    /**
     * Valida que el parqueo exista
     * @param id ID del espacio
     * 
     */
    public boolean espacioExiste(String id){
        Iterator<String[]> iterador = SistemaParqueo.ListaDeEspacios.iterator();
        while (iterador.hasNext()) {
            String[] elemento = iterador.next();
            if (elemento[1].equals(id)) {
                return true; 
            }
        }
        
        return false;
    }
    
    /**
     * Retorna valor booleano basado en si existe o no existe el parqueo
     * @param parqueo
     * @return (@code true) si el parqueo ya existe
     */
    public boolean parqueoExiste(String parqueo){
        Iterator<String[]> iterador = SistemaParqueo.ListaDeParqueos.iterator();
        while (iterador.hasNext()) {
            String[] elemento = iterador.next();
            if (elemento[0].equals(parqueo)) {
                return true; 
            }
        }
        
        return false;
    }
    
    /**
     * Crea un nuevo espacio
     * @param parqueo parqueo al que pertenece el espacio
     * @param id identificador del parqueo
     */
    public void crearEspacio(String parqueo, String id){
     Espacio espacio = new Espacio(parqueo, id, true, LocalTime.parse("00:00"));
     SistemaParqueo.ListaDeEspacios.add(espacio.toArray());
     ArchivoController.escribirArchivo(SistemaParqueo.ListaDeEspacios, "espacios.txt");   
    }
}