package dominio;

public class Dominio {

    public interface RepositorioPersona {
        void guardar(String id, String nombre);
        void actualizar(String id, String nombre);
        void eliminar(String id);
    }

    public static class GuardarNombre {
        private final RepositorioPersona repositorio;

        public GuardarNombre(RepositorioPersona repositorio) {
            this.repositorio = repositorio;
        }

        public String ejecutar(String id, String nombre) {
            if (id == null || id.trim().isEmpty()) {
                throw new IllegalArgumentException("El ID no puede estar vacío.");
            }
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new IllegalArgumentException("El nombre no puede estar vacío.");
            }
            repositorio.guardar(id, nombre);
            return "Guardado exitosamente.";
        }
    }

    public static class ActualizarNombre {
        private final RepositorioPersona repositorio;

        public ActualizarNombre(RepositorioPersona repositorio) {
            this.repositorio = repositorio;
        }

        public String ejecutar(String id, String nombre) {
            if (id == null || id.trim().isEmpty()) {
                throw new IllegalArgumentException("Debe seleccionar un ID para actualizar.");
            }
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new IllegalArgumentException("El nombre no puede estar vacío.");
            }
            repositorio.actualizar(id, nombre);
            return "Actualizado exitosamente.";
        }
    }

    public static class EliminarNombre {
        private final RepositorioPersona repositorio;

        public EliminarNombre(RepositorioPersona repositorio) {
            this.repositorio = repositorio;
        }

        public String ejecutar(String id) {
            if (id == null || id.trim().isEmpty()) {
                throw new IllegalArgumentException("Debe seleccionar un ID para eliminar.");
            }
            repositorio.eliminar(id);
            return "Eliminado exitosamente.";
        }
    }
}