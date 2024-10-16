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
    String usuario;
    String placa;
    String marca;
    String modelo;
    
    public Carro(String usuario, String placa){
        this.usuario = usuario;
        this.placa = placa;
        this.marca = "No especifica";
        this.modelo = "No especifica";
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
    
    public String[] toArray(){
        return new String[]{
        this.usuario,
        this.placa,
        this.modelo,
        this.marca};
    } 
}
