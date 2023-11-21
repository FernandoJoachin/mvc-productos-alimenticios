package com.example.Broker_;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Servicio {
    private String DireccionIp;
    private int portServer;
    private String nombreServicio;
    private int numServ;
    
    //constructor

    public Servicio(String DireccionIp, int portServer, String nombreServicio, int numServ) {
        this.DireccionIp = DireccionIp;
        this.portServer = portServer;
        this.nombreServicio = nombreServicio;
        this.numServ = numServ;
    }
    
    //getter y setter

    public String getDireccionIp() {
        return DireccionIp;
    }

    public void setDireccionIp(String DireccionIp) {
        this.DireccionIp = DireccionIp;
    }

    public int getPortServer() {
        return portServer;
    }

    public void setPortServer(int portServer) {
        this.portServer = portServer;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public int getNumServ() {
        return numServ;
    }

    public void setNumServ(int numServ) {
        this.numServ = numServ;
    }
}
