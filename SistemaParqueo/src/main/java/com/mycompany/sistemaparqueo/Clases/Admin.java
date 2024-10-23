package com.mycompany.sistemaparqueo.Clases;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * La clase Admin representa a un administrador del sistema, que es un tipo especializado de persona.
 * Un administrador tiene atributos como nombre, apellido, teléfono, correo electrónico, dirección,
 * identificación, fecha de ingreso, y una contraseña para autenticarse.
 * 
 * @author kevin
 */
public class Admin extends Persona {

    /**
     * Constructor por defecto que inicializa el tipo como "Administrador".
     */
    public Admin() {
        this.tipo = "Administrador";
    }

    /**
     * Constructor con parámetros que inicializa los atributos de un administrador.
     * 
     * @param tipo          El tipo de persona (en este caso siempre será "Administrador").
     * @param nombre        El nombre del administrador.
     * @param apellido      El apellido del administrador.
     * @param numero        El número de teléfono del administrador.
     * @param correo        El correo electrónico del administrador.
     * @param password      La contraseña del administrador.
     * @param direccion     La dirección del administrador.
     * @param identificacion La identificación del administrador.
     * @param fecha         La fecha de ingreso del administrador en formato "dd-MM-yyyy".
     */
    public Admin(String tipo, String nombre, String apellido, String numero, String correo, String password,
                 String direccion, String identificacion, String fecha) {
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
    }

    /**
     * Método que devuelve una representación en cadena de caracteres del objeto Admin.
     * 
     * @return Una cadena con los atributos del administrador separados por punto y coma (;).
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fecha = this.fechaIngreso.format(formatter);
        return this.tipo + ";" + this.nombre + ";" + this.apellido + ";" + this.telefono + ";" +
                this.correo + ";" + this.password + ";" + this.direccion + ";" + this.identificacion + 
                ";" + fecha;
    }

    /**
     * Método que convierte los atributos del administrador en un arreglo de cadenas.
     * 
     * @return Un arreglo de cadenas que contiene los atributos del administrador.
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
            fecha
        };
    }
}
