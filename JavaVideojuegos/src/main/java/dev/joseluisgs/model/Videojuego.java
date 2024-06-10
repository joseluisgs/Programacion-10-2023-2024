package dev.joseluisgs.model;

import dev.joseluisgs.locale.LocaleEs;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Videojuego {
    private long id = -1;
    private String nombre;
    private Plataforma plataforma;
    private double precio;
    private LocalDate fechaLanzamiento;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDeleted;

    public Videojuego(String nombre, Plataforma plataforma, double precio, LocalDate fechaLanzamiento) {
        this.nombre = nombre;
        this.plataforma = plataforma;
        this.precio = precio;
        this.fechaLanzamiento = fechaLanzamiento;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.isDeleted = false;
    }


    public Videojuego(long id, String nombre, Plataforma plataforma, double precio, LocalDate fechaLanzamiento) {
        this.id = id;
        this.nombre = nombre;
        this.plataforma = plataforma;
        this.precio = precio;
        this.fechaLanzamiento = fechaLanzamiento;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.isDeleted = false;
    }

    public Videojuego(long id, String nombre, Plataforma plataforma, double precio, LocalDate fechaLanzamiento, LocalDateTime createdAt, LocalDateTime updatedAt, boolean isDeleted) {
        this.id = id;
        this.nombre = nombre;
        this.plataforma = plataforma;
        this.precio = precio;
        this.fechaLanzamiento = fechaLanzamiento;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPlataforma() {
        return plataforma.name();
    }

    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }


    @Override
    public String toString() {
        return "Videojuego{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", plataforma='" + plataforma.name() + '\'' +
                ", precio=" + LocaleEs.getPrecioFormatted(precio) +
                ", fechaLanzamiento=" + LocaleEs.getFechaFormatted(fechaLanzamiento) +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", isDeleted=" + isDeleted +
                '}';
    }
}