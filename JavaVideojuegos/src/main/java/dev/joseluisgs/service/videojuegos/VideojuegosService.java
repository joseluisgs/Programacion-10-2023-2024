package dev.joseluisgs.service.videojuegos;

import dev.joseluisgs.exception.VideojuegoException;
import dev.joseluisgs.model.Videojuego;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface VideojuegosService {
    List<Videojuego> findAll() throws SQLException;

    List<Videojuego> findAllByNombre(String nombre) throws SQLException;

    Videojuego findById(long id) throws SQLException, VideojuegoException;

    Videojuego save(Videojuego alumno) throws SQLException, VideojuegoException;

    Videojuego update(Videojuego alumno) throws SQLException, VideojuegoException;

    Videojuego deleteById(long id) throws SQLException, VideojuegoException;

    void deleteAll() throws SQLException;

    List<Videojuego> loadFromFile(File file) throws SQLException, VideojuegoException;

    void saveToFile(File file, Map<String, List<Videojuego>> mapa) throws SQLException, VideojuegoException;
}