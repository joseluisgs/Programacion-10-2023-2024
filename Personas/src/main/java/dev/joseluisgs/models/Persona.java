package dev.joseluisgs.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Persona {
    private static final long idCounter = 0;
    private final long id;
    private final LocalDateTime createAt;
    private final LocalDateTime updateAt;
    private int edad;
    private String nombre;
    private boolean isDeleted;

    // Constructor
    public Persona(String nombre, int edad) {
        this.id = idCounter + 1;
        this.nombre = nombre;
        // this.edad = edad; // cuidado con la edad
        setEdad(edad);
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
        this.isDeleted = false;
    }

    public String isMayorEdad() {
        /*if (edad >= 18) {
            return "Sí";
        } else {
            return "No";
        }*/

        return edad >= 18 ? "Sí" : "No";
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if (edad < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa");
        }
        this.edad = edad;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona persona)) return false;
        return id == persona.id && edad == persona.edad && Objects.equals(nombre, persona.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, edad, nombre);
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre=" + nombre +
                ", edad=" + edad +
                ", isMayorEdad=" + isMayorEdad() +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
