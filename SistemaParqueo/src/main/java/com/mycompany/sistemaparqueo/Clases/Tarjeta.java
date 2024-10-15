/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaparqueo.Clases;

/**
 *
 * @author kevin
 */
public class Tarjeta {
    // Atributos
    private String usuario;
    private String numero;
    private String fechaVencimiento;
    private String codigoValidacion;

    // Constructor
    public Tarjeta(String usuario, String numero, String fechaVencimiento, String codigoValidacion) {
        this.usuario = usuario;
        this.numero = numero;
        this.fechaVencimiento = fechaVencimiento;
        this.codigoValidacion = codigoValidacion;
    }

    // Getters y Setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getCodigoValidacion() {
        return codigoValidacion;
    }

    public void setCodigoValidacion(String codigoValidacion) {
        this.codigoValidacion = codigoValidacion;
    }

   
    public String[] toArray() {
        return new String[]{
            this.usuario,
            this.numero,
            this.fechaVencimiento,
            this.codigoValidacion
        };
    }
}

