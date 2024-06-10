package dev.joseluisgs.repository;

import dev.joseluisgs.exception.VideojuegoException;
import dev.joseluisgs.model.Videojuego;

import java.sql.SQLException;
import java.util.List;

// Cogemos la interfaz Crud la contextualizamos a nuestro tipo y añadimos metodos sin falta
// Por ejeplo un FibByNombre
public interface VideojuegosRepository extends CrudRepository<Videojuego, Long, VideojuegoException> {
    // Buscar por nombre
    List<Videojuego> findByNombre(String nombre) throws SQLException;
}
