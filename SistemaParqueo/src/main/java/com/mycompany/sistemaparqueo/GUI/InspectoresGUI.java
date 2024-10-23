package com.mycompany.sistemaparqueo.GUI;

import com.mycompany.sistemaparqueo.Clases.Persona;
import com.mycompany.sistemaparqueo.SistemaParqueo;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Clase que representa la interfaz gráfica para visualizar los inspectores
 * en el sistema de parqueo. Muestra una tabla con los datos de los inspectores
 * registrados y proporciona un botón para regresar al menú principal.
 * 
 * <p>Esta clase extiende {@link JFrame} y es responsable de la creación de la
 * ventana y su contenido, incluyendo una tabla que muestra los inspectores y
 * un botón para navegar de vuelta al menú.</p>
 * 
 * @author kevin
 */
public class InspectoresGUI extends JFrame {

    /**
     * Constructor que inicializa la interfaz gráfica para ver los inspectores.
     * Crea una ventana con un modelo de tabla que muestra los inspectores y un botón
     * para regresar al menú principal.
     * 
     * @param persona El objeto {@link Persona} que representa al usuario que ha iniciado sesión.
     */
    public InspectoresGUI(Persona persona) {
        setTitle("Ver inspectores");
        setSize(900, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Crear el modelo de la tabla con las columnas "Identificación", "Terminal", "Nombre", "Apellido", "Correo", "Teléfono", "Dirección", "Fecha Ingreso"
        String[] columnNames = {"Identificacion", "Terminal", "Nombre", "Apellido", "Correo", "Telefono", "Direccion", "Fecha Ingreso"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        // Crear una tabla basada en ese modelo
        JTable carTable = new JTable(model);

        // Poblamos la tabla con los datos de los inspectores desde la lista de usuarios
        for (String[] data : SistemaParqueo.ListaDeUsuarios) {
            if (data[0].equals("Inspector")) {
                model.addRow(new Object[]{data[7], data[9], data[1], data[2], data[4], data[3], data[6], data[8]});
            }
        }

        // Panel para la tabla
        JScrollPane scrollPane = new JScrollPane(carTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Panel para los botones y el campo de texto
        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        JPanel buttonPanel = new JPanel(new FlowLayout());
        
        // Botón de volver
        JButton backButton = new JButton("Volver");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // Cerrar la ventana actual
                new Menu(persona);  // Crear una nueva instancia del menú principal
            }
        });
        
        // Añadir el botón al panel
        buttonPanel.add(backButton);
        bottomPanel.add(buttonPanel);
        
        // Añadir el panel de botones al panel principal
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Añadir el panel principal al JFrame
        add(mainPanel);
    }
}
