package com.example.servidor;

import org.json.JSONObject;

public class VoteProtocol {

    ServiciosServidor servicios = new ServiciosServidor();

    public JSONObject processInput(JSONObject theInput) {

        switch (theInput.getString("servicio")) {

            case "contar":
                return servicios.contar();
            case "votar":
                return servicios.votar(theInput.getString("variable1"), theInput.getInt("valor1"));
            case "registrar":
                return servicios.registrar(theInput.getString("valor1"), theInput.getString("valor2"));
            case "listar":
                return servicios.listar();
        }

        return null;
    }
}
