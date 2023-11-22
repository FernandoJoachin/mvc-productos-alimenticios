package com.example.Broker_;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

import com.example.LectorArchivo;

public class Broker {
    public Broker() {

    }

    public void init() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingresa el IP de la computadora: ");
        String ipAddress = scanner.nextLine();

        System.out.print("Ingresa el puerto del broker: ");
        int puertoBroker = Integer.parseInt(scanner.nextLine());

        try (ServerSocket serverSocket = new ServerSocket(puertoBroker)) {
            boolean listening = true;

            while (listening) {
                new MultiServerThread(serverSocket.accept(), ipAddress).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + puertoBroker);
            System.exit(-1);
        }
    }

    public static void main(String[] args) throws Exception {
        Broker broker = new Broker();
        broker.init();
    }
}