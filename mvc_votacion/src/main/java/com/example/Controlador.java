package com.example;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Controlador {
    private Vista vista;
    private ArrayList<Producto> productos;

    public Controlador(ArrayList<Producto> productos, Vista vista) {
        this.productos = productos;
        this.vista = vista;

        vista.addVotoListenerBtn1(new votoListener(0));
        vista.addVotoListenerBtn2(new votoListener(1));
        vista.addVotoListenerBtn3(new votoListener(2));
    }

    class votoListener implements ActionListener {
        private int tipoBtn;
        public votoListener(int tipoBtn){
            this.tipoBtn = tipoBtn;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            productos.get(this.tipoBtn).votar();
            switch(this.tipoBtn){
                case 0:
                    vista.contadorProducto1Label.setText("Contador: " +  productos.get(0).getVotos());
                    break;
                case 1:
                    vista.contadorProducto2Label.setText("Contador: " +  productos.get(1).getVotos());
                    break;
                case 2:
                    vista.contadorProducto3Label.setText("Contador: " +  productos.get(2).getVotos());
                    break;
            };
        }
    };

    public void votarPorProducto(int indice) {
        Producto producto = productos.get(indice);
        producto.votar();
        vista.actualizarContadores();
        // Registra la votación en el archivo correspondiente
        // Registra la acción en la bitácora
    }
}
