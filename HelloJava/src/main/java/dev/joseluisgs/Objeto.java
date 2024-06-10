package dev.joseluisgs;

public class Objeto {
    private static int contador = 0;
    private final int z;
    private int x;
    private int y;

    // constructor
    public Objeto(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        contador++;
    }

    public static int getContador() {
        return contador;
    }


    // Getter y Setter
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if (y > 0) {
            this.y = y;
        } else {
            throw new IllegalArgumentException("Y debe ser mayor que 0");
        }
    }

    public int getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "Objeto [x=" + x + ", y=" + y + ", z=" + z + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Objeto other = (Objeto) obj;
        return x == other.x && y == other.y && z == other.z;
    }
}
