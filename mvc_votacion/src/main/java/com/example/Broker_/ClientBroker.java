package com.example.Broker_;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.json.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class ClientBroker {
    private JSONObject mensaje;

    // Constructor
    public ClientBroker(JSONObject mensaje) {
        this.mensaje = mensaje;
    }

    public JSONObject cliente() throws SocketException, UnknownHostException {
        InetAddress ip = null;
        try (final DatagramSocket socket = new DatagramSocket()) {
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            ip = socket.getLocalAddress();
        }
        String hostName = ip.getHostAddress();
        hostName = "192.168.253.164";
        int portNumber = 9975;
        try (
                Socket socket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));) {
            JSONObject fromServer;
            JSONObject fromUser = mensaje;

            out.println(fromUser.toString());
            fromServer = new JSONObject(in.readLine());

            return fromServer;
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }
        return null;
    }
}
