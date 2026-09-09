package modelos;

public class aparcamiento {
    private String ciudad;
    private String nombre;
    private int aforoMaximo;
    private int cochesAparcados;

    public aparcamiento(String ciudad, String nombre, int aforoMaximo) {
        this.ciudad = ciudad;
        this.nombre = nombre;
        this.aforoMaximo = aforoMaximo;
        this.cochesAparcados = 0;
    }

    // Sección crítica: synchronized evita que dos hilos modifiquen
    // el contador a la vez (condición de carrera)
    public synchronized void entrarCoche() {
        if (cochesAparcados < aforoMaximo) {
            cochesAparcados++;
            System.out.println("[ENTRADA] " + nombre + " (" + ciudad + ") -> "
                    + cochesAparcados + "/" + aforoMaximo);
        } else {
            System.out.println("[ENTRADA] " + nombre + " LLENO. Coche rechazado.");
        }
    }

    public synchronized void salirCoche() {
        if (cochesAparcados > 0) {
            cochesAparcados--;
            System.out.println("[SALIDA]  " + nombre + " (" + ciudad + ") -> "
                    + cochesAparcados + "/" + aforoMaximo);
        } else {
            System.out.println("[SALIDA]  " + nombre + " VACÍO. No hay coches que sacar.");
        }
    }

    public synchronized int getCochesAparcados() {
        return cochesAparcados;
    }

    public String getCiudad() { return ciudad; }
    public String getNombre() { return nombre; }
    public int getAforoMaximo() { return aforoMaximo; }

    @Override
    public String toString() {
        return nombre + " (" + ciudad + ") - " + cochesAparcados + "/" + aforoMaximo + " plazas";
    }
}