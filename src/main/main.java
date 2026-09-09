package main;

import hilos.entrada;
import hilos.salida;
import modelos.aparcamiento;
import modelos.empresa;

public class main {
    public static void main(String[] args) {
        empresa miEmpresa = new empresa("ParkingsSA");

        aparcamiento p1 = new aparcamiento("Madrid", "Parking Sol", 20);
        aparcamiento p2 = new aparcamiento("Barcelona", "Parking Gótico", 15);

        miEmpresa.registrarAparcamiento(p1);
        miEmpresa.registrarAparcamiento(p2);

        // Hilos de entrada y salida trabajando de forma concurrente
        // sobre el mismo objeto aparcamiento
        Thread hiloEntradaP1 = new Thread(new entrada(p1, 30));
        Thread hiloSalidaP1  = new Thread(new salida(p1, 25));
        Thread hiloEntradaP2 = new Thread(new entrada(p2, 20));
        Thread hiloSalidaP2  = new Thread(new salida(p2, 15));

        hiloEntradaP1.start();
        hiloSalidaP1.start();
        hiloEntradaP2.start();
        hiloSalidaP2.start();

        try {
            hiloEntradaP1.join();
            hiloSalidaP1.join();
            hiloEntradaP2.join();
            hiloSalidaP2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println();
        miEmpresa.consultaGeneral();

        System.out.println();
        System.out.println("Búsqueda por ciudad 'Madrid':");
        for (aparcamiento a : miEmpresa.buscarPorCiudad("Madrid")) {
            System.out.println(" - " + a);
        }
    }
}