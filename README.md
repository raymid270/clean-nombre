# Clean - Nombre

Ejemplo mínimo de **Clean Architecture** en Java con Swing.
Una pantalla para escribir un nombre y guardarlo.

## Capas (un archivo por capa)

```
Presentacion  ──►  Dominio  ◄──  Data
```

| Capa         | Archivo                          | Responsabilidad                          |
|--------------|----------------------------------|------------------------------------------|
| Presentacion | `src/presentacion/Presentacion.java` | Dibuja la ventana y llama al caso de uso |
| Dominio      | `src/dominio/Dominio.java`        | Reglas del negocio. No depende de nadie  |
| Data         | `src/data/Data.java`             | Guarda los datos (lista en memoria)      |
| Arranque     | `src/Main.java`                  | Une las 3 capas                          |

## Requisitos

- Java 17 o superior (`java -version`)

## Compilar

Desde la carpeta `clean-nombre`:

```
javac -d out src/dominio/Dominio.java src/data/Data.java src/presentacion/Presentacion.java src/Main.java
```

Los `.class` se generan en `out/` (carpeta desechable, se puede borrar).

## Ejecutar

```
java -cp out Main
```

Escribe un nombre y pulsa **Guardar**.
