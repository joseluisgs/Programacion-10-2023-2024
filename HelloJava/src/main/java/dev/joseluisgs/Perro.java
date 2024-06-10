package dev.joseluisgs;

public final class Perro extends Animal {
    private final String raza;

    public Perro(String nombre, int edad, String raza) {
        super(nombre, edad);
        this.raza = raza;
    }

    public String getRaza() {
        return raza;
    }

    @Override
    public String toString() {
        return "Perro{" +
                "nombre='" + getNombre() + '\'' +
                ", edad=" + getEdad() +
                ", raza='" + raza + '\'' +
                '}';
    }
}
