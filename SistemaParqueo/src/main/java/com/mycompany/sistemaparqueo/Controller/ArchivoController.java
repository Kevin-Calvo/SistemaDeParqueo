package com.mycompany.sistemaparqueo.Controller;

import com.mycompany.sistemaparqueo.SistemaParqueo;
import java.io.*;
import java.util.ArrayList;

/**
 * La clase ArchivoController se encarga de la lectura y escritura de archivos que contienen datos 
 * relacionados con el sistema de parqueo. Los métodos de esta clase permiten leer y escribir en archivos 
 * que almacenan información de usuarios y otros elementos.
 * 
 * @author kevin
 */
public class ArchivoController {

    /**
     * Lee un archivo y devuelve una lista de elementos (cada elemento es un arreglo de cadenas).
     * 
     * @param rutaArchivo La ruta del archivo que se desea leer.
     * @return Una lista de arreglos de cadenas donde cada arreglo contiene los datos de una línea en el archivo.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public static ArrayList<String[]> leerDesdeArchivo(String rutaArchivo) throws IOException {
        ArrayList<String[]> elementos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                // Se separa la línea por el delimitador ';' y se agrega a la lista
                String[] datos = linea.split(";");
                elementos.add(datos);
            }
        }
        return elementos;
    }

    /**
     * Escribe una lista de elementos en un archivo, donde cada elemento es un arreglo de cadenas.
     * Cada arreglo de cadenas se convierte en una línea del archivo, y los elementos están separados por punto y coma (';').
     * 
     * @param lista La lista de elementos a escribir en el archivo, donde cada elemento es un arreglo de cadenas.
     * @param ruta La ruta del archivo en el que se desea escribir.
     */
    public static void escribirArchivo(ArrayList<String[]> lista, String ruta) {
        try (FileWriter writer = new FileWriter(ruta)) {
            for (String[] fila : lista) {
                // Unir los elementos del arreglo usando ';' como delimitador
                String linea = String.join(";", fila);
                
                // Escribir la línea en el archivo seguida de un salto de línea
                writer.write(linea + "\n");
            }
        } catch (IOException e) {
            // Manejo de errores al escribir en el archivo
            e.printStackTrace();
        }
    }
}
