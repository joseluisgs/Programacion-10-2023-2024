package dev.joseluisgs.service.storage;

import dev.joseluisgs.dto.VideojuegoDto;
import dev.joseluisgs.exception.VideojuegoStorageException;
import dev.joseluisgs.mapper.VideojuegoMapper;
import dev.joseluisgs.model.Videojuego;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;


public class VideojuegosCsvStorage implements CsvStorage<Videojuego, VideojuegoStorageException> {
    private final Logger logger = LoggerFactory.getLogger(VideojuegosCsvStorage.class);

    // leemos un archivo CSV y lo trasformamos en una lista de videojuegos
    public List<Videojuego> readFromFile(File file) throws VideojuegoStorageException {
        if (!file.exists() || !file.isFile() || !file.canRead()) {
            logger.error("El archivo CSV no existe o no tienes permisos para leerlo: " + file.getAbsolutePath());
            throw new VideojuegoStorageException("El archivo CSV no existe o no tienes permiso para leerlos: " + file.getAbsolutePath());
        }
        // Leemos csv como Stream mapeando a Videojuego
        try {
            return Files.lines(file.toPath())
                    .skip(1) // Saltamos la cabecera
                    .map(line -> line.split(",")) // Separamos por comas
                    .map(data -> new VideojuegoDto(data[0], data[1], data[2], data[3], data[4])) // Mapeamos a Videojuego
                    .map(VideojuegoMapper::toVidejuego) // Mapeamos a Videojuego
                    .toList(); // Convertimos a lista
        } catch (IOException e) {
            logger.error("Error al leer el archivo CSV: " + file.getAbsolutePath() + " " + e.getMessage());
            throw new VideojuegoStorageException("Error al leer el archivo CSV: " + file.getAbsolutePath() + " " + e.getMessage());
        }
    }
}
