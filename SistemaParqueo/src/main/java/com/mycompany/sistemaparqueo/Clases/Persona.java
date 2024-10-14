/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaparqueo.Clases;

import java.time.LocalDate;

/**
 *
 * @author kevin
 */
public class Persona {
    String tipo;
    String nombre;
    String apellido;
    String telefono;
    String correo;
    String direccion;
    String identificacion;
    LocalDate fechaIngreso;
    
    public Persona(){
        
    }
    
    public String getTipo(){
        return this.tipo;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public String getApellido(){
        return this.apellido;
    }
    
    public void setApellido(String apellido){
        this.apellido = apellido;
    }
    
    public String getTelefono(){
        return this.telefono;
    }
    
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }
    
    public String getCorreo(){
        return this.correo;
    }
    
    public void setCorreo(String correo){
        this.correo = correo;
    }
    
    public String getDireccion(){
        return this.direccion;
    }
    
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    
    public String getIdentificacion(){
        return this.identificacion;
    }
    
    public void setIdentificacion(String identificacion){
        this.identificacion = identificacion;
    }
    
    public LocalDate fechaIngreso(){
        return this.fechaIngreso;
    }
    
    public void setFechaIngreso(LocalDate fechaingreso){
        this.fechaIngreso = fechaingreso;
    }
}
