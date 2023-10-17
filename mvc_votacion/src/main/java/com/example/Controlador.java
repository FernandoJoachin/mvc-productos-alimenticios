package com.example;

import java.util.List;

public class Controlador {
    private List<Producto> productos;
    private Vista vista;

    public Controlador(List<Producto> productos, Vista vista) {
        this.productos = productos;
        this.vista = vista;
    }

    public void iniciar() {
        vista.iniciar();
    }

    public void votarPorProducto(int indice) {
        Producto producto = productos.get(indice);
        producto.votar();
        vista.actualizarContadores();
        // Registra la votación en el archivo correspondiente
        // Registra la acción en la bitácora
    }
}
