package dev.joseluisgs;

import java.util.Scanner;

public class LeerEscribirConsola {
    public void leerEscribirConsola() {
        // Leer de consola
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce un número: ");
        int numero = scanner.nextInt();
        System.out.println("El número introducido es: " + numero);

        // ahora un double
        System.out.println("Introduce un número decimal: ");
        double decimal = scanner.nextDouble();
        System.out.println("El número decimal introducido es: " + decimal);

        // ahora un string
        System.out.println("Introduce un texto: ");
        String texto = scanner.next();
        System.out.println("El texto introducido es: " + texto);

        scanner.close();

        // Escribir en consola
        System.out.println("Hola mundo!");
    }
}
