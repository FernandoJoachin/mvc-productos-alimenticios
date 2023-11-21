package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LectorArchivo {
    
    public static String[] lecturaArchivo(String archivo) {
        
        String cadenaDeNombres = "";
        String[] cadenaLista = new String[3];
        int i = 0;

        try {
            File arch = new File(archivo);
            FileReader archivoALeer = new FileReader(arch);
            BufferedReader buff = new BufferedReader(archivoALeer);
            while (i < 3) {
                
                cadenaDeNombres = buff.readLine();
                //System.out.println(cadenaDeNombres);
                if (cadenaDeNombres != null) {

                    cadenaLista[i] = cadenaDeNombres;

                } else {

                    break;
                }
                //System.out.println(i);
                i++;
            }

        } catch (FileNotFoundException excepcion) {
            System.out.println("No se encontró el archivo" + archivo);
        } catch (IOException excepcionIO) {
            System.out.println("");
        }
        return cadenaLista;
    }

    public static void EscribirArchivo(String cadenaPermutada, String nombre) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            File file = new File(nombre);
            // Si el archivo no existe, se crea!
            if (!file.exists()) {
                file.createNewFile();
            }
            // flag true, indica adjuntar información al archivo.
            fw = new FileWriter(file.getAbsoluteFile());
            bw = new BufferedWriter(fw);
            bw.write(cadenaPermutada);
            bw.newLine();
            System.out.println("información agregada!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //Cierra instancias de FileWriter y BufferedWriter
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}