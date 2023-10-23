package com.example;

import javax.swing.*;
import java.awt.event.ActionListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.util.ArrayList;

public class Vista {
    private JButton botonProducto1;
    private JButton botonProducto2;
    private JButton botonProducto3;
    
    private DefaultCategoryDataset graficoBarras;
    private DefaultPieDataset<String> graficoPastel;

    public Vista(ArrayList<Producto> productos) {
        this.iniciar(productos);
    }

    public void iniciar(ArrayList<Producto> productos) {

        graficoBarras = new DefaultCategoryDataset();
        graficoPastel = new DefaultPieDataset<>();
        for (Producto producto : productos) {
            graficoBarras.addValue(producto.getVotos(), "Votos", producto.getNombre());
            graficoPastel.setValue(producto.getNombre(), producto.getVotos());
        }

        generarGraficoBarras();
        generarGraficoPastel();

        JFrame marco = new JFrame("Votaciones");
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setSize(700, 400);
        marco.setLayout(new GridLayout(3, 1));

        Producto producto1 = productos.get(0);
        Producto producto2 = productos.get(1);
        Producto producto3 = productos.get(2);

        JLabel etiquetaProducto1 = new JLabel(producto1.getNombre());
        JLabel etiquetaProducto2 = new JLabel(producto2.getNombre());
        JLabel etiquetaProducto3 = new JLabel(producto3.getNombre());

        botonProducto1 = new JButton("Votar por " + producto1.getNombre());
        botonProducto2 = new JButton("Votar por " + producto2.getNombre());
        botonProducto3 = new JButton("Votar por " + producto3.getNombre());

        marco.add(etiquetaProducto1);
        marco.add(botonProducto1);

        marco.add(etiquetaProducto2);
        marco.add(botonProducto2);

        marco.add(etiquetaProducto3);
        marco.add(botonProducto3);

        marco.setVisible(true);
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

    public void actualizarDatosGraficos(ArrayList<Producto> productos) {
        for (Producto producto : productos) {
            graficoBarras.setValue(producto.getVotos(), "Votos", producto.getNombre());
            graficoPastel.setValue(producto.getNombre(), producto.getVotos());
        }
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
}
