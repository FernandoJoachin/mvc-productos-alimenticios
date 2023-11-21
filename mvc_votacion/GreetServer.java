package com.example.servidor;

import java.net.*;
import java.io.*;
import org.json.*;

public class GreetServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) throws Exception {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        // Recibir el archivo JSON del cliente
        String jsonStr = in.readLine();
        JSONObject receivedJson = new JSONObject(jsonStr);



        // Realizar alguna operación con el JSON recibido
        String greeting = receivedJson.getString("greeting");
        if ("Hola".equals(greeting)) {
            JSONObject responseJson = new JSONObject();
            responseJson.put("response", "Holaaaa");

            // Enviar el JSON de respuesta al cliente
            out.println(responseJson.toString());
        } else {
            out.println("unrecognised greeting");
        }
    }

    public void stop() throws Exception {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }

    public static void main(String[] args) throws Exception {
        GreetServer server = new GreetServer();
        server.start(6666);
    }
}