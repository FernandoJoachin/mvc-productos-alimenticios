package com.example.Broker_;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

import com.example.LectorArchivo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

import com.example.LectorArchivo;

public class Broker {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingresa el IP de la computadora: ");
        String ipAdress = scanner.nextLine();

        LectorArchivo.EscribirArchivo(ipAdress, "src/main/java/com/example/ipAdress.txt");

        System.out.print("Ingresa el puerto del broker: ");
        int puertoBroker = Integer.parseInt(scanner.nextLine());

        try (ServerSocket serverSocket = new ServerSocket(puertoBroker)) {
            boolean listening = true;

            while (listening) {
                new MultiServerThread(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + puertoBroker);
            System.exit(-1);
        }
    }
}

