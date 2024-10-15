/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaparqueo.Clases;

import java.time.format.DateTimeFormatter;

/**
 *
 * @author kevin
 */
public class Inspector extends Admin{
    String terminal;
    
    public Inspector(){
        this.tipo = "Inspector";
    }
    
    public Inspector(String tipo, String nombre, String apellido, String numero, String correo, String password,
            String direccion, String identificacion, String fecha, String terminal){
        
        super(tipo, nombre, apellido, numero, correo, password, direccion, identificacion, fecha);
        this.terminal = terminal;
    }
    
    @Override
    public void setTerminal(String terminal){
        this.terminal = terminal;
    }
    
    @Override
    public String getTerminal(){
        return this.terminal;
    }
    
    @Override
    public String toString(){
        return this.tipo + ";" + this.nombre + ";" + this.apellido + ";" + this.telefono + ";" +
                this.correo + ";" + this.password +";" + this.direccion + ";" + this.identificacion + 
                ";" + this.fechaIngreso.toString() + ";" + this.terminal;
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
            fecha,  // Convertir LocalDate a String
            this.terminal
        };
    }
}
