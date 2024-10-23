package com.mycompany.sistemaparqueo.GUI;

import com.mycompany.sistemaparqueo.Clases.Persona;
import com.mycompany.sistemaparqueo.SistemaParqueo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Clase que representa la ventana para el cambio de PIN de un usuario.
 * Permite a los usuarios cambiar su PIN temporal por uno nuevo mediante
 * un proceso de validación.
 * 
 * <p>Esta clase extiende {@link JFrame} y configura los componentes
 * de la interfaz gráfica necesarios para capturar el PIN temporal
 * y el nuevo PIN, además de validar la entrada.</p>
 * 
 * @author kevin
 */
public class CambioPin extends JFrame {

    /**
     * Constructor que crea la ventana para el cambio de PIN.
     * Genera un PIN temporal aleatorio para el usuario y actualiza la
     * información en el archivo de usuarios.
     * 
     * @param persona El objeto {@link Persona} que representa al usuario
     *                cuyo PIN se está cambiando.
     * @param pantalla Indica la pantalla desde la que se accede a este cambio.
     * @param existente El objeto {@link Persona} que representa al usuario
     *                  existente (usado para redirigir a la pantalla correspondiente).
     */
    public CambioPin(Persona persona, String pantalla, Persona existente) {
        // Generar un número PIN temporal aleatorio
        Random random = new Random();
        int numero = 1000 + random.nextInt(9000); // Genera un número entre 1000 y 9999
        System.out.println("Número generado: " + numero);
        persona.setPassword(String.valueOf(numero));

        // Actualizar el PIN temporal en la lista de usuarios
        for (String[] data : SistemaParqueo.ListaDeUsuarios) {
            if (data[7].equals(persona.getIdentificacion())) data[5] = String.valueOf(numero);
        }

        // Escribir los cambios en el archivo "usuarios.txt"
        SistemaParqueo.controladorArchivos.escribirArchivo(SistemaParqueo.ListaDeUsuarios, "usuarios.txt");

        // Configuración de la ventana principal
        setTitle("Cambio de Pin");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana
        setVisible(true);

        // Crear el panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10)); // Usar un GridLayout para alinear componentes en una cuadrícula

        // Crear etiquetas y campos de entrada para el PIN temporal y el nuevo PIN
        JLabel tempPinLabel = new JLabel("Pin Temporal:");
        JPasswordField tempPinField = new JPasswordField();

        JLabel newPinLabel = new JLabel("Nuevo Pin:");
        JPasswordField newPinField = new JPasswordField();

        // Botón para confirmar el cambio de PIN
        JButton cambiarButton = new JButton("Cambiar Pin");

        // Agregar los componentes al panel
        panel.add(tempPinLabel);
        panel.add(tempPinField);
        panel.add(newPinLabel);
        panel.add(newPinField);

        // Crear un panel inferior para el botón
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(cambiarButton);

        // Agregar los paneles a la ventana
        add(panel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Acción del botón para capturar los PINs ingresados
        cambiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los PINs ingresados
                String tempPin = new String(tempPinField.getPassword());
                String newPin = new String(newPinField.getPassword());

                // Validar el PIN temporal y el nuevo PIN
                if (!persona.getPassword().equals(tempPin)) {
                    JOptionPane.showMessageDialog(null, "PIN TEMPORAL INCORRECTO");
                } else if (!validarPin(newPin)) {
                    JOptionPane.showMessageDialog(null, "NUEVO PIN DEBE CONTENER 4 CARACTERES NUMERICOS");
                } else {
                    // Actualizar el nuevo PIN y guardarlo en el archivo
                    persona.setPassword(newPin);

                    for (String[] data : SistemaParqueo.ListaDeUsuarios) {
                        if (data[7].equals(persona.getIdentificacion())) data[5] = newPin;
                    }

                    SistemaParqueo.controladorArchivos.escribirArchivo(SistemaParqueo.ListaDeUsuarios, "usuarios.txt");

                    // Cerrar la ventana actual y redirigir según la pantalla de origen
                    dispose();
                    if (!pantalla.equals("Menu")) {
                        new ModificarUsuario(persona);
                    } else {
                        new Menu(existente);
                    }
                }
            }
        });
    }

    /**
     * Valida que el PIN nuevo contenga exactamente 4 caracteres numéricos.
     * 
     * @param pin El PIN a validar.
     * @return true si el PIN tiene exactamente 4 caracteres numéricos, false de lo contrario.
     */
    private boolean validarPin(String pin) {
        return pin.length() == 4 && pin.matches("\\d{4}");
    }
}
