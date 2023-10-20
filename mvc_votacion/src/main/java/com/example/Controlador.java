package com.example;

import java.util.List;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Controlador {
    private List<Producto> productos;
    private Vista vista;
    private Producto producto;

    public Controlador(Producto producto, Vista vista) {
        this.producto = producto;
        this.vista = vista;

        vista.addVotoListenerBtn1(new votoListener());

    }

    class votoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            vista.producto1.votar();
            contadorProducto1Label.setText("Contador: " + producto1.getVotos());
        }
    };

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
