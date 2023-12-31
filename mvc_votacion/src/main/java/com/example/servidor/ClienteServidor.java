package com.example.servidor;

import java.net.*;
import java.io.*;
import org.json.*;

import com.example.cliente.Controlador;
import com.example.cliente.Vista;

public class ClienteServidor {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws Exception {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(JSONObject json) throws Exception {
        // Enviar el archivo JSON al servidor

        // Recibir la respuesta del servidor (puede ser un JSON también)
        String resp = in.readLine();
        return resp;
    }

    public void stopConnection() throws Exception {
        in.close();
        out.close();
        clientSocket.close();
    }
}