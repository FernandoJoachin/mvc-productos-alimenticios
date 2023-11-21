package com.example.Broker_;

import java.net.SocketException;
import java.net.UnknownHostException;

import org.json.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Protocol {
    ListaDeServicios servicios = new ListaDeServicios();

    public JSONObject processInput(JSONObject input) throws SocketException, UnknownHostException {

        switch (input.getString("servicio")) {
            case "registar":
                return servicios.registrar(input.getString("valor1"),
                        input.getInt("valor2"), input.getString("valor3"),
                        input.getInt("valor4"));
            case "listar":
                return servicios.listarServicios(input.getString("valor1"));
            case "ejecutar":
                return switchEjecutar(input.getString("valor1"), input);
        }
        return null;
    }

    public String[][] splitter(String input) {
        String[] lineas = input.split(",");
        String[][] output = new String[lineas.length][2];
        for (int i = 0; i < lineas.length; i++) {
            output[i] = lineas[i].split(":");
        }
        return output;
    }

    public JSONObject switchEjecutar(String servicios, JSONObject mensaje) throws SocketException, UnknownHostException {
        ClientBroker cliente = null;
        switch (servicios) {
            case "contar":
                cliente = new ClientBroker(armarMsjSinParam(mensaje));
                break;
            case "votar":
            System.out.println("VOTANDO");
                cliente = new ClientBroker(armarMensajeVotar(mensaje));
                break;
            case "registrar":
                cliente = new ClientBroker(armarMensajeRegistrar(mensaje));
                break;
            case "listar":
                cliente = new ClientBroker(armarMsjSinParam(mensaje));
                break;
        }
        return cliente.cliente();
    }

    public JSONObject armarMensajeVotar(JSONObject message) {
        JSONObject jsonToSend = new JSONObject();
        jsonToSend.put("servicio", message.getString("valor1"));
        jsonToSend.put("variables", "1");
        jsonToSend.put("variable1", message.getString("variable2"));
        jsonToSend.put("valor1", message.getInt("valor2"));

        return jsonToSend;
    }

    public JSONObject armarMsjSinParam(JSONObject message) {
        JSONObject jsonToSend = new JSONObject();
        jsonToSend.put("servicio", message.getString("valor1"));
        jsonToSend.put("variables", "0");

        return jsonToSend;
    }

    public JSONObject armarMensajeRegistrar(JSONObject message) {
        JSONObject jsonToSend = new JSONObject();
        jsonToSend.put("servicio", message.getString("servicio"));
        jsonToSend.put("variables", "2");
        jsonToSend.put("variable1", message.getString("variable1"));
        jsonToSend.put("valor1", message.getString("valor1"));
        jsonToSend.put("variable2", message.getString("variable2"));
        jsonToSend.put("valor2", message.getString("valor2"));

        return jsonToSend;
    }
}
