package com.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bitacora {

    private String nombreArchivo;
    private SimpleDateFormat formatoFecha;

    public Bitacora(){
        this.nombreArchivo = "bitacora.txt";
        this.formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public void crearArchivo() {
        try {
            File archivo = new File(nombreArchivo);
            if (archivo.createNewFile()) {
                System.out.println("Archivo creado: " + archivo.getName());
            } else {
                System.out.println("Archivo modificado: " + archivo.getName());
            }
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }
    }

    public void escribirArchivo(String texto){
        try {
            FileWriter archivo = new FileWriter(nombreArchivo, true);
            try (BufferedWriter bw = new BufferedWriter(archivo)) {
                String fecha = formatoFecha.format(new Date());
                bw.write(texto + " " + fecha); 
                bw.newLine(); 
            }
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }       
    }
    
}
