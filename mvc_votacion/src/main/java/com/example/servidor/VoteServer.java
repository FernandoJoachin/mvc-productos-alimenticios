package com.example.servidor;

import java.net.*;
import java.io.*;

public class VoteServer {
    public static void main(String[] args) throws Exception {

        int serverPortNumber = 9975;// Integer.parseInt(args[0]);
        // int brokerPortNumber = 9974;
        boolean listening = true;

        // ClienteServidor clienteServidor = new ClienteServidor();
        // clienteServidor.startConnection("192.168.253.164", brokerPortNumber);
        // String respuesta = clienteServidor.sendMessage(ServiciosServidor.generarRegistrar("192.168.253.164", brokerPortNumber, "votar", 1));

        // System.out.println(respuesta);
        try (ServerSocket serverSocket = new ServerSocket(serverPortNumber)) {
            while (listening) {
                new VoteMultiServerThread(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + serverPortNumber);
            System.exit(-1);
        }
    }

}
