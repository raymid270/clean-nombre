import dominio.Dominio;
import presentacion.Presentacion;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Implementación temporal o real del repositorio
        Dominio.RepositorioPersona repo = new Dominio.RepositorioPersona() {
            @Override public void guardar(String id, String nombre) { System.out.println("Guardando: " + id + ", " + nombre); }
            @Override public void actualizar(String id, String nombre) { System.out.println("Actualizando: " + id + ", " + nombre); }
            @Override public void eliminar(String id) { System.out.println("Eliminando ID: " + id); }
        };

        Dominio.GuardarNombre guardarCaso = new Dominio.GuardarNombre(repo);
        Dominio.ActualizarNombre actualizarCaso = new Dominio.ActualizarNombre(repo);
        Dominio.EliminarNombre eliminarCaso = new Dominio.EliminarNombre(repo);

        SwingUtilities.invokeLater(() -> {
            new Presentacion(guardarCaso, actualizarCaso, eliminarCaso).setVisible(true);
        });
    }
}