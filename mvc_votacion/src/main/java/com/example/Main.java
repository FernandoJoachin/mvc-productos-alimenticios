package com.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Producto> productos = LectorArchivo.obtenerProductos();
        Vista vista = new Vista(productos);
        Controlador controlador = new Controlador(productos, vista);
    }
}