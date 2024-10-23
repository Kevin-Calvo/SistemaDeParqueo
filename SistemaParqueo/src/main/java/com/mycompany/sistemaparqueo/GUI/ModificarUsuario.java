package com.mycompany.sistemaparqueo.GUI;

import com.mycompany.sistemaparqueo.Clases.*;
import com.mycompany.sistemaparqueo.SistemaParqueo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase representa una ventana para modificar los datos de un usuario.
 * Extiende JFrame para crear una interfaz gráfica que permite editar la información
 * de una persona en el sistema.
 * 
 * @author Kevin Yadir Calvo Rodriguez
 */
public class ModificarUsuario extends JFrame {

    /**
     * Constructor de la clase ModificarUsuario.
     * 
     * @param persona El objeto Persona que contiene la información del usuario
     *                que se desea modificar. Esta información puede ser utilizada
     *                para cargar los datos actuales del usuario en los campos de la interfaz.
     */
    public ModificarUsuario(Persona persona) {
        // Configuración de la ventana
        setTitle("Modificar Usuario");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Usar layout nulo para posicionar los componentes por coordenadas

        // Crear los JLabels y JTextFields, y añadirlos justo después de su creación
        JLabel tipoLabel = new JLabel("Tipo:");
        tipoLabel.setBounds(20, 20, 100, 30);
        add(tipoLabel);
        
        JLabel tipoField = new JLabel();
        tipoField.setBounds(150, 20, 200, 30);
        tipoField.setText(persona.getTipo());
        add(tipoField);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(20, 60, 100, 30);
        add(nombreLabel);

        JTextField nombreField = new JTextField();
        nombreField.setBounds(150, 60, 200, 30);
        add(nombreField);
        nombreField.setText(persona.getNombre());

        JLabel apellidoLabel = new JLabel("Apellido:");
        apellidoLabel.setBounds(20, 100, 100, 30);
        add(apellidoLabel);

        JTextField apellidoField = new JTextField();
        apellidoField.setBounds(150, 100, 200, 30);
        add(apellidoField);
        apellidoField.setText(persona.getApellido());

        JLabel telefonoLabel = new JLabel("Teléfono:");
        telefonoLabel.setBounds(20, 140, 100, 30);
        add(telefonoLabel);

        JTextField telefonoField = new JTextField();
        telefonoField.setBounds(150, 140, 200, 30);
        add(telefonoField);
        telefonoField.setText(persona.getTelefono());
        
        JLabel correoLabel = new JLabel("Correo:");
        correoLabel.setBounds(20, 180, 100, 30);
        add(correoLabel);

        JTextField correoField = new JTextField();
        correoField.setBounds(150, 180, 200, 30);
        add(correoField);
        correoField.setText(persona.getCorreo());

        JLabel direccionLabel = new JLabel("Dirección:");
        direccionLabel.setBounds(20, 220, 100, 30);
        add(direccionLabel);

        JTextField direccionField = new JTextField();
        direccionField.setBounds(150, 220, 200, 30);
        add(direccionField);
        direccionField.setText(persona.getDireccion());

        JLabel identificacionLabel = new JLabel("Identificación:");
        identificacionLabel.setBounds(20, 260, 100, 30);
        add(identificacionLabel);

        JTextField identificacionField = new JTextField();
        identificacionField.setBounds(150, 260, 200, 30);
        add(identificacionField);
        identificacionField.setText(persona.getIdentificacion());

        JLabel fechaIngresoLabel = new JLabel("Fecha de Ingreso:");
        fechaIngresoLabel.setBounds(20, 300, 120, 30);
        add(fechaIngresoLabel);

        JLabel fechaIngresoField = new JLabel();
        fechaIngresoField.setBounds(150, 300, 200, 30);
        add(fechaIngresoField);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fecha = persona.getfechaIngreso().format(formatter);
        fechaIngresoField.setText(fecha);

        // Botones ------------------------------------
        JButton borrarButton = new JButton("Borrar");
        borrarButton.setBounds(50, 440, 120, 30);
        add(borrarButton);
        
        borrarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int respuesta = JOptionPane.showConfirmDialog(
                null,
                "¿Confirmar Borrado?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
            );

            if (respuesta == JOptionPane.YES_OPTION) {
                // Si el usuario confirma, cerrar la ventana y abrir PantallaInicio
                Iterator<String[]> iterador = SistemaParqueo.ListaDeUsuarios.iterator();

                while (iterador.hasNext()) {
                    String[] elemento = iterador.next();
                    if (elemento[7].equals(persona.getIdentificacion())) {
                        iterador.remove(); // Eliminar el elemento "B"
                    }
                }
                SistemaParqueo.controladorArchivos.escribirArchivo(SistemaParqueo.ListaDeUsuarios, "Usuarios.txt");
                try {
                    SistemaParqueo.ListaDeUsuarios = SistemaParqueo.controladorArchivos.leerDesdeArchivo("Usuarios.txt");
                } catch (IOException ex) {
                    Logger.getLogger(ModificarUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
                dispose();
                new PantallaInicio();
            } else {
                // Si el usuario cancela, no hacer nada
                System.out.println("Borrado cancelado");
            }
        }
        
       
        });        
        
