/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaparqueo.Clases;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author kevin
 */
public class Admin extends Persona {
    
    public Admin(){
        this.tipo = "Administrador";
    }
    
   public Admin(String tipo, String nombre, String apellido, String numero, String correo, String password,
            String direccion, String identificacion, String fecha){
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
    
     @Override
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fecha = this.fechaIngreso.format(formatter);
        return this.tipo + ";" + this.nombre + ";" + this.apellido + ";" + this.telefono + ";" +
                this.correo + ";" + this.password +";" + this.direccion + ";" + this.identificacion + 
                ";" + fecha;
    }
    
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
