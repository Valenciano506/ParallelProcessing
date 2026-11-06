package Tareas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Tarea8 {

    public static void main(String[] args) throws Exception {

        Random random = new Random();

        int[] array = new int[20];

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(101);
        }

        int num = random.nextInt(101);
        System.out.println("El numero a buscar es: " + num);

        // Procesadores disponibles
        int numProcesadores = Runtime.getRuntime().availableProcessors();
        System.out.println("Ejecutando búsqueda paralela en " + numProcesadores + " núcleos...");

        // Nunca más hilos que elementos
        int numHilos = Math.min(numProcesadores, array.length);

        // Tamaño del bloque
        int tamanoBloque = array.length / numHilos;

        AtomicBoolean encontrado = new AtomicBoolean(false);

        // ExecutorService con un pool de hilos fijo
        ExecutorService executor = Executors.newFixedThreadPool(numHilos);
        List<Future<?>> tareas = new ArrayList<>();

        long inicioTiempo = System.nanoTime();

        // Enviar las tareas al ExecutorService
        for (int i = 0; i < numHilos; i++) {
            int inicio = i * tamanoBloque;
            // La última tarea llega hasta el final del array (absorbe el resto)
            int fin = (i == numHilos - 1) ? array.length : inicio + tamanoBloque;
            int idHilo = i + 1;

            // Tarea definida con función flecha (lambda)
            Runnable tarea = () -> buscarNumero(array, num, inicio, fin, encontrado, idHilo);

            tareas.add(executor.submit(tarea));
        }

        // Esperar a que terminen todas las tareas
        for (Future<?> tarea : tareas) {
            tarea.get();
        }

        // Cerrar el ExecutorService
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

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