package data;

import dominio.Dominio;
import java.util.HashMap;
import java.util.Map;

public class Data implements Dominio.RepositorioPersona {

    // Simulación de almacenamiento en memoria (ID -> Nombre)
    private final Map<String, String> registros = new HashMap<>();

    @Override
    public void guardar(String id, String nombre) {
        if (registros.containsKey(id)) {
            throw new IllegalArgumentException("El ID ya se encuentra registrado.");
        }
        registros.put(id, nombre);
    }

    @Override
    public void actualizar(String id, String nombre) {
        if (!registros.containsKey(id)) {
            throw new IllegalArgumentException("El ID no existe para actualizar.");
        }
        registros.put(id, nombre);
    }

    @Override
    public void eliminar(String id) {
        if (!registros.containsKey(id)) {
            throw new IllegalArgumentException("El ID no existe para eliminar.");
        }
        registros.remove(id);
    }
}