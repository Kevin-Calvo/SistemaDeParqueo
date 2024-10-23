package com.mycompany.sistemaparqueo.GUI;

import com.mycompany.sistemaparqueo.Clases.*;
import com.mycompany.sistemaparqueo.SistemaParqueo;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Esta clase representa la ventana de registro de usuario en el sistema.
 * Permite a los usuarios crear un nuevo perfil o modificar el perfil de un usuario existente.
 * 
 * @author Kevin Yadir Calvo Rodriguez
 */
public class Registrar extends JFrame {
     /**
     * Constructor de la clase Registrar.
     * Configura la ventana de registro de usuario, creando los componentes necesarios como etiquetas,
     * campos de texto, botones, y sus respectivos manejadores de eventos.
     *
     * @param persona El objeto de tipo Persona que representa al usuario que se registrará o modificará.
     * @param existente El objeto de tipo Persona que representa a admin generando un inspector o admin
     */
    public Registrar(Persona persona, Persona existente) {
        // Configuraciones de la ventana
        setTitle("Pantalla de Registro");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null); // Centrar la ventana

        // Crear el panel principal
        JPanel panel = new JPanel();
        panel.setLayout(null); // Layout nulo para control manual de posiciones
        add(panel);

        // Etiqueta y campo para el nombre
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(10, 20, 100, 25);
        panel.add(nombreLabel);

        JTextField nombreText = new JTextField(20);
        nombreText.setBounds(120, 20, 250, 25);
        panel.add(nombreText);

        // Etiqueta y campo para el apellido
        JLabel apellidoLabel = new JLabel("Apellido:");
        apellidoLabel.setBounds(10, 60, 100, 25);
        panel.add(apellidoLabel);

        JTextField apellidoText = new JTextField(20);
        apellidoText.setBounds(120, 60, 250, 25);
        panel.add(apellidoText);

        // Etiqueta y campo para el teléfono
        JLabel telefonoLabel = new JLabel("Teléfono:");
        telefonoLabel.setBounds(10, 100, 100, 25);
        panel.add(telefonoLabel);

        JTextField telefonoText = new JTextField(20);
        telefonoText.setBounds(120, 100, 250, 25);
        panel.add(telefonoText);

        // Etiqueta y campo para el correo
        JLabel correoLabel = new JLabel("Correo:");
        correoLabel.setBounds(10, 140, 100, 25);
        panel.add(correoLabel);

        JTextField correoText = new JTextField(20);
        correoText.setBounds(120, 140, 250, 25);
        panel.add(correoText);

        // Etiqueta y campo para la dirección física
        JLabel direccionLabel = new JLabel("Dirección:");
        direccionLabel.setBounds(10, 180, 100, 25);
        panel.add(direccionLabel);

        JTextField direccionText = new JTextField(20);
        direccionText.setBounds(120, 180, 250, 25);
        panel.add(direccionText);

        // Etiqueta y campo para la identificación
        JLabel idLabel = new JLabel("Identificación:");
        idLabel.setBounds(10, 220, 100, 25);
        panel.add(idLabel);

        JTextField idText = new JTextField(20);
        idText.setBounds(120, 220, 250, 25);
        panel.add(idText);

        // Etiqueta y campo para el PIN
        JLabel pinLabel = new JLabel("PIN:");
        pinLabel.setBounds(10, 260, 100, 25);
        panel.add(pinLabel);

        JPasswordField pinText = new JPasswordField(20); // El campo de PIN oculta el texto
        pinText.setBounds(120, 260, 250, 25);
        panel.add(pinText);
        
        JLabel fechaIngresoLabel = new JLabel("Fecha Ingreso:");
        fechaIngresoLabel.setBounds(10, 300, 100, 25);
        panel.add(fechaIngresoLabel);
        fechaIngresoLabel.setVisible(false);
        
        JTextField fechaIngresoText = new JTextField(20);
        fechaIngresoText.setBounds(120, 300, 250, 25);
        panel.add(fechaIngresoText);
        fechaIngresoText.setVisible(false);
        
        JLabel terminalLabel = new JLabel("Terminal:");
        terminalLabel.setBounds(10, 340, 100, 25);
        panel.add(terminalLabel);
        terminalLabel.setVisible(false);
        
