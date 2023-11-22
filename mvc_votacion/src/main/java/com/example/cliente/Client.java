package com.example.cliente;

import org.json.*;
import com.example.LectorArchivo;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public Client() {

    }

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

    public void init() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Ingresa el IP de la computadora: ");
            String ipAddress = scanner.nextLine();

            System.out.print("Ingresa el puerto del broker: ");
            int puertoBroker = Integer.parseInt(scanner.nextLine());

            Vista vista = new Vista(ipAddress, puertoBroker);
            Controlador controlador = new Controlador(vista, ipAddress, puertoBroker);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.init();
    }
}
