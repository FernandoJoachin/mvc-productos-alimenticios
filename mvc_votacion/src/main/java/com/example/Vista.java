package com.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Vista {
    private Controlador controlador;
    // private JFrame frame;
    // private JLabel[] labels;
    // private JButton[] botones;
    // Botones para cada producto
    private JButton botonProducto1;
    private JButton botonProducto2;
    private JButton botonProducto3;

    private static int contadorProducto1 = 0;
    private static int contadorProducto2 = 0;
    private static int contadorProducto3 = 0;

    protected JLabel contadorProducto1Label;
    protected JLabel contadorProducto2Label ;
    protected JLabel contadorProducto3Label;

    public Vista(ArrayList<Producto> productos) {
        this.iniciar(productos);
    }

    public void iniciar(ArrayList<Producto> productos) {
        generarGraficoBarras(productos);
        generarGraficoPastel(productos);

        // Configura y muestra la interfaz de usuario
    
            // Crear el marco (ventana) principal
            JFrame marco = new JFrame("Votaciones");
            marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            marco.setSize(700, 400);
            marco.setLayout(new GridLayout(3, 1));

            Producto producto1 = productos.get(0);
            Producto producto2 = productos.get(1);
            Producto producto3 = productos.get(2);
    
            // Etiquetas para los productos
            JLabel etiquetaProducto1 = new JLabel(producto1.getNombre());
            JLabel etiquetaProducto2 = new JLabel(producto2.getNombre());
            JLabel etiquetaProducto3 = new JLabel(producto3.getNombre());
    
            // Contadores para cada producto
            this.contadorProducto1Label = new JLabel("Contador: " + contadorProducto1);
            this.contadorProducto2Label = new JLabel("Contador: " + contadorProducto2);
            this.contadorProducto3Label = new JLabel("Contador: " + contadorProducto3);

            botonProducto1 = new JButton("Incrementar " + producto1.getNombre());
            botonProducto2 = new JButton("Incrementar " + producto2.getNombre());
            botonProducto3 = new JButton("Incrementar " + producto3.getNombre());
    
            // Agregar componentes al marco
            marco.add(etiquetaProducto1);
            marco.add(botonProducto1);
            marco.add(contadorProducto1Label);
    
            marco.add(etiquetaProducto2);
            marco.add(botonProducto2);
            marco.add(contadorProducto2Label);
    
            marco.add(etiquetaProducto3);
            marco.add(botonProducto3);
            marco.add(contadorProducto3Label);
    
            // Hacer visible el marco
            marco.setVisible(true);
    }

    void addVotoListenerBtn1(ActionListener voto){
        botonProducto1.addActionListener(voto);
    }

    void addVotoListenerBtn2(ActionListener voto){
        botonProducto2.addActionListener(voto);
    }

    void addVotoListenerBtn3(ActionListener voto){
        botonProducto3.addActionListener(voto);
    }

    public void actualizarContadores() {
        // Actualiza los contadores de votos en la interfaz
    }

    public void generarGraficoBarras(ArrayList<Producto> productos) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Producto producto : productos) {
            dataset.addValue(producto.getVotos(), "Votos", producto.getNombre());
        }
    
        JFreeChart chart = ChartFactory.createBarChart(
            "Votos por Producto", "Productos", "Votos", dataset, PlotOrientation.VERTICAL, true, true, false
        );
    
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(400, 300));
    
        JFrame frame = new JFrame("Gráfico de Barras");
        frame.add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public void generarGraficoPastel(ArrayList<Producto> productos) {
       DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        for (Producto producto : productos) {
            dataset.setValue(producto.getNombre(), producto.getVotos());
        }
    
        JFreeChart chart = ChartFactory.createPieChart(
            "Votos por Producto", dataset, true, true, false
        );
    
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(400, 300));
    
        JFrame frame = new JFrame("Gráfico de Pastel");
        frame.add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    } 
    
}
