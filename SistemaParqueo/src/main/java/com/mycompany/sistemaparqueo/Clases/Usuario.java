package com.mycompany.sistemaparqueo.Clases;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * La clase {@code Usuario} representa a un usuario en el sistema de parqueo.
 * <p>Hereda de la clase {@code Persona} y contiene información específica del usuario, como su nombre, apellido, teléfono,
 * correo, dirección, identificación, fecha de ingreso y tipo.</p>
 * 
 * <p>El {@code Usuario} se caracteriza por ser una entidad que puede interactuar con el sistema de parqueo, creando
 * reservas o manejando su cuenta.</p>
 * 
 * @author kevin
 */
public class Usuario extends Persona {
    private float dinero;
    /**
     * Constructor sin parámetros que establece el tipo de usuario como "Usuario" y la fecha de ingreso
     * a un valor por defecto (01-01-3000).
     */
    public Usuario() {
        this.tipo = "Usuario";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.fechaIngreso = LocalDate.parse("01-01-3000", formatter);
        this.dinero = 0;
    }

    /**
     * Constructor con parámetros que inicializa los atributos de la clase {@code Usuario} con los valores proporcionados.
     * 
     * @param tipo El tipo de usuario.
     * @param nombre El nombre del usuario.
     * @param apellido El apellido del usuario.
     * @param numero El número de teléfono del usuario.
     * @param correo El correo electrónico del usuario.
     * @param password La contraseña del usuario.
     * @param direccion La dirección del usuario.
     * @param identificacion La identificación del usuario.
     * @param fecha La fecha de ingreso del usuario.
     */
    public Usuario(String tipo, String nombre, String apellido, String numero, String correo,
            String password, String direccion, String identificacion, String fecha) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = numero;
        this.correo = correo;
        this.password = password;
        this.direccion = direccion;
        this.identificacion = identificacion;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.fechaIngreso = LocalDate.parse(fecha, formatter);
        this.dinero = 0;
    }
    
     /**
     * Establece la cantidad de dinero restante.
     * 
     * @param dinero La nueva cantidad de dinero .
     */
    @Override
    public void setDinero(String dinero){
        this.dinero = Float.parseFloat(dinero);
    }

    /**
     * Obtiene la cantidad de dinero disponible por usuario.
     * 
     * @return La cantidad de dinero disponible
     */
    @Override
    public String getDinero(){
        return String.valueOf(this.dinero);
    }

    /**
     * Representa la información del {@code Usuario} como una cadena de texto en formato CSV.
     * 
     * @return Una cadena con la información del usuario separada por punto y coma (;).
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fecha = this.fechaIngreso.format(formatter);
        return this.tipo + ";" + this.nombre + ";" + this.apellido + ";" + this.telefono + ";" +
                this.correo + ";" + this.password +";" + this.direccion + ";" + this.identificacion + 
                ";" + fecha;
    }

    /**
     * Convierte los atributos del {@code Usuario} en un arreglo de cadenas.
     * 
     * @return Un arreglo de cadenas con los atributos del usuario: tipo, nombre, apellido, teléfono,
     *         correo, contraseña, dirección, identificación y fecha de ingreso.
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
            fecha,
            String.valueOf(this.dinero)
        };
    }
}
