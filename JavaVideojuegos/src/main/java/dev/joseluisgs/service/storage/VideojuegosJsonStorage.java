package dev.joseluisgs.service.storage;

import com.squareup.moshi.Moshi;
import dev.joseluisgs.dto.VideojuegoDto;
import dev.joseluisgs.exception.VideojuegoStorageException;
import dev.joseluisgs.mapper.VideojuegoMapper;
import dev.joseluisgs.model.Videojuego;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VideojuegosJsonStorage implements JsonStorage<String, Videojuego, VideojuegoStorageException> {
    private final Logger logger = LoggerFactory.getLogger(VideojuegosJsonStorage.class);

    @Override
    public void saveToFile(Map<String, List<Videojuego>> videojuegoMapa, File file) throws VideojuegoStorageException {
        logger.debug("Guardando videojuegos en fichero JSON: " + file.getName());
        // Configuro Moshi con pretty print
        Moshi moshi = new Moshi.Builder().build();
        // Trasformamos el mapa  de Map<String, List<Videojuego>> a Map<String, List<VideojuegoDto>>
        Map<String, List<VideojuegoDto>> mapa = videojuegoMapa.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .map(VideojuegoMapper::toDto)
                                .collect(Collectors.toList())
                ));

        // Obtenemos el adapter
        var adapter = moshi.adapter(Map.class).indent("  "); // Pretty print con el indent // es un Map<String, List<VideojuegoDto>> pero se pone Map.class
        String json = adapter.toJson(mapa);
        // Guardamos en fichero
        try {
            Files.writeString(file.toPath(), json);
        } catch (Exception e) {
            logger.error("Error al guardar el archivo JSON: " + file.getAbsolutePath() + " " + e.getMessage());
            throw new VideojuegoStorageException("Error al guardar el archivo JSON: " + file.getAbsolutePath() + " " + e.getMessage());
        }

    }
}
