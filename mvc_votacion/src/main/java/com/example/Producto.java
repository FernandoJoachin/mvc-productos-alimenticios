package com.example;

import java.util.ArrayList;

public class Producto {
    private String nombre;
    private int votos;
    public static ArrayList<Producto> productos = new ArrayList<>();

    public Producto(String nombre) {
        this(nombre, 0);
    }

    public Producto(String nombre, int votos) {
        this.nombre = nombre;
        this.votos = votos;
    }

    public void addProducto(Producto producto) {
        productos.add(producto);
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }

    public int getVotos() {
        return this.votos;
    }

    public void votar() {
        this.votos++;
        //Actualizar votos en archivo
    }
}