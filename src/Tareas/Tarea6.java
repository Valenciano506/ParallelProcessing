package Tareas;

import java.util.Random;

public class Tarea6 {

    // Variable compartida: indica si algún hilo ya encontró el número.
    // volatile garantiza que ambos hilos vean siempre el valor actualizado.
    static volatile boolean encontrado = false;

    public static void main(String[] args) {

        Random random = new Random();

        // Numero aleatorio a buscar en el array
        int num = random.nextInt(101);

        // Crear un array con numeros aleatorios
        int[] array = new int[15];

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(101);
        }

        System.out.println("El numero a buscar es: " + num);

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();

        // Limites de cada mitad del array
        int mitad = array.length / 2;

        // Hilo 1: recorre desde 0 hasta la mitad (sin incluirla)
        Thread hilo1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < mitad && !encontrado; i++) {
                    if (array[i] == num) {
                        encontrado = true;
                        System.out.println("Hilo 1: se ha encontrado el numero en la posicion " + (i + 1) + " del array");
                    }
                }
            }
        });

        // Hilo 2: recorre desde la mitad hasta el final
        Thread hilo2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = mitad; i < array.length && !encontrado; i++) {
                    if (array[i] == num) {
                        encontrado = true;
                        System.out.println("Hilo 2: se ha encontrado el numero en la posicion " + (i + 1) + " del array");
                    }
                }
            }
        });

        hilo1.start();
        hilo2.start();

        // Esperar a que los dos hilos terminen antes de dar el resultado final
        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!encontrado) {
            System.out.println("No se ha encontrado el numero en el array");
        }
    }
}