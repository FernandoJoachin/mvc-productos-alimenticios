package com.example.cliente;

import java.net.*;
import java.io.*;
import org.json.*;
import com.example.LectorArchivo;

public class Client {
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
        out.println(json.toString());

        // Recibir la respuesta del servidor (puede ser un JSON también)
        String resp = in.readLine();
        return resp;
    }

    public void stopConnection() throws Exception {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static void main(String[] args) {
        String[] ip = LectorArchivo.lecturaArchivo("src/main/java/com/example/ipAdress.txt");
        String ipAdress = ip[0];
        
        Vista vista = new Vista(ipAdress, 9974);
        Controlador controlador = new Controlador(vista, ipAdress, 9974);
    }
}