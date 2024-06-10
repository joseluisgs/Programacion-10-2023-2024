package dev.joseluisgs;

public class Singleton {
    private static Singleton instance = null;

    int x = 10; // var x: Int no puede ser null
    private final Integer y = 20; // var y: Int? puede ser null

    private final String z = "hola"; // var z: String? puede ser null


    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
