package com.mycompany.sistemaparqueo.GUI;

import com.mycompany.sistemaparqueo.Controller.ArchivoController;
import com.mycompany.sistemaparqueo.Clases.Carro;
import com.mycompany.sistemaparqueo.Clases.Persona;
import com.mycompany.sistemaparqueo.Controller.ArchivoController;
import com.mycompany.sistemaparqueo.SistemaParqueo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.table.DefaultTableModel;

/**
 * Clase que representa la interfaz gráfica para la gestión de carros de una persona en el sistema de parqueo.
 * Permite visualizar, eliminar y agregar carros asociados a la persona.
 * 
 * @author Kevin Yadir Calvo Rodríguez
 */
public class CarroGUI extends JFrame {

    /**
     * Constructor que crea la ventana de la GUI para mostrar la información de los carros de una persona.
     * 
     * @param persona La persona que posee los carros a mostrar en la GUI.
     */
    public CarroGUI(Persona persona) {
        setTitle("Información de Carros");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Crear el modelo de la tabla con las columnas "Placa", "Modelo", y "Marca"
        String[] columnNames = {"Placa", "Modelo", "Marca"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        // Crear una tabla basada en ese modelo
        JTable carTable = new JTable(model);

        for (String[] data : SistemaParqueo.ListaDeCarros){
            if (data[0].equals(persona.getIdentificacion())){
                model.addRow(new Object[]{data[1], data[2], data[3]});
            }
        }

        // Panel para la tabla
        JScrollPane scrollPane = new JScrollPane(carTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Panel para los botones y el campo de texto
        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));

        // Panel de información con GridLayout
        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 5, 5)); // 3 filas, 2 columnas, con espacio entre componentes

        // Crear y agregar los JLabels y JTextFields
        infoPanel.add(new JLabel("Placa: "));
        JTextField placaField = new JTextField(20);
        infoPanel.add(placaField);

        infoPanel.add(new JLabel("Modelo: "));
        JTextField modeloField = new JTextField(20);
        infoPanel.add(modeloField);

        infoPanel.add(new JLabel("Marca: "));
        JTextField marcaField = new JTextField(20);
        infoPanel.add(marcaField);

        // Añadir el infoPanel al panel inferior
        bottomPanel.add(infoPanel);
        
        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton deleteButton = new JButton("Eliminar");
        
        deleteButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String placa = placaField.getText();
                int respuesta = JOptionPane.showConfirmDialog(
                null,
                "¿Confirmar Borrado?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
            );

            if (respuesta == JOptionPane.YES_OPTION) {
                eliminarCarro(persona, placa);
            }
            }
        });
        
        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //Datos
                String placa = placaField.getText();
                String modelo = modeloField.getText();
                String marca = marcaField.getText();
        
                if (placa.length() != 6){
                    JOptionPane.showMessageDialog(null, "La placa debe ser de 6 caracteres y no puede ser repetida");
                } else if (modelo.length() > 15) {
                    JOptionPane.showMessageDialog(null, "Modelo debe ser menor a 15 caracteres");
                } else if (marca.length() > 15){
                    JOptionPane.showMessageDialog(null, "Marca debe ser menor a 15 caracteres");
                } else {
                    guardarCarro(persona,placa,modelo,marca);
                }
            }
        });
        
        JButton backButton = new JButton("Volver");
        backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new Menu(persona);
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
     * Guarda un nuevo carro para la persona en el sistema.
     * Si el carro ya existe, se reemplaza la entrada anterior.
     * 
     * @param persona La persona que posee el carro.
     * @param placa La placa del carro.
     * @param modelo El modelo del carro.
     * @param marca La marca del carro.
     */
    public void guardarCarro(Persona persona, String placa, String modelo, String marca){
        Iterator<String[]> iterador = SistemaParqueo.ListaDeCarros.iterator();

        while (iterador.hasNext()) {
            String[] elemento = iterador.next();
            if (elemento[1].equals(placa)) {
                iterador.remove(); 
            }
        }
                
        Carro carro = new Carro(persona.getIdentificacion(),placa);
        if (!modelo.equals("")) carro.setModelo(modelo); 
        if (!marca.equals("")) carro.setMarca(marca);
        
        SistemaParqueo.ListaDeCarros.add(carro.toArray());
        ArchivoController.escribirArchivo(SistemaParqueo.ListaDeCarros, "Carros.txt");
        dispose();
        new CarroGUI(persona);
    }
    
    /**
     * Elimina un carro de la lista de carros de la persona.
     * 
     * @param persona La persona que posee el carro.
     * @param placa La placa del carro a eliminar.
     */
    public void eliminarCarro(Persona persona, String placa){
        Iterator<String[]> iterador = SistemaParqueo.ListaDeCarros.iterator();
        while (iterador.hasNext()) {
            String[] elemento = iterador.next();
            if (elemento[1].equals(placa)) {
                iterador.remove(); 
            }
        }
        dispose();
        new CarroGUI(persona);
    }
}