        JTextField terminalText = new JTextField(20);
        terminalText.setBounds(120, 340, 250, 25);
        panel.add(terminalText);
        terminalText.setVisible(false);
        
        if(persona.getTipo().equals("Administrador")|| persona.getTipo().equals("Inspector")){
            fechaIngresoLabel.setVisible(true);
            fechaIngresoText.setVisible(true);
        }
        
        if (persona.getTipo().equals("Inspector")){
            pinLabel.setVisible(false);
            pinText.setVisible(false);
             terminalLabel.setVisible(true);
             terminalText.setVisible(true);
        }
        
        JButton volverButton = new JButton("Volver");
        volverButton.setBounds(10, 380, 100, 25);
        panel.add(volverButton);
        volverButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                if (persona.getTipo().equals("Usuario"))
                    new PantallaInicio();
                else new Menu(existente);
            }
        });

        // Botón de registro
        JButton registrarButton = new JButton("Registrar");
        registrarButton.setBounds(120, 380, 250, 25);
        panel.add(registrarButton);
        
         registrarButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String nombre = nombreText.getText();
                String apellido = apellidoText.getText();
                String telefono = telefonoText.getText();
                String correo = correoText.getText();
                String password = new String(pinText.getPassword());
                if (persona.getTipo().equals("Inspector")) password = "0000";
                String direccion = direccionText.getText();
                String identificacion = idText.getText();
                
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
                } else if (!validarIdentificacion(identificacion)) {
                    JOptionPane.showMessageDialog(null, "La identificación no puede ser repetida y debe tener entre 2 y 25 caracteres.");
                } else if (!validarPin(password)){
                    JOptionPane.showMessageDialog(null, "El pin debe ser de 6 caracteres numericos");
                } else{
                    
                    persona.setNombre(nombre);
                    persona.setApellido(apellido);
                    persona.setTelefono(telefono);
                    persona.setCorreo(correo);
                    persona.setPassword(password);
                    persona.setDireccion(direccion);
                    persona.setIdentificacion(identificacion);
                    persona.setFechaIngreso(LocalDate.now());
        
                    if (persona.getTipo().equals("Usuario")){
                        insertarPersona(persona);
                        new Menu(persona);
                    }
                    else if (persona.getTipo().equals("Administrador")){
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                            try{
                                LocalDate fechaIngreso = LocalDate.parse(fechaIngresoText.getText(), formatter);
                                if (!validarFecha(fechaIngreso)){
                                    JOptionPane.showMessageDialog(null, "Fecha debe estar en formato dd-MM-yyyy y ser antes que la fecha del sistema");
                                }
                                else {
                                    persona.setFechaIngreso(fechaIngreso);
                                    insertarPersona(persona);
                                    new Menu(existente);
                                }
                            }
                            catch (DateTimeParseException o) {
                                // Mostrar mensaje de error si la fecha no es válida
                                JOptionPane.showMessageDialog(null, "Fecha debe estar en formato dd-MM-yyyy y ser antes que la fecha del sistema.");
                            }
                        }
                    
                    else if (persona.getTipo().equals("Inspector")){
                        if(fechaIngresoText.getText().equals("")){
                            JOptionPane.showMessageDialog(null, "Fecha debe estar en formato dd-MM-yyyy y ser antes que la fecha del sistema");
                        }else{
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                            try{
                                LocalDate fechaIngreso = LocalDate.parse(fechaIngresoText.getText(), formatter);
                                String terminal = terminalText.getText();
                                if (!validarFecha(fechaIngreso)){
                                    JOptionPane.showMessageDialog(null, "Fecha debe estar en formato dd-MM-yyyy y ser antes que la fecha del sistema");
                                } else if (!validarTerminal(terminal)){
                                JOptionPane.showMessageDialog(null,"Terminal debe ser de 6 caracteres numericos");
                                }else {
                                    persona.setFechaIngreso(fechaIngreso);
                                    persona.setTerminal(terminal);
                                    insertarPersona(persona);
                                    new CambioPin(persona, "Menu", existente);
                                }      
                            }
                            catch (DateTimeParseException o) {
                                // Mostrar mensaje de error si la fecha no es válida
                                JOptionPane.showMessageDialog(null, "Fecha debe estar en formato dd-MM-yyyy y ser antes que la fecha del sistema.");
                            }  
                        }
                    }
                    
                }
            }
        });
        
    }
    
    // Métodos 
    
    /**
    * Inserta una nueva persona en la lista de usuarios y guarda la información en un archivo de texto.
    * 
    * @param persona La persona que se va a insertar en el sistema.
    */
    private void insertarPersona(Persona persona){
         SistemaParqueo.ListaDeUsuarios.add(persona.toArray());
         SistemaParqueo.controladorArchivos.escribirArchivo(SistemaParqueo.ListaDeUsuarios, "Usuarios.txt");
         JOptionPane.showMessageDialog(null, "Usuario insertado correctamente.");
         dispose();
    }
    
    /**
    * Valida que el nombre tenga una longitud entre 2 y 20 caracteres.
    * 
    * @param nombre El nombre a validar.
    * @return true si el nombre tiene una longitud válida, false en caso contrario.
    */
    private boolean validarNombre(String nombre) {
        return nombre.length() >= 2 && nombre.length() <= 20;
    }

    /**
    * Valida que el apellido tenga una longitud entre 1 y 40 caracteres.
    * 
    * @param apellido El apellido a validar.
    * @return true si el apellido tiene una longitud válida, false en caso contrario.
    */
    private boolean validarApellido(String apellido) {
        return apellido.length() >= 1 && apellido.length() <= 40;
    }

    
    /**
     * Valida que el teléfono contenga exactamente 8 dígitos numéricos.
     * 
     * @param telefono El número de teléfono a validar.
     * @return true si el teléfono es válido, false en caso contrario.
     */
    private boolean validarTelefono(String telefono) {
        return telefono.matches("\\d{8}");
    }

    /**
    * Valida que el correo tenga el formato correcto (por ejemplo, usuario@dominio.com).
    * 
    * @param correo El correo electrónico a validar.
    * @return true si el correo tiene el formato correcto, false en caso contrario.
    */
    private boolean validarCorreo(String correo) {
        return correo.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }

    /**
    * Valida que la dirección tenga una longitud entre 5 y 60 caracteres.
    * 
    * @param direccion La dirección a validar.
    * @return true si la dirección tiene una longitud válida, false en caso contrario.
    */
    private boolean validarDireccion(String direccion) {
        return direccion.length() >= 5 && direccion.length() <= 60;
    }

    /**
    * Valida que la identificación sea única y tenga una longitud entre 2 y 25 caracteres.
    * 
    * @param id La identificación a validar.
    * @return true si la identificación es válida y no existe en el sistema, false en caso contrario.
    */
    private boolean validarIdentificacion(String id) {
        // Verificar que la longitud de la identificación esté en el rango correcto
        if (id.length() >= 2 && id.length() <= 25) {
            // Iterar sobre la lista de usuarios para buscar si la identificación ya existe
            for (String[] data : SistemaParqueo.ListaDeUsuarios) {
                // Comparar la identificación (en la posición 8 del array) con el id proporcionado
                if (data[7].equals(id)) {
                    return false; // La identificación ya existe
                }
            }
            return true; // La identificación no existe
        } else {
            return false; // La longitud de la identificación no es válida
        }
    }

    /**
     * Valida que el PIN tenga exactamente 4 dígitos numéricos.
     * 
     * @param pin El PIN a validar.
     * @return true si el PIN es válido, false en caso contrario.
     */
    private boolean validarPin(String pin) {
        return pin.length() == 4 && pin.matches("\\d{4}");
    }

    /**
    * Valida que la fecha de ingreso no sea posterior a la fecha actual.
    * 
    * @param fechaIngreso La fecha de ingreso a validar.
    * @return true si la fecha es válida, false en caso contrario.
    */
    private boolean validarFecha(LocalDate fechaIngreso) {
        return !fechaIngreso.isAfter(LocalDate.now());
    }

    /**
    * Valida que el número de terminal tenga una longitud de 6 caracteres y que no exista
    * un terminal con el mismo número para un Inspector.
    * 
    * @param terminal El número de terminal a validar.
    * @return true si el número de terminal es válido, false en caso contrario.
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
