/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sistemaparqueo;

import com.mycompany.sistemaparqueo.Clases.*;
import com.mycompany.sistemaparqueo.GUI.PantallaInicio;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author kevin
 */
public class SistemaParqueo {
    public static ArrayList<String[]> ListaDeUsuarios;
    public static ArrayList<String[]> ListaDeParqueos;
    public static ArrayList<String[]> ListaDeCarros;
    public static ArrayList<String[]> ListaDeTarjetas;
    public static ArchivoController controladorArchivos = new ArchivoController();
    
    public static void main(String[] args) throws IOException {

        ListaDeUsuarios = controladorArchivos.leerDesdeArchivo("usuarios.txt");
        ListaDeTarjetas = controladorArchivos.leerDesdeArchivo("tarjetas.txt");
        
        PantallaInicio a = new PantallaInicio();
    }
}
