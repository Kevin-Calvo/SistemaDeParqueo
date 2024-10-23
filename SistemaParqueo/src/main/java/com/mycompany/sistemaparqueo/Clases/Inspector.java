package com.mycompany.sistemaparqueo.Clases;

import java.time.format.DateTimeFormatter;

/**
 * La clase Inspector extiende la clase {@link Admin}, representando a un inspector del sistema de parqueo.
 * <p>El Inspector hereda los atributos de la clase {@link Admin} y agrega un atributo específico: terminal.</p>
 * 
 * <p>Esta clase permite acceder y modificar los atributos de un inspector, como el nombre, apellido, correo, 
 * así como la terminal a la que el inspector está asignado.</p>
 * 
 * @author kevin
 */
public class Inspector extends Admin {

    /** La terminal a la que está asignado el inspector. */
    String terminal;

    /**
     * Constructor por defecto que inicializa el tipo del inspector.
     * El tipo se establece como "Inspector".
     */
    public Inspector(){
        this.tipo = "Inspector";
    }

    /**
     * Constructor que inicializa un inspector con los atributos proporcionados.
     * 
     * <p>Este constructor utiliza los atributos heredados de la clase {@link Admin} y también inicializa 
     * el atributo específico de terminal.</p>
     * 
     * @param tipo El tipo del inspector (generalmente "Inspector").
     * @param nombre El nombre del inspector.
     * @param apellido El apellido del inspector.
     * @param numero El número de teléfono del inspector.
     * @param correo El correo electrónico del inspector.
     * @param password La contraseña del inspector.
     * @param direccion La dirección del inspector.
     * @param identificacion El número de identificación del inspector.
     * @param fecha La fecha de ingreso del inspector.
     * @param terminal La terminal a la que está asignado el inspector.
     */
    public Inspector(String tipo, String nombre, String apellido, String numero, String correo, String password,
            String direccion, String identificacion, String fecha, String terminal){
        
        super(tipo, nombre, apellido, numero, correo, password, direccion, identificacion, fecha);
        this.terminal = terminal;
    }

    /**
     * Establece la terminal del inspector.
     * 
     * @param terminal La nueva terminal del inspector.
     */
    @Override
    public void setTerminal(String terminal){
        this.terminal = terminal;
    }

    /**
     * Obtiene la terminal a la que está asignado el inspector.
     * 
     * @return La terminal del inspector.
     */
    @Override
    public String getTerminal(){
        return this.terminal;
    }

    /**
     * Devuelve una representación en cadena del inspector, incluyendo los atributos heredados y la terminal.
     * 
     * @return Una cadena que representa al inspector en el formato: 
     * tipo;nombre;apellido;telefono;correo;password;direccion;identificacion;fechaIngreso;terminal
     */
    @Override
    public String toString(){
        return this.tipo + ";" + this.nombre + ";" + this.apellido + ";" + this.telefono + ";" +
                this.correo + ";" + this.password +";" + this.direccion + ";" + this.identificacion + 
                ";" + this.fechaIngreso.toString() + ";" + this.terminal;
    }

    /**
     * Devuelve los atributos del inspector como un arreglo de cadenas.
     * 
     * @return Un arreglo de cadenas con los atributos del inspector: tipo, nombre, apellido, teléfono, correo, 
     * password, dirección, identificación, fecha de ingreso y terminal.
     */
    @Override
    public String[] toArray() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fecha = this.fechaIngreso.format(formatter);
        return new String[] {
            this.tipo, 
            this.nombre, 
            this.apellido, 
            this.telefono, 
            this.correo, 
            this.password, 
            this.direccion, 
            this.identificacion, 
            fecha,  // Convertir LocalDate a String
            this.terminal
        };
    }
}

