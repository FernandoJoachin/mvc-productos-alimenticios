package com.example.servidor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Bitacora {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public static String registrar(String producto) {
        String fechaHoraActual = LocalDateTime.now().format(FORMATTER);
        String registro = String.format("Se votó por el producto %s en el método votar %s", producto, fechaHoraActual);
        String resultado = registro;

        return resultado;
    }
}