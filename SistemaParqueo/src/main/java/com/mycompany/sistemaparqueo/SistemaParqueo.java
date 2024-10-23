package com.mycompany.sistemaparqueo;

import com.mycompany.sistemaparqueo.Clases.*;
import com.mycompany.sistemaparqueo.Controller.ArchivoController;
import com.mycompany.sistemaparqueo.GUI.PantallaInicio;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase principal del sistema de parqueo que se encarga de inicializar y gestionar
 * la lectura de datos desde archivos y la visualización de la pantalla de inicio.
 * 
 * <p>El sistema carga las listas de usuarios, tarjetas, y carros desde archivos de texto
 * y lanza la pantalla de inicio del sistema.</p>
 * 
 * @author kevin
 */
public class SistemaParqueo {

    /**
     * Lista que contiene los usuarios registrados en el sistema.
     */
    public static ArrayList<String[]> ListaDeUsuarios;

    /**
     * Lista que contiene los parqueos disponibles en el sistema.
     */
    public static ArrayList<String[]> ListaDeParqueos;
    
    /**
     * Lista que contiene los espacios correspondientes a cada parqueo en el sistema.
     */
    public static ArrayList<String[]> ListaDeEspacios;

    /**
     * Lista que contiene los carros registrados en el sistema.
     */
    public static ArrayList<String[]> ListaDeCarros;

    /**
     * Lista que contiene las tarjetas de los usuarios en el sistema.
     */
    public static ArrayList<String[]> ListaDeTarjetas;
    
    /**
     * Lista que contiene el historial de todos los carros que se han parqueado
     */
    public static ArrayList<String[]> ListaDeCarroParqueo;

    /**
     * Instancia del controlador de archivos para manejar la lectura y escritura de datos.
     */
    public static ArchivoController controladorArchivos = new ArchivoController();
    
    /**
     * Método principal del sistema que se encarga de leer los datos desde los archivos
     * y lanzar la pantalla de inicio.
     * 
     * @param args Argumentos de la línea de comandos.
     * @throws IOException Si ocurre un error al leer los archivos de datos.
     */
    public static void main(String[] args) throws IOException {

        // Carga los usuarios, tarjetas y carros desde los archivos correspondientes.
        ListaDeUsuarios = controladorArchivos.leerDesdeArchivo("usuarios.txt");
        ListaDeTarjetas = controladorArchivos.leerDesdeArchivo("tarjetas.txt");
        ListaDeCarros = controladorArchivos.leerDesdeArchivo("carros.txt");
        ListaDeParqueos = controladorArchivos.leerDesdeArchivo("parqueos.txt");
        ListaDeEspacios = controladorArchivos.leerDesdeArchivo("espacios.txt");
        ListaDeCarroParqueo = controladorArchivos.leerDesdeArchivo("carroparqueo.txt");
        
        // Inicia la pantalla de inicio del sistema.
        PantallaInicio a = new PantallaInicio();
    }
}

