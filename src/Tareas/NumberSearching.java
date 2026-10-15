package Tareas;

import java.util.Random;

public class NumberSearching {

    public static void main(String[] args) {

        Random random = new Random();

        // Numero aleatorio a buscar en el array
        int num = random.nextInt(101);

        // Crear un array con numeros aleatorios
        int[] array = new int[15];

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(101);
        }

        // Crear un hilo
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {

                System.out.println("El numero a buscar es: " + num);

                // Mostrar el contenido del array
                for (int i = 0; i < array.length; i++) {
                    System.out.print(array[i] + " ");
                }

                System.out.println();

                // Buscar el numero en el array
                boolean encontrado = false;

                for (int i = 0; i < array.length; i++) {

                    if (array[i] == num) {
                        System.out.println("Se ha encontrado el numero en la posicion " + (i + 1) + " del array");
                        encontrado = true;
                    }
                }

                if (!encontrado) {
                    System.out.println("No se ha encontrado el numero en el array");
                }
            }
        });

        thread.start();
    }
}