package Tareas;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Tarea7 {

    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();

        int[] array = new int[20];

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(101);
        }

        int num = random.nextInt(101);
        System.out.println("El numero a buscar es: " + num);

        // TODO 1: procesadores disponibles
        int numProcesadores = Runtime.getRuntime().availableProcessors();
        System.out.println("Ejecutando búsqueda paralela en " + numProcesadores + " núcleos...");

        // Nunca más hilos que elementos
        int numHilos = Math.min(numProcesadores, array.length);

        // TODO 2: tamaño del bloque
        int tamanoBloque = array.length / numHilos;

        AtomicBoolean encontrado = new AtomicBoolean(false);
        Thread[] hilos = new Thread[numHilos];

        long inicioTiempo = System.nanoTime();

        // TODO 3: crear y arrancar los hilos
        for (int i = 0; i < numHilos; i++) {
            int inicio = i * tamanoBloque;
            // El último hilo llega hasta el final del array (absorbe el resto)
            int fin = (i == numHilos - 1) ? array.length : inicio + tamanoBloque;
            int idHilo = i + 1;

            hilos[i] = new Thread(() -> buscarNumero(array, num, inicio, fin, encontrado, idHilo));
            hilos[i].start();
        }

        // TODO 4: esperar a todos
        for (Thread hilo : hilos) {
            hilo.join();
        }

        long tiempoMs = (System.nanoTime() - inicioTiempo) / 1_000_000;

        if (!encontrado.get()) {
            System.out.println("El número " + num + " no se ha encontrado en el array.");
        }
        System.out.println("Tiempo: " + tiempoMs + " ms con " + numHilos + " hilos");
    }

    public static void buscarNumero(int[] array, int num, int inicio, int fin,
                                    AtomicBoolean encontrado, int idHilo) {
        for (int i = inicio; i < fin && !encontrado.get(); i++) {
            if (array[i] == num) {
                // Solo el primer hilo que lo encuentre imprime
                if (encontrado.compareAndSet(false, true)) {
                    System.out.println("Hilo " + idHilo + ": se ha encontrado el numero " + num + " en la posicion " + (i + 1) + " del array.");
                }
                return;
            }
        }
    }
}