package com.example.servidor;

import java.net.*;
import java.io.*;

public class VoteServer {

    public VoteServer() {

    }

    public void init() throws Exception {
        int serverPortNumber = 9975;
        boolean listening = true;

        try (ServerSocket serverSocket = new ServerSocket(serverPortNumber)) {
            while (listening) {
                new VoteMultiServerThread(serverSocket.accept()).start();
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