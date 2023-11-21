package com.example.cliente;

import javax.swing.*;
import java.awt.event.ActionListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.json.JSONObject;

import java.awt.*;

public class Vista {
    private JButton botonProducto1;
    private JButton botonProducto2;
    private JButton botonProducto3;
    private JButton botonContar;
    private JButton botonListar;
    protected JLabel etiquetaProducto1;
    protected JLabel etiquetaProducto2;
    protected JLabel etiquetaProducto3;
    protected JLabel etiquetaContar;
    protected JLabel etiquetaListar;

    private Instrucciones instrucciones = new Instrucciones();

    private DefaultCategoryDataset graficoBarras;
    private DefaultPieDataset<String> graficoPastel;

    public Vista(String ip, int puerto) {
        this.iniciar(ip, puerto);
    }

    public void iniciar(String ip, int puerto) {

        try {
            Client cliente = new Client();
            cliente.startConnection(ip, puerto);
            String response = cliente.sendMessage(instrucciones.generarContar());
            JSONObject votosProducto = new JSONObject(response);

            graficoBarras = new DefaultCategoryDataset();
            graficoPastel = new DefaultPieDataset<>();

            graficoBarras.addValue(votosProducto.getInt("valor1"), "Votos", votosProducto.getString("respuesta1"));
            graficoPastel.setValue(votosProducto.getString("respuesta1"), votosProducto.getInt("valor1"));

            graficoBarras.addValue(votosProducto.getInt("valor2"), "Votos", votosProducto.getString("respuesta2"));
            graficoPastel.setValue(votosProducto.getString("respuesta2"), votosProducto.getInt("valor2"));

            graficoBarras.addValue(votosProducto.getInt("valor3"), "Votos", votosProducto.getString("respuesta3"));
            graficoPastel.setValue(votosProducto.getString("respuesta3"), votosProducto.getInt("valor3"));

            generarGraficoBarras();
            generarGraficoPastel();

            JFrame marco = new JFrame("Votaciones");
            marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            marco.setSize(700, 400);
            marco.setLayout(new GridLayout(3, 1));

            this.etiquetaProducto1 = new JLabel(votosProducto.getString("respuesta1"));
            this.etiquetaProducto2 = new JLabel(votosProducto.getString("respuesta2"));
            this.etiquetaProducto3 = new JLabel(votosProducto.getString("respuesta3"));
            

            this.botonProducto1 = new JButton("Votar por " + votosProducto.getString("respuesta1"));
            this.botonProducto2 = new JButton("Votar por " + votosProducto.getString("respuesta2"));
            this.botonProducto3 = new JButton("Votar por " + votosProducto.getString("respuesta3"));

            // this.botonContar = new JButton("Contar");
            // this.botonListar = new JButton("Listar");

            marco.add(etiquetaProducto1);
            marco.add(botonProducto1);

            marco.add(etiquetaProducto2);
            marco.add(botonProducto2);

            marco.add(etiquetaProducto3);
            marco.add(botonProducto3);

            // marco.add(botonContar);
            // marco.add(botonListar);

            marco.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void addVotoListenerBtn1(ActionListener voto) {
        botonProducto1.addActionListener(voto);
    }

    void addVotoListenerBtn2(ActionListener voto) {
        botonProducto2.addActionListener(voto);
    }

    void addVotoListenerBtn3(ActionListener voto) {
        botonProducto3.addActionListener(voto);
    }

    void addVistaListenerContar(){
        
    }

    void addVistaListenerListar(){

    }

    public void actualizarDatosGraficos(String nombreProducto, int votos) {
        graficoBarras.setValue(votos, "Votos", nombreProducto);
        graficoPastel.setValue(nombreProducto, votos);
    }

    public void generarGraficoBarras() {
        JFreeChart grafico = ChartFactory.createBarChart(
                "Votos por Producto", "Productos", "Votos",
                graficoBarras, PlotOrientation.VERTICAL, true, true, false);

        ChartPanel ventanaGrafico = new ChartPanel(grafico);
        ventanaGrafico.setPreferredSize(new Dimension(400, 300));

        JFrame frame = new JFrame("Gráfico de Barras");
        frame.add(ventanaGrafico);
        frame.pack();
        frame.setVisible(true);
    }

    public void generarGraficoPastel() {
        JFreeChart grafico = ChartFactory.createPieChart("Votos por Producto", graficoPastel, true, true, false);

        ChartPanel ventanaGrafico = new ChartPanel(grafico);
        ventanaGrafico.setPreferredSize(new Dimension(400, 300));

        JFrame frame = new JFrame("Gráfico de Pastel");
        frame.add(ventanaGrafico);
        frame.pack();
        frame.setVisible(true);
    }

    public void generarVistaContar() {
        
    }

    public void generarVistaListar(){
        
    }
}
