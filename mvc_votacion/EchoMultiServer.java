package com.example.servidor;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoMultiServer {

    private ServerSocket serverSocket;
    private ExecutorService executorService;

    public void start(int port) {
        try {
            System.out.println(port);
            serverSocket = new ServerSocket(port);
            executorService = Executors.newCachedThreadPool();

            while (true) {
                Socket clientSocket = serverSocket.accept();
                executorService.submit(new EchoClientHandler(clientSocket));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            serverSocket.close();
            executorService.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class EchoClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public EchoClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    try {
                        // JSONObject jsonInput = new JSONObject(inputLine);
                        // Iterator<String> keys = jsonInput.keys();

                        // JSONObject jsonOutput = new JSONObject();

                        // while (keys.hasNext()) {
                        //     String key = keys.next();
                        //     String value = jsonInput.getString(key);
                        //     jsonOutput.put(key, value);
                        // }
                        // out.println(jsonOutput.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        EchoMultiServer server = new EchoMultiServer();
        server.start(5555);
    }
}