        JButton volverButton = new JButton("Volver");
        volverButton.setBounds(50, 480, 120, 30);
        add(volverButton);
        
        volverButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new Menu(persona);
            }
        });

        JButton guardarButton = new JButton("Guardar Cambios");
        guardarButton.setBounds(230, 480, 150, 30);
        add(guardarButton);
        
        JButton passwordButton = new JButton("Cambiar pin");
        passwordButton.setBounds(230, 440, 150, 30);
        add(passwordButton);
        
        passwordButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                
                new CambioPin(persona, " ", null);
            }
        });

        // Hacer visible la ventana
        setVisible(true);
        
        guardarButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
            String nombre = nombreField.getText();
            String apellido = apellidoField.getText();
            String telefono = telefonoField.getText();
            String correo = correoField.getText();
            String direccion = direccionField.getText();
            String id = identificacionField.getText();

             // Validaciones
            if (!validarNombre(nombre)) {
                JOptionPane.showMessageDialog(null, "El nombre debe tener entre 2 y 20 caracteres.");
            } else if (!validarApellido(apellido)) {
                JOptionPane.showMessageDialog(null, "El apellido debe tener entre 1 y 40 caracteres.");
            } else if (!validarTelefono(telefono)) {
                JOptionPane.showMessageDialog(null, "El teléfono debe tener 8 caracteres numéricos.");
            } else if (!validarCorreo(correo)) {
                JOptionPane.showMessageDialog(null, "El correo debe estar en formato parte1@parte2, donde parte1 y parte2 tienen al menos 3 caracteres.");
            } else if (!validarDireccion(direccion)) {
                JOptionPane.showMessageDialog(null, "La dirección debe tener entre 5 y 60 caracteres.");
            } else if (!validarIdentificacion(id, persona)) {
                JOptionPane.showMessageDialog(null, "La identificación no puede ser repetida y debe tener entre 2 y 25 caracteres.");
            } else {

                // Si el usuario confirma, cerrar la ventana y abrir PantallaInicio
                    Iterator<String[]> iterador = SistemaParqueo.ListaDeUsuarios.iterator();

                    while (iterador.hasNext()) {
                        String[] elemento = iterador.next();
                        if (elemento[7].equals(persona.getIdentificacion())) {
                            iterador.remove(); // Eliminar el elemento "B"
                        }
                    }

                persona.setNombre(nombre);
                persona.setApellido(apellido);
                persona.setTelefono(telefono);
                persona.setCorreo(correo);
                persona.setDireccion(direccion);
                persona.setIdentificacion(id);

                SistemaParqueo.ListaDeUsuarios.add(persona.toArray());
                SistemaParqueo.controladorArchivos.escribirArchivo(SistemaParqueo.ListaDeUsuarios, "Usuarios.txt");
                JOptionPane.showMessageDialog(null, "Usuario modificado correctamente.");
                dispose();
                new Menu(persona); // Suponiendo que Menu es otra pantalla
            }
                }
        });
        
         if (persona.getTipo().equals("Usuario")){
            JLabel tarjetaLabel = new JLabel("Numero de Tarjeta: ");
            tarjetaLabel.setBounds(20,340,120,25);
            add(tarjetaLabel);
            
            JLabel tarjetaLabel2 = new JLabel(" hola ");
            tarjetaLabel2.setBounds(150,340,100,25);
            add(tarjetaLabel2);
            
            JButton tarjetaButton = new JButton("Tarjeta");
            tarjetaButton.setBounds(50, 400, 120, 30);
            add(tarjetaButton);
            
            tarjetaButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new TarjetaGUI(persona);
            }
        });
         }
         if (persona.getTipo().equals("Inspector")){
             JLabel terminalLabel = new JLabel("Terminal:");
            terminalLabel.setBounds(20, 340, 100, 25);
            add(terminalLabel);

            JLabel terminalText = new JLabel();
            terminalText.setBounds(150, 340, 120, 25);
            add(terminalText);
            terminalText.setText(persona.getTerminal());
         
         }
    }
    

    // Métodos de validación de datos
    /**
    * Valida que el nombre tenga entre 2 y 20 caracteres.
    *
    * @param nombre El nombre a validar.
    * @return true si el nombre cumple con la longitud válida (entre 2 y 20 caracteres),
    *         false en caso contrario.
    */
    private boolean validarNombre(String nombre) {
        return nombre.length() >= 2 && nombre.length() <= 20;
    }

    /**
    * Valida que el apellido tenga entre 1 y 40 caracteres.
    *
    * @param apellido El apellido a validar.
    * @return true si el apellido cumple con la longitud válida (entre 1 y 40 caracteres),
    *         false en caso contrario.
    */
    private boolean validarApellido(String apellido) {
        return apellido.length() >= 1 && apellido.length() <= 40;
    }

    /**
    * Valida que el teléfono tenga exactamente 8 dígitos numéricos.
    *
    * @param telefono El número de teléfono a validar.
    * @return true si el teléfono tiene exactamente 8 dígitos, false en caso contrario.
    */
    private boolean validarTelefono(String telefono) {
        return telefono.matches("\\d{8}");
    }

    /**
    * Valida que el correo electrónico tenga un formato válido.
    *
    * @param correo El correo electrónico a validar.
    * @return true si el correo tiene un formato válido, false en caso contrario.
    */
    private boolean validarCorreo(String correo) {
        return correo.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }

    /**
    * Valida que la dirección tenga entre 5 y 60 caracteres.
    *
    * @param direccion La dirección a validar.
    * @return true si la dirección cumple con la longitud válida (entre 5 y 60 caracteres),
    *         false en caso contrario.
    */
    private boolean validarDireccion(String direccion) {
        return direccion.length() >= 5 && direccion.length() <= 60;
    }

    /**
    * Valida que la identificación de la persona esté en el rango de longitud correcto
    * y que no exista una identificación duplicada en la lista de usuarios.
    *
    * @param id La identificación a validar.
    * @param persona La persona que contiene la identificación actual (para evitar comparación con su propia ID).
    * @return true si la identificación cumple con la longitud válida y no está duplicada,
    *         false en caso contrario.
    */
    private boolean validarIdentificacion(String id, Persona persona) {
    // Verificar que la longitud de la identificación esté en el rango correcto
        if (id.length() >= 2 && id.length() <= 25) {
            // Iterar sobre la lista de usuarios para buscar si la identificación ya existe
            for (String[] data : SistemaParqueo.ListaDeUsuarios) {
                
                if (!data[7].equals(persona.getIdentificacion())){
                    // Comparar la identificación (en la posición 8 del array) con el id proporcionado
                    if (data[7].equals(id)) {
                        return false; // La identificación ya existe
                    }
                }
            }
            return true; // La identificación no existe
        } else {
        return false; // La longitud de la identificación no es válida
        }
    }


    /**
    * Valida si un número de terminal es válido.
    * El terminal debe tener exactamente 6 caracteres y no debe estar duplicado
    * en la lista de usuarios con el rol de "Inspector".
    * 
    * @param terminal El número de terminal a validar.
    * @return true si el terminal es válido (tiene 6 caracteres y no está duplicado),
    *         false en caso contrario.
    */
    private boolean validarTerminal(String terminal) {
        for (String[] data : SistemaParqueo.ListaDeUsuarios) {
            // Comparar la identificación (en la posición 8 del array) con el id proporcionado
            if (data[0].equals("Inspector")) {
                if (data[9].equals(terminal))
                return false; // La identificación ya existe
            }
        }
        return terminal.length() == 6;
    }
    
}

