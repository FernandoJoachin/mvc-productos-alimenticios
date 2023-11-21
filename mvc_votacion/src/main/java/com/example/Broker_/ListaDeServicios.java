package com.example.Broker_;

import java.util.ArrayList;

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
public class ListaDeServicios {
    ArrayList<Servicio> listaServicios = new ArrayList<>();

    public JSONObject registrar(String DireccionIp, int portServer, String nombreServicio, int numParam) {
        if (DireccionIp.equals("") || (nombreServicio.equals(""))) {
            Servicio servicio = new Servicio(DireccionIp, portServer, nombreServicio, numParam);
            listaServicios.add(servicio);

            JSONObject jsonToSend = new JSONObject();
            jsonToSend.put("servicio", "registrar");
            jsonToSend.put("respuestas", "1");
            jsonToSend.put("respuesta1", servicio.getNombreServicio());
            jsonToSend.put("valor1", "1");

            return jsonToSend;
        } else {
            return null;
        }
    }

    public JSONObject listarServicios(String servicio){

        JSONObject jsonToSend = new JSONObject();
        jsonToSend.put("servicio", "listar");

        if(!servicio.equals("")){
            if(listaServicios.size()>0) {
                jsonToSend.put("respuestas", listaServicios.size() + 1);

                for (int numElementos = 0; numElementos < listaServicios.size(); numElementos++){
                    int respuesta = 1;
                    if (servicio.equals(listaServicios.get(numElementos).getNombreServicio())){
                        jsonToSend.put("respuesta" + respuesta, servicio);
                        jsonToSend.put("valor" + respuesta, listaServicios.get(numElementos).getDireccionIp());                 
                    }
                }
            } else {
                jsonToSend.put("respuestas", 0);
            }            
        } else {
            int contador = 1;
            jsonToSend.put("respuestas", listaServicios.size() + 1);
            for (Servicio servicioTodo : listaServicios) {
                jsonToSend.put("respuesta" + contador, servicioTodo);
                jsonToSend.put("valor" + contador, servicioTodo.getDireccionIp());                 
            }
        }

        return jsonToSend;
    }
}
