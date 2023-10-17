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
    private ArrayList<Producto> productos = new ArrayList<>();

    private static int contadorProducto1 = 0;
    private static int contadorProducto2 = 0;
    private static int contadorProducto3 = 0;

    public Vista(Controlador controlador) {
        this.controlador = controlador;
        // Inicializa la interfaz de usuario aquí
    }

    public void iniciar() {
        generarGraficoBarras();
        generarGraficoPastel();
        try {
            List<String> nombresProductos = Files.readAllLines(Paths.get("src/main/java/com/example/productos.txt"));
            for (String nombre : nombresProductos) {
                Producto producto = new Producto(nombre);
                this.productos.add(producto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Configura y muestra la interfaz de usuario
    
            // Crear el marco (ventana) principal
            JFrame marco = new JFrame("Votaciones");
            marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            marco.setSize(700, 400);
            marco.setLayout(new GridLayout(3, 1));

            Producto producto1 = this.productos.get(0);
            Producto producto2 = this.productos.get(1);
            Producto producto3 = this.productos.get(2);
    
            // Etiquetas para los productos
            JLabel etiquetaProducto1 = new JLabel(producto1.getNombre());
            JLabel etiquetaProducto2 = new JLabel(producto2.getNombre());
            JLabel etiquetaProducto3 = new JLabel(producto3.getNombre());
    
            // Botones para cada producto
            JButton botonProducto1 = new JButton("Incrementar " + producto1.getNombre());
            JButton botonProducto2 = new JButton("Incrementar " + producto2.getNombre());
            JButton botonProducto3 = new JButton("Incrementar " + producto3.getNombre());
    
            // Contadores para cada producto
            JLabel contadorProducto1Label = new JLabel("Contador: " + contadorProducto1);
            JLabel contadorProducto2Label = new JLabel("Contador: " + contadorProducto2);
            JLabel contadorProducto3Label = new JLabel("Contador: " + contadorProducto3);
    
            // Agregar ActionListener para los botones
            botonProducto1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    producto1.votar();
                    contadorProducto1Label.setText("Contador: " + producto1.getVotos());
                }
            });
    
            botonProducto2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    producto2.votar();
                    contadorProducto2Label.setText("Contador: " + producto2.getVotos());
                }
            });
    
            botonProducto3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    producto3.votar();
                    contadorProducto3Label.setText("Contador: " + producto3.getVotos());
                }
            });
    
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

    public void actualizarContadores() {
        // Actualiza los contadores de votos en la interfaz
    }

    public void generarGraficoBarras() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Producto producto : this.productos) {
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

    public void generarGraficoPastel() {
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
