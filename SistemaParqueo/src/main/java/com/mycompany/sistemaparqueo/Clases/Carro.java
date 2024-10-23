package com.mycompany.sistemaparqueo.Clases;

/**
 * La clase Carro representa un vehículo asociado a un usuario. Contiene información sobre la placa, 
 * la marca y el modelo del vehículo, así como métodos para acceder y modificar esta información.
 * 
 * <p>El constructor de la clase permite crear un objeto Carro con la información mínima: 
 * usuario y placa. La marca y el modelo se establecen de forma opcional.</p>
 * 
 * @author kevin
 */
public class Carro {
    
    /** El usuario asociado al carro. */
    String usuario;
    
    /** La placa del carro. */
    String placa;
    
    /** La marca del carro. */
    String marca;
    
    /** El modelo del carro. */
    String modelo;
    
    /** Valor booleano de si esta parqueado */
    boolean parqueado;
  

    /**
     * Constructor que inicializa el carro con un usuario y una placa.
     * 
     * <p>La marca y el modelo son establecidos con valores predeterminados ("No especifica").</p>
     * 
     * @param usuario El usuario asociado al carro.
     * @param placa La placa del carro.
     */
    public Carro(String usuario, String placa){
        this.usuario = usuario;
        this.placa = placa;
        this.marca = "No especifica";
        this.modelo = "No especifica";
    }

    /**
     * Establece la marca del carro.
     * 
     * @param marca La nueva marca del carro.
     */
    public void setMarca(String marca){
        this.marca = marca;
    }
    
    /**
     * Obtiene la marca del carro.
     * 
     * @return La marca del carro.
     */
    public String getMarca(){
        return this.marca;
    }

    /**
     * Establece el modelo del carro.
     * 
     * @param modelo El nuevo modelo del carro.
     */
    public void setModelo(String modelo){
        this.modelo = modelo;
    }
    
    /**
     * Obtiene el modelo del carro.
     * 
     * @return El modelo del carro.
     */
    public String getModelo(){
        return this.modelo;
    }

    /**
     * Obtiene la placa del carro.
     * 
     * @return La placa del carro.
     */
    public String getPlaca(){
        return this.placa;
    }

    /**
     * Devuelve los atributos del carro como un arreglo de cadenas.
     * 
     * @return Un arreglo de cadenas con los atributos del carro: usuario, placa, modelo, marca.
     */
    public String[] toArray(){
        return new String[]{
            this.usuario,
            this.placa,
            this.modelo,
            this.marca,
            String.valueOf(this.parqueado)
        };
    }
}
