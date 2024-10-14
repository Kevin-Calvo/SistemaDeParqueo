/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaparqueo.Clases;

import java.time.YearMonth;
import java.util.ArrayList;

/**
 *
 * @author kevin
 */
public class Usuario extends Persona{
    
    ArrayList<Carro> listaCarros;
    Tarjeta tarjeta;
    
    public Usuario(){
        this.tipo = "Usuario";
        this.listaCarros = new ArrayList<>(); 
    }
    
    public void setTarjeta(String numero, YearMonth vencimiento, String validacion){
        this.tarjeta = new Tarjeta(numero, vencimiento, validacion);
    }
    
    public Tarjeta getTarjeta(){
        return this.tarjeta;
    }
    
    public void agregarCarro(){
        
    }
}
