package hilos;

import modelos.aparcamiento;

public class entrada implements Runnable {
    private aparcamiento parking;
    private int numCoches;

    public entrada(aparcamiento parking, int numCoches) {
        this.parking = parking;
        this.numCoches = numCoches;
    }

    @Override
    public void run() {
        for (int i = 0; i < numCoches; i++) {
            parking.entrarCoche();
            try {
                Thread.sleep((long) (Math.random() * 300));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}