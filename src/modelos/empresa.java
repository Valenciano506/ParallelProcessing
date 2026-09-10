package modelos;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class empresa {
    private String nombre;
    private Map<String, aparcamiento> aparcamientos;

    public empresa(String nombre) {
        this.nombre = nombre;
        this.aparcamientos = new HashMap<>();
    }

    // Antes: aparcamientos.add(a) -> ahora se indexa por nombre (clave única)
    public void registrarAparcamiento(aparcamiento a) {
        aparcamientos.put(a.getNombre(), a);
    }

    // NUEVO: acceso directo O(1) a un aparcamiento por su nombre,
    // sin recorrer nada ni comparar uno a uno
    public aparcamiento buscarPorNombre(String nombre) {
        return aparcamientos.get(nombre);
    }

    // buscarPorCiudad sigue necesitando revisar varios aparcamientos
    // (la ciudad no es la clave del Map, puede haber varios por ciudad),
    // pero ahora recorremos los valores del Map en vez de una lista aparte
    public List<aparcamiento> buscarPorCiudad(String ciudad) {
        List<aparcamiento> resultado = new ArrayList<>();
        for (aparcamiento a : aparcamientos.values()) {
            if (a.getCiudad().equalsIgnoreCase(ciudad)) {
                resultado.add(a);
            }
        }
        return resultado;
    }

    public void consultaGeneral() {
        System.out.println("=== Estado general de " + nombre + " ===");
        for (aparcamiento a : aparcamientos.values()) {
            System.out.println(a);
        }
    }

    public String getNombre() { return nombre; }

    // Antes devolvía List<aparcamiento>, ahora devolvemos los valores del Map
    public Collection<aparcamiento> getAparcamientos() {
        return aparcamientos.values();
    }
}