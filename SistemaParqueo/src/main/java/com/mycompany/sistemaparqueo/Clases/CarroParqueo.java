package com.mycompany.sistemaparqueo.Clases;

import java.time.LocalTime;

/**
 * Clase que representa el parqueo de un carro en un espacio.
 * Contiene información sobre el carro, el espacio, las horas de inicio y fin de parqueo, y si el parqueo está activo.
 * 
 */
public class CarroParqueo {
    private String carro;
    private String espacio;
    private LocalTime horaInicio;
    private LocalTime horaFinal;
    private boolean activo;
    private float monto;
    
    public CarroParqueo(String carro, String espacio, LocalTime now, LocalTime plusMinutes, boolean b, float dinero) {
        this.carro = carro;
        this.espacio = espacio;
        this.horaInicio = now;
        this.horaFinal = plusMinutes;
        this.activo = b;
        this.monto = dinero;
        
    }

    /**
     * Obtiene el carro asociado a este parqueo.
     * 
     * @return el carro
     */
    public String getCarro() {
        return carro;
    }

    /**
     * Establece el carro asociado a este parqueo.
     * 
     * @param carro el carro a asociar
     */
    public void setCarro(String carro) {
        this.carro = carro;
    }

    /**
     * Obtiene el espacio donde está aparcado el carro.
     * 
     * @return el espacio
     */
    public String getEspacio() {
        return espacio;
    }

    /**
     * Establece el espacio donde estará aparcado el carro.
     * 
     * @param espacio el espacio a asignar
     */
    public void setEspacio(String espacio) {
        this.espacio = espacio;
    }

    /**
     * Obtiene la hora de inicio del parqueo.
     * 
     * @return la hora de inicio
     */
    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    /**
     * Establece la hora de inicio del parqueo.
     * 
     * @param horaInicio la hora de inicio a asignar
     */
    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * Obtiene la hora de finalización del parqueo.
     * 
     * @return la hora final
     */
    public LocalTime getHoraFinal() {
        return horaFinal;
    }

    /**
     * Establece la hora de finalización del parqueo.
     * 
     * @param horaFinal la hora final a asignar
     */
    public void setHoraFinal(LocalTime horaFinal) {
        this.horaFinal = horaFinal;
    }

    /**
     * Verifica si el parqueo está activo.
     * 
     * @return true si el parqueo está activo, false en caso contrario
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * Establece el estado activo del parqueo.
     * 
     * @param activo true para indicar que el parqueo está activo, false para inactivo
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    /**
     * @param dinero cantidad de dinero que costo este parqueo
     */
    public void setMonto(float dinero){
        monto = dinero;
    }
    
    /**
     * @return cantidad de dinero que costo este parqueo
     */
    public float getMonto(){
        return monto;
    }
    
    /**
     * @return un String[] con los datos del CarroParqueo
     * 
     */
    public String[] toArray(){
        return new String[]{
          this.carro,
          this.espacio,
          this.horaInicio.toString(),
          this.horaFinal.toString(),
          String.valueOf(this.activo),
          String.valueOf(this.monto)
        };
    }
}
