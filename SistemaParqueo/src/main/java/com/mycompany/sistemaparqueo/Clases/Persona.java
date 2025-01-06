package com.mycompany.sistemaparqueo.Clases;

import java.time.LocalDate;

/**
 * Clase abstracta que representa una persona en el sistema de parqueo.
 * Esta clase contiene los atributos comunes que comparten los diferentes tipos de personas
 * (Administrador, Usuario, Inspector) y proporciona métodos para acceder y modificar estos atributos.
 * 
 * @author kevin
 */
public abstract class Persona {
    
    /** Tipo de la persona (por ejemplo, Administrador, Usuario, Inspector) */
    String tipo;
    
    /** Nombre de la persona */
    String nombre;
    
    /** Apellido de la persona */
    String apellido;
    
    /** Teléfono de contacto de la persona */
    String telefono;
    
    /** Correo electrónico de la persona */
    String correo;
    
    /** Contraseña de la persona */
    String password;
    
    /** Dirección de la persona */
    String direccion;
    
    /** Identificación de la persona */
    String identificacion;
    
    /** Fecha de ingreso de la persona al sistema */
    LocalDate fechaIngreso;
    
    /**
     * Constructor por defecto de la clase Persona.
     * Este constructor inicializa los atributos de la clase sin parámetros.
     */
    public Persona(){
    }
    
    /**
     * Obtiene el tipo de persona (Administrador, Usuario, Inspector, etc.).
     *
     * @return El tipo de persona.
     */
    public String getTipo(){
        return this.tipo;
    }
    
    /**
     * Establece el nombre de la persona.
     *
     * @param nombre El nombre de la persona.
     */
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    /**
     * Obtiene el nombre de la persona.
     *
     * @return El nombre de la persona.
     */
    public String getNombre(){
        return this.nombre;
    }
    
    /**
     * Obtiene el apellido de la persona.
     *
     * @return El apellido de la persona.
     */
    public String getApellido(){
        return this.apellido;
    }
    
    /**
     * Establece el apellido de la persona.
     *
     * @param apellido El apellido de la persona.
     */
    public void setApellido(String apellido){
        this.apellido = apellido;
    }
    
    /**
     * Obtiene el número de teléfono de la persona.
     *
     * @return El teléfono de la persona.
     */
    public String getTelefono(){
        return this.telefono;
    }
    
    /**
     * Establece el número de teléfono de la persona.
     *
     * @param telefono El teléfono de la persona.
     */
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }
    
    /**
     * Obtiene el correo electrónico de la persona.
     *
     * @return El correo electrónico de la persona.
     */
    public String getCorreo(){
        return this.correo;
    }
    
    /**
     * Establece el correo electrónico de la persona.
     *
     * @param correo El correo electrónico de la persona.
     */
    public void setCorreo(String correo){
        this.correo = correo;
    }
    
    /**
     * Obtiene la contraseña de la persona.
     *
     * @return La contraseña de la persona.
     */
    public String getPassword(){
        return this.password;
    }
    
    /**
     * Establece la contraseña de la persona.
     *
     * @param password La contraseña de la persona.
     */
    public void setPassword(String password){
        this.password = password;
    }
    
    /**
     * Obtiene la dirección de la persona.
     *
     * @return La dirección de la persona.
     */
    public String getDireccion(){
        return this.direccion;
    }
    
    /**
     * Establece la dirección de la persona.
     *
     * @param direccion La dirección de la persona.
     */
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    
    /**
     * Obtiene la identificación de la persona.
     *
     * @return La identificación de la persona.
     */
    public String getIdentificacion(){
        return this.identificacion;
    }
    
    /**
     * Establece la identificación de la persona.
     *
     * @param identificacion La identificación de la persona.
     */
    public void setIdentificacion(String identificacion){
        this.identificacion = identificacion;
    }
    
    /**
     * Obtiene la fecha de ingreso de la persona al sistema.
     *
     * @return La fecha de ingreso de la persona.
     */
    public LocalDate getfechaIngreso(){
        return this.fechaIngreso;
    }
    
    /**
     * Establece la fecha de ingreso de la persona al sistema.
     *
     * @param fechaingreso La fecha de ingreso de la persona.
     */
    public void setFechaIngreso(LocalDate fechaingreso){
        this.fechaIngreso = fechaingreso;
    }
    
    /**
     * Establece el terminal asociado a la persona.
     * Este método es abstracto y no realiza ninguna acción en esta clase base.
     *
     * @param terminal El terminal de la persona.
     */
    public void setTerminal(String terminal){
    };
    
    /**
     * Obtiene el terminal asociado a la persona.
     * Este método es abstracto y retorna null en esta clase base.
     *
     * @return El terminal de la persona.
     */
    public String getTerminal(){
        return null;
    }
    
    /**
     * Establece la cantidad de dinero disponible por parqueo no usado.
     * Este metodo es abstracto y no realiza ninguna accion en esta clase base.
     * 
     * @param dinero cantidad de dinero que tiene a favor.
     */
    public void setDinero(String dinero){
        
    };
    
    /**
     * Obtiene la cantidad de dinero disponible por parqueo no usado.
     * Este metodo es abstracto y retorna null en esta clase base.
     * 
     * @return El dinero disponible de la persona.
     */
    public String getDinero(){
        return null;
    }
    
    public int impuestos(int dinero){
      return dinero * 13;  
    };
    
    /**
     * Convierte la información de la persona a un arreglo de cadenas.
     * Este método es abstracto y no realiza ninguna acción en esta clase base.
     *
     * @return El arreglo de cadenas con la información de la persona.
     */
    public String[] toArray(){
        return null;
    };
}
