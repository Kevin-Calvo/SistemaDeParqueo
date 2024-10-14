/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaparqueo.Clases;

/**
 *
 * @author kevin
 */
public class Carro {
    String placa;
    String marca;
    String modelo;
    
    public Carro(String placa){
        this.placa = placa;
    }
    
    public void setMarca(String marca){
        this.marca = marca;
    }
    
    public String getMarca(){
        return this.marca;
    }
    
    public void setModelo(String modelo){
        this.modelo = modelo;
    }
    
    public String getModelo(){
        return this.modelo;
    }
    
    public String getPlaca(){
        return this.placa;
    }
    
}
