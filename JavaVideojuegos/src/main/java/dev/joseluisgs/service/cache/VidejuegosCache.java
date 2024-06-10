package dev.joseluisgs.service.cache;

import dev.joseluisgs.model.Videojuego;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class VidejuegosCache implements Cache<Long, Videojuego> {
    private final int maxSize;
    private final Map<Long, Videojuego> cache;
    private final Logger logger = LoggerFactory.getLogger(VidejuegosCache.class);

    public VidejuegosCache(int maxSize) {
        this.maxSize = maxSize;
        this.cache = new HashMap<>(maxSize);
    }

    @Override
    public void put(Long key, Videojuego value) {
        logger.debug("Añadiendo videojuego a la cache: " + value.getNombre());
        // Si el tamaño de la cache es mayor que el tamaño máximo y no contiene la clave a añadir
        if (cache.size() > maxSize && !cache.containsKey(key)) {
            // Eliminamos el primer elemento
            Long firstKey = cache.keySet().iterator().next();
            cache.remove(firstKey);
            logger.debug("Eliminando videojuego de la cache: " + firstKey);
        }
        cache.put(key, value);
    }

    @Override
    public Videojuego get(Long key) {
        logger.debug("Obteniendo videojuego de la cache: " + key);
        return cache.get(key);
    }

    @Override
    public void remove(Long key) {
        logger.debug("Eliminando videojuego de la cache: " + key);
        cache.remove(key);
    }

    @Override
    public void clear() {
        logger.debug("Limpiando la cache");
        cache.clear();
    }
}
