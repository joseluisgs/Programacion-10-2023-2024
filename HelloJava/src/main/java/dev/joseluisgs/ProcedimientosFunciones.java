package dev.joseluisgs;

public class ProcedimientosFunciones {
    // Procdimiento es void
    public void procedimiento() {
        System.out.println("Esto es un procedimiento");
    }

    // Con parámetros
    public void procedimientoConParametros(int x) {
        System.out.println("Esto es un procedimiento con parámetros: " + x);
    }

    // Función
    public int funcion() {
        return 10;
    }

    // Función con parámetros
    public int funcionConParametros(int x) {
        return x;
    }
}
