package dev.joseluisgs.dto;

public class VideojuegoDto {
    private String id;
    private String titulo;
    private String plataforma;
    private String precio;
    private String fechaLanzamiento;

    public VideojuegoDto() {
    }

    public VideojuegoDto(String id, String titulo, String plataforma, String precio, String fechaLanzamiento) {
        this.id = id;
        this.titulo = titulo;
        this.plataforma = plataforma;
        this.precio = precio;
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(String fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    @Override
    public String toString() {
        return "VideojuegoDto{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", plataforma='" + plataforma + '\'' +
                ", precio='" + precio + '\'' +
                ", fechaLanzamiento='" + fechaLanzamiento + '\'' +
                '}';
    }

}
