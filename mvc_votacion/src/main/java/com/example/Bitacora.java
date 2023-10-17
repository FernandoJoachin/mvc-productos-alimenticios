package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

public class Bitacora {
    public static void registrarAccion(String clase, String mensaje) {
        try {
            LocalDateTime ahora = LocalDateTime.now();
            String registro = ahora + " - " + clase + " - " + mensaje + "\n";
            Files.write(Paths.get("bitacora.txt"), registro.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
