package com.example.cliente;

import java.util.ArrayList;

import org.json.JSONObject;

import com.example.Bitacora;
import com.example.Producto;

import java.awt.event.*;

public class Controlador {
    private Vista vista;
    private ArrayList<Producto> productos;
    private Bitacora bitacora;
    private Instrucciones instrucciones = new Instrucciones();
    private String ip;
    private int puerto;

    public Controlador(Vista vista, String ip, int puerto) {
        this.vista = vista;
        this.ip = ip;
        this.puerto = puerto;
        this.bitacora = new Bitacora();
        bitacora.crearArchivo();

        vista.addVotoListenerBtn1(new votoListener(1, this.ip, this.puerto));
        vista.addVotoListenerBtn2(new votoListener(2, this.ip, this.puerto));
        vista.addVotoListenerBtn3(new votoListener(3, this.ip, this.puerto));
    }

    class votoListener implements ActionListener {
        private int tipoBtn;
        private String ip;
        private int puerto;

        public votoListener(int tipoBtn, String ip, int puerto) {
            this.tipoBtn = tipoBtn;
            this.ip = ip;
            this.puerto = puerto;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (this.tipoBtn) {
                case 1:
                    try {
                        Client cliente = new Client();
                        cliente.startConnection(this.ip, this.puerto);
                        String response = cliente.sendMessage(instrucciones.generarVotar(vista.etiquetaProducto1.getText()));
                        JSONObject votoProducto = new JSONObject(response);

                        vista.actualizarDatosGraficos(votoProducto.getString("respuesta1"),
                                votoProducto.getInt("valor1"));
                    } catch (Exception error) {
                        error.printStackTrace();
                        System.out.println("ERROR AL VOTAR EL PRIMER PRODUCTO");
                    }
                    break;
                case 2:
                    try {
                        Client cliente = new Client();
                        cliente.startConnection(this.ip, this.puerto);
                        String response = cliente.sendMessage(instrucciones.generarVotar(vista.etiquetaProducto2.getText()));

                        JSONObject votoProducto = new JSONObject(response);

                        vista.actualizarDatosGraficos(votoProducto.getString("respuesta1"),
                                votoProducto.getInt("valor1"));
                    } catch (Exception error) {
                        error.printStackTrace();
                        System.out.println("ERROR AL VOTAR EL SEGUNDO PRODUCTO");
                    }
                    break;
                case 3:
                    try {
                        Client cliente = new Client();
                        cliente.startConnection(this.ip, this.puerto);
                        String response = cliente.sendMessage(instrucciones.generarVotar(vista.etiquetaProducto3.getText()));

                        JSONObject votoProducto = new JSONObject(response);

                        vista.actualizarDatosGraficos(votoProducto.getString("respuesta1"),
                                votoProducto.getInt("valor1"));

                    } catch (Exception error) {
                        error.printStackTrace();
                        System.out.println("ERROR AL VOTAR EL TECER PRODUCTO");
                    }
                    break;
            }
            ;
        }
    };

    public void votarPorProducto(int indice) {
        Producto producto = productos.get(indice);
        producto.votar();
        String texto = "Se ha votado por el producto " + producto.getNombre() + " en el metodo votarPorProducto()";
        bitacora.escribirArchivo(texto);
    }
}