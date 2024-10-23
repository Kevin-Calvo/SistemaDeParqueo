package com.mycompany.sistemaparqueo.GUI;

import com.mycompany.sistemaparqueo.Clases.Persona;
import com.mycompany.sistemaparqueo.SistemaParqueo;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kevin
 */
public class Desaparcar extends JFrame {
    /**
     * Constructor que crea la ventana de la GUI para mostrar la información de los carros de una persona.
     * 
     * @param persona La persona que posee los carros a mostrar en la GUI.
     */
    public Desaparcar(Persona persona) {
        setTitle("Información de Carros Parqueados");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Crear el modelo de la tabla con las columnas "Carro", "Espacio", "Inicio", "Final"
        String[] columnNames = {"Carro", "Espacio", "Inicio", "Final"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        // Crear una tabla basada en ese modelo
        JTable carTable = new JTable(model);
        
        ArrayList<String> carrosUsuario = new ArrayList<>();
        for (String[] data: SistemaParqueo.ListaDeCarros){
            if(data[0].equals(persona.getIdentificacion())) carrosUsuario.add(data[1]);
        }
        
        for (String[] data : SistemaParqueo.ListaDeCarroParqueo){
            if (data[4].equals("true")){
                if (carrosUsuario.contains(data[0]))
                model.addRow(new Object[]{data[0],data[1], data[2], data[3]});
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

        infoPanel.add(new JLabel("Espacio: "));
        JTextField espacioField = new JTextField(20);
        infoPanel.add(espacioField);

        // Añadir el infoPanel al panel inferior
        bottomPanel.add(infoPanel);
        
        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout());
        
        JButton saveButton = new JButton("Desaparcar");
        saveButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                  String placa = placaField.getText();
                    String espacio = espacioField.getText();

                    if (placa.equals("") && espacio.equals("")) {
                        JOptionPane.showMessageDialog(null, "Placa y Espacio no pueden ser vacíos");
                        return;
                    }

                    // Nueva hora final
                    LocalTime nuevoFinal = LocalTime.now();
                    LocalTime antiguoFinal = null;

                    // Modifica el espacio
                    String parqueo = null;
                    for (String[] data : SistemaParqueo.ListaDeEspacios) {
                        if (data[1].equals(espacio)) {
                            parqueo = data[0];
                            data[2] = "true";
                            data[3] = "00:00"; // Resetea el horario de finalización
                        }
                    }
                    SistemaParqueo.controladorArchivos.escribirArchivo(SistemaParqueo.ListaDeEspacios, "espacios.txt");
                    int precio = 0;
                    float nuevoDinero = 0;
                    // Busca y actualiza la información de CarroParqueo
                    for (String[] data : SistemaParqueo.ListaDeCarroParqueo) {
                        if (data[4].equals("true") && data[0].equals(placa) && data[1].equals(espacio)) {
                            antiguoFinal = LocalTime.parse(data[3]); // Asegúrate de asignar antiguoFinal antes de usarlo

                            // Calcula la diferencia de tiempo solo si antiguoFinal es válido
                            if (antiguoFinal != null) {
                                Duration duracion = Duration.between(antiguoFinal, nuevoFinal);
                                int minutosDeDiferencia = (int) duracion.toMinutes();

                                // Calcula el precio del parqueo
                                precio = 0;
                                for (String[] espacioData : SistemaParqueo.ListaDeParqueos) {
                                    if (espacioData[0].equals(parqueo)) {
                                        precio = Integer.parseInt(espacioData[3]);
                                    }
                                }

                                nuevoDinero = minutosDeDiferencia * precio;
                                float nuevoPrecio = Float.parseFloat(data[5]) - nuevoDinero;
                                data[3] = String.valueOf(nuevoFinal); // Actualiza la hora final
                                data[5] = String.valueOf(nuevoPrecio); // Actualiza el precio
                                data[4] = "false"; // Marca el carro como "desaparcado"
                            } else {
                                JOptionPane.showMessageDialog(null, "No se pudo encontrar la hora de salida previa");
                                return;
                            }
                        }
                    }
                    SistemaParqueo.controladorArchivos.escribirArchivo(SistemaParqueo.ListaDeCarroParqueo, "carroparqueo.txt");

                    // Actualiza el estado del carro
                    for (String[] data : SistemaParqueo.ListaDeCarros) {
                        if (data[1].equals(placa)) {
                            data[4] = "false";
                        }
                    }
                    SistemaParqueo.controladorArchivos.escribirArchivo(SistemaParqueo.ListaDeCarros, "carros.txt");

                    // Si la nueva hora es antes de la antigua, actualiza la persona y parqueo
                    if (antiguoFinal != null && nuevoFinal.isBefore(antiguoFinal)) {
                        for (String[] data : SistemaParqueo.ListaDeParqueos) {
                            if (data[0].equals(parqueo)) {
                                precio = Integer.parseInt(data[3]);
                            }
                        }

                        for (String[] data : SistemaParqueo.ListaDeUsuarios) {
                            if (data[7].equals(persona.getIdentificacion())) {
                                data[9] = String.valueOf(Integer.parseInt(data[9]) + nuevoDinero);
                            }
                        }
                        SistemaParqueo.controladorArchivos.escribirArchivo(SistemaParqueo.ListaDeUsuarios, "usuarios.txt");
                    }

                    JOptionPane.showMessageDialog(null, "Carro desaparcado");
                    dispose();
                    new Menu(persona);

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
        
        buttonPanel.add(saveButton);
        buttonPanel.add(backButton);
        bottomPanel.add(buttonPanel);
        
        // Añadir el bottomPanel al panel principal en la parte inferior (SOUTH)
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        // Añadir el panel principal al JFrame
        add(mainPanel);
    }
}
