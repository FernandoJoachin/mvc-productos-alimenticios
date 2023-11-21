package com.example;

import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Uso: java com.example.Main <puerto>");
            System.exit(1);
        }

        int brokerPort = Integer.parseInt(args[0]);
        MessageBroker broker = new MessageBroker();
        broker.startServer(brokerPort);

        // Puedes ajustar estos puertos según tu necesidad
        int serverPort1 = 91;
        int serverPort2 = 92;
        int serverPort3 = 93;

        // Levantar los servidores indicando el puerto en el que escucharán y la dirección ip y puerto del broker
        new Thread(() -> {
            EchoMultiServer server1 = new EchoMultiServer();
            server1.start(serverPort1);
        }).start();

        new Thread(() -> {
            EchoMultiServer server2 = new EchoMultiServer();
            server2.start(serverPort2);
        }).start();

        new Thread(() -> {
            EchoMultiServer server3 = new EchoMultiServer();
            server3.start(serverPort3);
        }).start();

        // Levantar el cliente indicando la dirección ip y puerto del broker
        MessageBroker clientBroker = new MessageBroker();
        clientBroker.startServer(brokerPort);

        // Crear un JSON para enviar al servidor a través del broker
        JSONObject jsonToSend = new JSONObject();
        jsonToSend.put("greeting", "Hola");
        jsonToSend.put("greeting2", "HOLA2");

        // Enviar el JSON al servidor a través del broker y recibir la respuesta
        String response1 = clientBroker.sendMessageToServer(jsonToSend, serverPort1);
        String response2 = clientBroker.sendMessageToServer(jsonToSend, serverPort2);
        String response3 = clientBroker.sendMessageToServer(jsonToSend, serverPort3);

        System.out.println("Respuesta del servidor 1: " + response1);
        System.out.println("Respuesta del servidor 2: " + response2);
        System.out.println("Respuesta del servidor 3: " + response3);

        // Detener el broker
        broker.stopServer();
        clientBroker.stopServer();
    }
}
