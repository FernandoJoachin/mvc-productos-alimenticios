package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LectorArchivo {

    public static ArrayList<Producto> obtenerProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        try {
            List<String> nombresProductos = Files.readAllLines(Paths.get("src/main/java/com/example/productos.txt"));
            for (String nombre : nombresProductos) {
                Producto producto = new Producto(nombre);
                productos.add(producto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productos;
    }
}