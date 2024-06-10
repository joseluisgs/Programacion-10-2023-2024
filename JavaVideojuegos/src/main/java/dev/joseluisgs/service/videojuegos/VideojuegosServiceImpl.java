package dev.joseluisgs.service.videojuegos;

import dev.joseluisgs.exception.VideojuegoDataBaseException;
import dev.joseluisgs.exception.VideojuegoException;
import dev.joseluisgs.model.Videojuego;
import dev.joseluisgs.repository.VideojuegosRepository;
import dev.joseluisgs.service.cache.VidejuegosCache;
import dev.joseluisgs.service.storage.VideojuegosCsvStorage;
import dev.joseluisgs.service.storage.VideojuegosJsonStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class VideojuegosServiceImpl implements VideojuegosService {
    private final Logger logger = LoggerFactory.getLogger(VidejuegosCache.class);
    private final VidejuegosCache cache;
    private final VideojuegosRepository repository;
    private final VideojuegosCsvStorage videojuegosCsvStorage;
    private final VideojuegosJsonStorage videojuegosJsonStorage;

    public VideojuegosServiceImpl(VidejuegosCache cache, VideojuegosRepository repository, VideojuegosCsvStorage videojuegosCsvStorage, VideojuegosJsonStorage videojuegosJsonStorage) {
        this.cache = cache;
        this.repository = repository;
        this.videojuegosCsvStorage = videojuegosCsvStorage;
        this.videojuegosJsonStorage = videojuegosJsonStorage;
    }

    @Override
    public List<Videojuego> findAll() throws SQLException {
        logger.debug("Obteniendo todos los videojuegos");
        return repository.findAll();
    }

    @Override
    public List<Videojuego> findAllByNombre(String nombre) throws SQLException {
        logger.debug("Obteniendo todos los videojuegos por nombre que contenga: " + nombre);
        return repository.findByNombre(nombre);
    }

    @Override
    public Videojuego findById(long id) throws SQLException, VideojuegoDataBaseException {
        logger.debug("Obteniendo videojuego por ID: " + id);
        Optional<Videojuego> videojuego = Optional.ofNullable(cache.get(id));
        if (videojuego.isEmpty()) {
            videojuego = repository.findById(id);
            videojuego.ifPresent(value -> cache.put(id, value));
        }
        return videojuego.orElseThrow(() -> new VideojuegoDataBaseException("No se ha encontrado el videojuego con ID: " + id));
    }

    @Override
    public Videojuego save(Videojuego videojuego) throws SQLException, VideojuegoException {
        logger.debug("Guardando videojuego: " + videojuego.getNombre());
        Videojuego saved = repository.save(videojuego);
        cache.put(saved.getId(), videojuego);
        return saved;
    }

    @Override
    public Videojuego update(Videojuego videojuego) throws SQLException, VideojuegoException {
        logger.debug("Actualizando videojuego: " + videojuego.getNombre());
        Videojuego updated = this.findById(videojuego.getId());
        updated = repository.update(videojuego);
        cache.put(updated.getId(), updated);
        return updated;
    }

    @Override
    public Videojuego deleteById(long id) throws SQLException, VideojuegoException {
        logger.debug("Borrando videojuego por ID: " + id);
        Videojuego videojuego = this.findById(id);
        repository.deleteById(id);
        cache.remove(id);
        return videojuego;
    }

    @Override
    public void deleteAll() throws SQLException {
        logger.debug("Borrando todos los videojuegos");
        repository.deleteAll();
        cache.clear();
    }

    @Override
    public List<Videojuego> loadFromFile(File file) throws SQLException, VideojuegoException {
        return videojuegosCsvStorage.readFromFile(file);
    }

    @Override
    public void saveToFile(File file, Map<String, List<Videojuego>> mapa) throws SQLException, VideojuegoException {
        videojuegosJsonStorage.saveToFile(mapa, file);
    }

}
