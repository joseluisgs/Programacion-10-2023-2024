package dev.joseluisgs;

import dev.joseluisgs.model.Videojuego;
import dev.joseluisgs.repository.VideojuegosRepositoryImpl;
import dev.joseluisgs.service.cache.VidejuegosCache;
import dev.joseluisgs.service.database.DatabaseManager;
import dev.joseluisgs.service.storage.VideojuegosCsvStorage;
import dev.joseluisgs.service.storage.VideojuegosJsonStorage;
import dev.joseluisgs.service.videojuegos.VideojuegosService;
import dev.joseluisgs.service.videojuegos.VideojuegosServiceImpl;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hola Videojuegos!");

        VideojuegosService service = new VideojuegosServiceImpl(
                new VidejuegosCache(7),
                new VideojuegosRepositoryImpl(DatabaseManager.getInstance()),
                new VideojuegosCsvStorage(),
                new VideojuegosJsonStorage()
        );

        try {
            var list = service.loadFromFile(new File("videojuegos.csv"));
            list.forEach(juego -> {
                try {
                    service.save(juego);
                } catch (Exception e) {
                    System.err.println("Error al guardar el videojuego: " + juego.getNombre());
                }
            });
        } catch (Exception e) {
            System.err.println("Error al cargar los videojuegos: " + e.getMessage());
        }

        try {
            service.findAll().forEach(System.out::println);
        } catch (Exception e) {
            System.err.println("Error al obtener los videojuegos");
        }

        // Obtenemos el juego con id 10
        try {
            var juego = service.findById(10L);
            System.out.println(juego);
            // Le cambiamos el nombre del juego con id 10 a TEST UPDATE
            juego.setNombre("TEST UPDATE");
            juego.setPrecio(99.99);
            juego = service.update(juego);
            System.out.println(juego);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // Borramos el juego con id 20
        try {
            var juego = service.deleteById(20L);
            System.out.println("Juego borrado: " + juego);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // Obtenemos todos los juegos
        try {
            var list = service.findAll();
            // ahora vamos a a crear un mapa con al agrupación por plataforma y de valor la lista ordenada por precio descendentemente (de mayor a menor)
            Map<String, List<Videojuego>> map = list.stream()
                    .collect(Collectors.groupingBy(Videojuego::getPlataforma,
                            Collectors.collectingAndThen(Collectors.toList(),
                                    games -> games.stream()
                                            .sorted(Comparator.comparingDouble(Videojuego::getPrecio).reversed())
                                            .collect(Collectors.toList()))));

            // Imprimir el mapa
            map.forEach((plataforma, juegos) -> {
                System.out.println("Plataforma: " + plataforma);
                juegos.forEach(juego -> System.out.println("Juego: " + juego.getNombre() + ", Precio: " + juego.getPrecio()));
            });

            // Lo salvamos como Json
            service.saveToFile(new File("videojuegos.json"), map);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }


    }
}