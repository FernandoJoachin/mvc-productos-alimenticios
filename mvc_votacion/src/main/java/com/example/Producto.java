package com.example;

public class Producto {
    private String nombre;
    private int votos;

    public Producto(String nombre) {
        this.nombre = nombre;
        this.votos = 0;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getVotos() {
        return this.votos;
    }

    public void votar() {
        this.votos++;
        // Lógica para guardar la votación en un archivo aquí
    }
}