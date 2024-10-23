package com.mycompany.sistemaparqueo.Clases;

import java.time.LocalTime;

/**
 * La clase {@code Espacio} representa un espacio de estacionamiento con un número,
 * un identificador de espacio, su disponibilidad y la hora de finalización
 * en la que quedará libre.
 * 
 * @author kevin
 */
public class Espacio {

    private String numero;           // Número de identificación del espacio
    private String espacio;          // Identificador del espacio
    private boolean disponible;      // Disponibilidad del espacio
    private LocalTime finalizacion;  // Hora en que el espacio se liberará

    /**
     * Constructor de la clase {@code Espacio}.
     * 
     * @param numero Número de parqueo
     * @param espacio Identificador del espacio
     * @param disponible Estado de disponibilidad del espacio (true si está disponible, false si está ocupado)
     * @param finalizacion Hora en que el espacio quedará libre
     */
    public Espacio(String numero, String espacio, boolean disponible, LocalTime finalizacion) {
        this.numero = numero;
        this.espacio = espacio;
        this.disponible = disponible;
        this.finalizacion = finalizacion;
    }

    /**
     * Obtiene el número del espacio de estacionamiento.
     * 
     * @return El número del espacio de estacionamiento
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Establece el número del espacio de estacionamiento.
     * 
     * @param numero El número a asignar al espacio
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Obtiene el identificador del espacio de estacionamiento.
     * 
     * @return El identificador del espacio
     */
    public String getEspacio() {
        return espacio;
    }

    /**
     * Establece el identificador del espacio de estacionamiento.
     * 
     * @param espacio El identificador a asignar al espacio
     */
    public void setEspacio(String espacio) {
        this.espacio = espacio;
    }

    /**
     * Verifica si el espacio está disponible.
     * 
     * @return {@code true} si el espacio está disponible; {@code false} en caso contrario
     */
    public boolean esDisponible() {
        return disponible;
    }
    
    /**
     * Obtiene la disponibilidad del espacio
     * @return  Disponible si no esta ocupado, Ocupado si ya hay alguien parqueado
     */
    public String getDisponible(){
        if (this.disponible){
            return "Disponible";
        }
        else return "Ocupado";
    }

    /**
     * Establece el estado de disponibilidad del espacio de estacionamiento.
     * 
     * @param disponible El estado de disponibilidad a establecer (true si está disponible, false si está ocupado)
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * Obtiene la hora de finalización en la que el espacio quedará libre.
     * 
     * @return La hora de finalización
     */
    public LocalTime getFinalizacion() {
        return finalizacion;
    }

    /**
     * Establece la hora de finalización en la que el espacio quedará libre.
     * 
     * @param finalizacion La hora de finalización a asignar
     */
    public void setFinalizacion(LocalTime finalizacion) {
        this.finalizacion = finalizacion;
    }

    /**
     * Convierte la información del espacio a un arreglo de cadenas.
     * Este método es abstracto y no realiza ninguna acción en esta clase base.
     *
     * @return El arreglo de cadenas con la información de la persona.
     */
    
    public String[] toArray(){
        return new String[]{
          this.numero,
          this.espacio,
          String.valueOf(this.disponible),
          this.finalizacion.toString()
            
        };
    }
}

