/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaparqueo.Clases;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * La clase {@code Parqueo} representa a un espacio de parqueo.
 * 
 * @author kevin
 */
public class Parqueo {
    private String id; 
    private LocalTime horarioInicio;
    private LocalTime horarioFinal;
    private int precio;
    private int minutos;
    private int multa;

    /**
     * Constructor para inicializar un objeto Parqueo.
     * 
     * @param id           El identificador del parqueo.
     * @param inicio       La hora de inicio del parqueo.
     * @param horarioFinal La hora de finalización del parqueo.
     * @param precio       El precio por minuto o por tiempo definido.
     */
    public Parqueo(String id, LocalTime inicio, LocalTime horarioFinal, int precio, int minutos, int multa) {
        this.id = id;
        this.horarioInicio = inicio;
        this.horarioFinal = horarioFinal;
        this.precio = precio;
        this.minutos = minutos;
        this.multa = multa;
    }

    /**
     * Obtiene el identificador del parqueo.
     * 
     * @return El identificador del parqueo.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador del parqueo.
     * 
     * @param id El nuevo identificador del parqueo.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene la hora de inicio del parqueo.
     * 
     * @return La hora de inicio del parqueo.
     */
    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    /**
     * Establece la hora de inicio del parqueo.
     * 
     * @param horarioInicio La nueva hora de inicio del parqueo.
     */
    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    /**
     * Obtiene la hora de finalización del parqueo.
     * 
     * @return La hora de finalización del parqueo.
     */
    public LocalTime getHorarioFinal() {
        return horarioFinal;
    }

    /**
     * Establece la hora de finalización del parqueo.
     * 
     * @param horarioFinal La nueva hora de finalización del parqueo.
     */
    public void setHorarioFinal(LocalTime horarioFinal) {
        this.horarioFinal = horarioFinal;
    }

    /**
     * Obtiene el precio del parqueo.
     * 
     * @return El precio del parqueo.
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del parqueo.
     * 
     * @param precio El nuevo precio del parqueo.
     */
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    /**
     * Obtiene la cantidad de minutos que ha sido utilizado el parqueo.
     * 
     * @return La cantidad de minutos.
     */
    public int getMinutos() {
        return minutos;
    }

    /**
     * Establece la cantidad de minutos que ha sido utilizado el parqueo.
     * 
     * @param minutos Los nuevos minutos utilizados.
     */
    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    /**
     * Obtiene la multa impuesta por exceder el tiempo permitido.
     * 
     * @return La multa impuesta.
     */
    public int getMulta() {
        return multa;
    }

    /**
     * Establece la multa por exceder el tiempo permitido.
     * 
     * @param multa La nueva multa impuesta.
     */
    public void setMulta(int multa) {
        this.multa = multa;
    }
    
    /**
     * Convierte la información del parqueo a un arreglo de cadenas.
     * Este método es abstracto y no realiza ninguna acción en esta clase base.
     *
     * @return El arreglo de cadenas con la información de la persona.
     */
    public String[] toArray(){
        return new String[] {
            this.id, 
            this.horarioInicio.toString(), 
            this.horarioFinal.toString(), 
            String.valueOf(this.precio), 
            String.valueOf(this.minutos), 
            String.valueOf(this.multa), 

        };
    };
}

   
