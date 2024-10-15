/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaparqueo.Clases;

import com.mycompany.sistemaparqueo.SistemaParqueo;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author kevin
 */
public class ArchivoController {
    
    // Método para leer usuarios desde un archivo
    public static ArrayList<String[]> leerDesdeArchivo(String rutaArchivo) throws IOException {
        ArrayList<String[]> elementos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(";");
                elementos.add(datos);
                }
            }
        return elementos;
    }
    
    // Función para escribir un ArrayList de String[] en un archivo
    public static void escribirArchivo(ArrayList<String[]> lista, String ruta) {
        try (FileWriter writer = new FileWriter(ruta)) {
            for (String[] fila : lista) {
                // Unir los elementos del String[] con ';' como delimitador
                String linea = String.join(";", fila);
                
                // Escribir la línea en el archivo seguida de un salto de línea
                writer.write(linea + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
}
