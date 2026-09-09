package modelos;

import java.util.ArrayList;
import java.util.List;

public class empresa {
    private String nombre;
    private List<aparcamiento> aparcamientos;

    public empresa(String nombre) {
        this.nombre = nombre;
        this.aparcamientos = new ArrayList<>();
    }

    public void registrarAparcamiento(aparcamiento a) {
        aparcamientos.add(a);
    }

    public List<aparcamiento> buscarPorCiudad(String ciudad) {
        List<aparcamiento> resultado = new ArrayList<>();
        for (aparcamiento a : aparcamientos) {
            if (a.getCiudad().equalsIgnoreCase(ciudad)) {
                resultado.add(a);
            }
        }
        return resultado;
    }

    public void consultaGeneral() {
        System.out.println("=== Estado general de " + nombre + " ===");
        for (aparcamiento a : aparcamientos) {
            System.out.println(a);
        }
    }

    public String getNombre() { return nombre; }
    public List<aparcamiento> getAparcamientos() { return aparcamientos; }
}