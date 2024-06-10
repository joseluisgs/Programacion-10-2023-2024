package dev.joseluisgs;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        // If - else
        int x = 10;
        if (x == 10) {
            System.out.println("x is 10");
        } else {
            System.out.println("x is not 10");
        }

        // switch
        int day = 4;
        switch (day) {
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            case 4:
                System.out.println("Thursday");
                break;
            case 5:
                System.out.println("Friday");
                break;
            case 6:
                System.out.println("Saturday");
                break;
            case 7:
                System.out.println("Sunday");
                break;
            default:
                System.out.println("Invalid day");
                break;
        }

        // While
        int i = 0;
        while (i < 5) {
            System.out.println(i);
            i++;
        }

        // Do-While
        int j = 0;
        do {
            System.out.println(j);
            j++;
        } while (j < 5);

        // For
        for (int k = 0; k < 5; k++) {
            System.out.println(k);
        }

        // Foreach
        String[] names = {"Jose", "Luis", "Gonzalo"};
        for (String name : names) {
            System.out.println(name);
        }

        // Arrays
        int[] numbers = {1, 2, 3, 4, 5};
        for (int number : numbers) {
            System.out.println(number);
        }


        Objeto objeto = new Objeto(1, 2, 3);
        System.out.println(objeto);
        System.out.println(Objeto.getContador());

        Objeto objeto2 = new Objeto(1, 2, 3);
        System.out.println(objeto2);
        System.out.println(Objeto.getContador());

        Objeto objeto3 = new Objeto(1, 2, 3);
        System.out.println(objeto3);
        System.out.println(Objeto.getContador());

        if (objeto3 instanceof Objeto) {
            System.out.println("Es un objeto");
        }

        if (objeto2 == objeto3) {
            System.out.println("Son iguales");
        } else {
            System.out.println("No son iguales");
        }

        if (objeto2.equals(objeto3)) {
            System.out.println("Son iguales");
        } else {
            System.out.println("No son iguales");
        }

        Persona persona = new Persona("Jose", 30);
        System.out.println(persona);

        Persona persona2 = new Persona("Jose", 30);
        System.out.println(persona2);

        if (persona.equals(persona2)) {
            System.out.println("Son iguales");
        } else {
            System.out.println("No son iguales");
        }

        PersonaRecord personaRecord = new PersonaRecord("Jose", 30);
        System.out.println(personaRecord);

    }
}