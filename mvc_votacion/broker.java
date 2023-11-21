package com.example;

import org.json.JSONObject;

public class MessageBroker {

    private EchoMultiServer echoServer;

    public MessageBroker() {
        echoServer = new EchoMultiServer();
    }

    public void startServer(int port) {
        echoServer.start(port);
    }

    public void stopServer() {
        echoServer.stop();
    }

    public String sendMessageToServer(JSONObject json, int serverPort) {
        String response = null;
        try {
            GreetClient greetClient = new GreetClient();
            greetClient.startConnection("localhost", serverPort);
            response = greetClient.sendMessage(json);
            greetClient.stopConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
