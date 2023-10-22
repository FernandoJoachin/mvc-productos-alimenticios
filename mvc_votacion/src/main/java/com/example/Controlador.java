package com.example;

import java.util.ArrayList;
import java.awt.event.*;

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

        public votoListener(int tipoBtn) {
            this.tipoBtn = tipoBtn;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            productos.get(this.tipoBtn).votar();
            switch (this.tipoBtn) {
                case 0:
                    vista.actualizarDatosGraficos(productos);
                    break;
                case 1:
                    vista.actualizarDatosGraficos(productos);
                    break;
                case 2:
                    vista.actualizarDatosGraficos(productos);
                    break;
            }
            ;
        }
    };

    public void votarPorProducto(int indice) {
        Producto producto = productos.get(indice);
        producto.votar();
        // Registra la acción en la bitácora
    }
}
