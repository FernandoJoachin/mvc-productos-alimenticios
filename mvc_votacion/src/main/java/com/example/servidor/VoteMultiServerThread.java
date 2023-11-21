package com.example.servidor;

import java.net.*;

import org.json.JSONObject;

import java.io.*;

public class VoteMultiServerThread extends Thread {
    private Socket socket = null;

    public VoteMultiServerThread(Socket socket) {
        super("VoteMultiServerThread");
        this.socket = socket;
    }

    public void run() {

        try (
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()));) {
            String inputLine, outputLine;
            VoteProtocol vp = new VoteProtocol();
            while ((inputLine = in.readLine()) != null) {

                outputLine = vp.processInput(new JSONObject(inputLine)).toString();
                out.println(outputLine);
                if (outputLine.equals("Bye"))
                    break;
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
