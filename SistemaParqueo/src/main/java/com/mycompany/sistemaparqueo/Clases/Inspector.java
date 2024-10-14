/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaparqueo.Clases;

/**
 *
 * @author kevin
 */
public class Inspector extends Admin{
    String terminal;
    
    public Inspector(){
        this.tipo = "Inspector";
    }
    
    public void setTerminal(String terminal){
        this.terminal = terminal;
    }
    
    public String getTerminal(){
        return this.terminal;
    }
}
