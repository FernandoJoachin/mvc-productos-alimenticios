package com.example.servidor;

import java.util.ArrayList;
import org.json.JSONObject;
import com.example.LectorArchivo;
import com.example.Producto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class ServiciosServidor {

    private ArrayList<Producto> productos;
    private String[] nombreProductos;
    private String[] votoProductos;

    public ServiciosServidor() {
        this.productos = new ArrayList<>();
        this.nombreProductos = LectorArchivo.lecturaArchivo("src/main/java/com/example/productos.txt");
        this.votoProductos = LectorArchivo.lecturaArchivo("src/main/java/com/example/votos.txt");

        if (nombreProductos.length == votoProductos.length) {
            for (int i = 0; i < nombreProductos.length; i++) {
                Producto producto = new Producto(nombreProductos[i], Integer.parseInt(votoProductos[i]));
                this.productos.add(producto);
            }
        }
    }

    public static JSONObject generarRegistrar(String ipServidor, int puerto, String nombreServicio, int parametros) {

        JSONObject jsonToSend = new JSONObject();
        jsonToSend.put("servicio", "GenerarRegistrar");
        jsonToSend.put("variables", "4");
        jsonToSend.put("variable1", "servidor");
        jsonToSend.put("valor1", ipServidor);
        jsonToSend.put("variable2", "puerto");
        jsonToSend.put("valor2", puerto);
        jsonToSend.put("variable3", "servicio");
        jsonToSend.put("valor3", nombreServicio);
        jsonToSend.put("variable4", "parametros");
        jsonToSend.put("valor4", parametros);

        return jsonToSend;
    }

    public JSONObject contar() {
        JSONObject jsonToSend = new JSONObject();

        jsonToSend.put("servicio", "contar");
        jsonToSend.put("respuestas", productos.size());

        for (int i = 1; i <= productos.size(); i++) {
            jsonToSend.put("respuesta" + i, productos.get(i - 1).getNombre());
            jsonToSend.put("valor" + i, productos.get(i - 1).getVotos());
        }

        return jsonToSend;
    }

    public JSONObject votar(String nombre, int votos) {
        Producto productoSeleccionado = null;

        for (Producto producto : this.productos) {
            if (nombre.equals(producto.getNombre())) {
                productoSeleccionado = producto;

                String record = Bitacora.registrar(productoSeleccionado.getNombre());
                LectorArchivo.EscribirArchivo(record, "src/main/java/com/example/bitacora.txt", true);

                break;
            }
        }

        productoSeleccionado.votar();
        String contadorVotos = "";
        for (Producto producto : this.productos) {
            contadorVotos += producto.getVotos() + "\n";
        }

        LectorArchivo.EscribirArchivo(contadorVotos, "src/main/java/com/example/votos.txt");
        
        JSONObject jsonToSend = new JSONObject();
        
        jsonToSend.put("servicio", "votar");
        jsonToSend.put("respuestas", 1);
        jsonToSend.put("respuesta1", productoSeleccionado.getNombre());
        jsonToSend.put("valor1", productoSeleccionado.getVotos());

        return jsonToSend;
    }

    public JSONObject registrar(String evento, String fecha) {

        String entrada = fecha + " " + evento;
        LectorArchivo.EscribirArchivo(entrada, "src/main/java/com/example/bitacora.txt");

        String[] listaEventos = LectorArchivo.lecturaArchivo("src/main/java/com/example/bitacora.txt");
    

        JSONObject jsonToSend = new JSONObject();

        jsonToSend.put("servicio", "registrar");
        jsonToSend.put("respuestas", 1);
        jsonToSend.put("respuesta1", "eventos");
        jsonToSend.put("valor1", listaEventos.length);

        return jsonToSend;
    }

    public JSONObject listar() {

        String[] listaEventos = LectorArchivo.lecturaArchivo("src/main/java/com/example/bitacora.txt");

        JSONObject jsonToSend = new JSONObject();

        jsonToSend.put("servicio", "listar");
        jsonToSend.put("respuestas", listaEventos.length);

        for (int i = 1; i <= listaEventos.length; i++) {
            jsonToSend.put("respuesta" + i, "evento");
            jsonToSend.put("valor" + i, listaEventos[i - 1]);
        }

        return jsonToSend;
    }
}
