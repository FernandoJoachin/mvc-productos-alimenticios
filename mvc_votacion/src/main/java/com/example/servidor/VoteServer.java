package com.example.servidor;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class VoteServer {

    public VoteServer() {

    }

    public void init() throws Exception {
         Scanner scanner = new Scanner(System.in);

/*         System.out.print("Ingresa el IP de la computadora: ");
        String ipAddress = scanner.nextLine();  */
        System.out.print("Ingresa el IP de la computadora: ");
        String ipAddress = scanner.nextLine();

        System.out.print("Ingresa el puerto del Servidor: ");
        int serverPortNumber = Integer.parseInt(scanner.nextLine());
        
        boolean listening = true;

        try (ServerSocket serverSocket = new ServerSocket(serverPortNumber)) {
            while (listening) {
                new VoteMultiServerThread(serverSocket.accept(), ipAddress).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + serverPortNumber);
            System.exit(-1);
        }
    }

    public static void main(String[] args) throws Exception {
        VoteServer vs = new VoteServer();
        vs.init();
    }
}