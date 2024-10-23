package com.mycompany.sistemaparqueo.Clases;

/**
 * La clase {@code Tarjeta} representa una tarjeta de crédito o débito asociada a un usuario del sistema de parqueo.
 * <p>Esta clase almacena información relacionada con la tarjeta, como el usuario propietario, el número de la tarjeta, 
 * la fecha de vencimiento y el código de validación.</p>
 * 
 * @author kevin
 */
public class Tarjeta {
    
    /** El usuario propietario de la tarjeta. */
    private String usuario;
    
    /** El número de la tarjeta. */
    private String numero;
    
    /** La fecha de vencimiento de la tarjeta. */
    private String fechaVencimiento;
    
    /** El código de validación de la tarjeta. */
    private String codigoValidacion;

    /**
     * Constructor que inicializa los atributos de la tarjeta con los valores proporcionados.
     * 
     * @param usuario El usuario propietario de la tarjeta.
     * @param numero El número de la tarjeta.
     * @param fechaVencimiento La fecha de vencimiento de la tarjeta.
     * @param codigoValidacion El código de validación de la tarjeta.
     */
    public Tarjeta(String usuario, String numero, String fechaVencimiento, String codigoValidacion) {
        this.usuario = usuario;
        this.numero = numero;
        this.fechaVencimiento = fechaVencimiento;
        this.codigoValidacion = codigoValidacion;
    }

    /**
     * Obtiene el usuario propietario de la tarjeta.
     * 
     * @return El usuario de la tarjeta.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario propietario de la tarjeta.
     * 
     * @param usuario El usuario propietario de la tarjeta.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene el número de la tarjeta.
     * 
     * @return El número de la tarjeta.
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Establece el número de la tarjeta.
     * 
     * @param numero El número de la tarjeta.
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Obtiene la fecha de vencimiento de la tarjeta.
     * 
     * @return La fecha de vencimiento de la tarjeta.
     */
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * Establece la fecha de vencimiento de la tarjeta.
     * 
     * @param fechaVencimiento La nueva fecha de vencimiento de la tarjeta.
     */
    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * Obtiene el código de validación de la tarjeta.
     * 
     * @return El código de validación de la tarjeta.
     */
    public String getCodigoValidacion() {
        return codigoValidacion;
    }

    /**
     * Establece el código de validación de la tarjeta.
     * 
     * @param codigoValidacion El nuevo código de validación de la tarjeta.
     */
    public void setCodigoValidacion(String codigoValidacion) {
        this.codigoValidacion = codigoValidacion;
    }

    /**
     * Convierte los atributos de la tarjeta en un arreglo de cadenas.
     * 
     * @return Un arreglo de cadenas con los atributos de la tarjeta: usuario, número, fecha de vencimiento y código de validación.
     */
    public String[] toArray() {
        return new String[]{
            this.usuario,
            this.numero,
            this.fechaVencimiento,
            this.codigoValidacion
        };
    }
}
