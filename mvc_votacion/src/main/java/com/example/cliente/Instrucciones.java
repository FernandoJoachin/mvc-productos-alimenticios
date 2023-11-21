package com.example.cliente;

import org.json.JSONObject;

public class Instrucciones {
    public JSONObject generarVotar(String nombre) {

        JSONObject jsonToSend = new JSONObject();
        jsonToSend.put("servicio", "ejecutar");
        jsonToSend.put("variables", "2");
        jsonToSend.put("variable1", "servicio");
        jsonToSend.put("valor1", "votar");
        jsonToSend.put("variable2", nombre);
        jsonToSend.put("valor2", "1");

        return jsonToSend;
    }

    public JSONObject generarContar() {

        JSONObject jsonToSend = new JSONObject();
        jsonToSend.put("servicio", "ejecutar");
        jsonToSend.put("variables", "1");
        jsonToSend.put("variable1", "servicio");
        jsonToSend.put("valor1", "contar");

        return jsonToSend;
    }

    public JSONObject generarListar(String nombreServicio) {

        JSONObject jsonToSend = new JSONObject();
        jsonToSend.put("servicio", "listar");
        jsonToSend.put("variables", "1");
        jsonToSend.put("variable1", "palabra");
        jsonToSend.put("valor1", nombreServicio);

        return jsonToSend;
    }
}
