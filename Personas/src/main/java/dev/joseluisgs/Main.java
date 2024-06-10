package dev.joseluisgs;

import dev.joseluisgs.models.Persona;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Para leer de consola conn Scanner
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hola Personas");

        System.out.println("Introduce el nombre de la persona: ");
        String nombre = scanner.nextLine();
        System.out.println("Introduce la edad de la persona: ");
        int edad = -1;
        do {
            try {
                edad = Integer.parseInt(scanner.nextLine());
                if (edad < 0) {
                    System.out.println("Introduce un número válido, no puede ser negativo");
                }
            } catch (Exception e) {
                System.out.println("Introduce un número válido");
            }
        } while (edad < 0);

        Persona persona = new Persona(nombre, edad);
        System.out.println("Persona creada: " + persona);

        // Operador ternario
        System.out.println("La persona es mayor de edad: " + (persona.getEdad() >= 18 ? "Sí" : "No"));

    }
}