package com.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Scanner;

import com.example.Broker_.MultiServerThread;
import com.example.cliente.Controlador;
import com.example.cliente.Vista;
import com.example.servidor.VoteMultiServerThread;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario el puerto del broker
        System.out.print("Ingresa el puerto del broker: ");
        int puertoBroker = Integer.parseInt(scanner.nextLine());

        // Solicitar al usuario la dirección IP del cliente
        System.out.print("Ingresa la dirección IP del cliente: ");
        String ipCliente = scanner.nextLine();

        scanner.close();
        
        Vista vista = new Vista(ipCliente, puertoBroker);
        Controlador controlador = new Controlador(vista, ipCliente, puertoBroker);
    }
}