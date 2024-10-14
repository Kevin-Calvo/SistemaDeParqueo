/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaparqueo.Clases;

import java.time.YearMonth;

/**
 *
 * @author kevin
 */
public class Tarjeta {
    String numero;
    YearMonth vencimiento;
    String validacion;
    
    public Tarjeta(String numero, YearMonth vencimiento, String validacion){
        this.numero = numero;
        this.vencimiento = vencimiento;
        this.validacion = validacion;
       
    }
}
