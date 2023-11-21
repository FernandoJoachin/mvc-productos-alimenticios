package com.example.Broker_;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Broker {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario el puerto del broker
        System.out.print("Ingresa el puerto del broker: ");
        int puertoBroker = Integer.parseInt(scanner.nextLine()); //9974
        boolean listening = true;
        
        try (ServerSocket serverSocket = new ServerSocket(puertoBroker)) { 
            while (listening) {
	            new MultiServerThread(serverSocket.accept()).start();
	        }
	    } catch (IOException e) {
            System.err.println("Could not listen on port " + puertoBroker);
            System.exit(-1);
        }
    }
}
