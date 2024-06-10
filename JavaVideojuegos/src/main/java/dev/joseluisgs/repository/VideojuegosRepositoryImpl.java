package dev.joseluisgs.repository;

import dev.joseluisgs.exception.VideojuegoDataBaseException;
import dev.joseluisgs.model.Plataforma;
import dev.joseluisgs.model.Videojuego;
import dev.joseluisgs.service.database.DatabaseManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Esta es la clase que se encarga de la persistencia de los alumnos
public class VideojuegosRepositoryImpl implements VideojuegosRepository {
    private final Logger logger = LoggerFactory.getLogger(VideojuegosRepositoryImpl.class);
    // Base de datos
    private final DatabaseManager db;

    public VideojuegosRepositoryImpl(DatabaseManager db) {
        this.db = db;
    }


    @Override
    public List<Videojuego> findAll() throws SQLException {
        logger.debug("Obteniendo todos los videojuegos");
        var query = "SELECT * FROM VIDEOJUEGOS";
        try (var connection = db.getConnection();
             var stmt = connection.prepareStatement(query)
        ) {
            var rs = stmt.executeQuery();
            var lista = new ArrayList<Videojuego>();
            while (rs.next()) {
                Videojuego videojuego = new Videojuego(
                        rs.getLong("id"),
                        rs.getString("nombre"),
                        Plataforma.valueOf(rs.getString("plataforma")),
                        rs.getDouble("precio"),
                        rs.getObject("fecha_lanzamiento", LocalDate.class),
                        rs.getObject("created_at", LocalDateTime.class),
                        rs.getObject("updated_at", LocalDateTime.class),
                        rs.getBoolean("is_deleted")
                );
                lista.add(videojuego);
            }
            return lista;
        }
    }

    @Override
    public List<Videojuego> findByNombre(String nombre) throws SQLException {
        logger.debug("Obteniendo todos los videojuegos por nombre que contenga: " + nombre);
        // Vamos a usar Like para buscar por nombre
        String query = "SELECT * FROM VIDEOJUEGOS WHERE nombre LIKE ?";
        try (var connection = db.getConnection();
             var stmt = connection.prepareStatement(query)
        ) {
            stmt.setString(1, "%" + nombre + "%");
            var rs = stmt.executeQuery();
            var lista = new ArrayList<Videojuego>();
            while (rs.next()) {
                // Creamos un alumno
                Videojuego videojuego = new Videojuego(
                        rs.getLong("id"),
                        rs.getString("nombre"),
                        Plataforma.valueOf(rs.getString("plataforma")),
                        rs.getDouble("precio"),
                        rs.getObject("fecha_lanzamiento", LocalDate.class),
                        rs.getObject("created_at", LocalDateTime.class),
                        rs.getObject("updated_at", LocalDateTime.class),
                        rs.getBoolean("is_deleted")
                );
                // Lo añadimos a la lista
                lista.add(videojuego);
            }
            return lista;
        }
    }

    @Override
    public Optional<Videojuego> findById(Long id) throws SQLException {
        logger.debug("Obteniendo el videojuego con id: " + id);
        String query = "SELECT * FROM VIDEOJUEGOS WHERE id = ?";
        try (var connection = db.getConnection();
             var stmt = connection.prepareStatement(query)
        ) {
            stmt.setLong(1, id);
            var rs = stmt.executeQuery();
            Optional<Videojuego> videojuego = Optional.empty();
            while (rs.next()) {
                // Creamos un alumno
                videojuego = Optional.of(
                        new Videojuego(
                                rs.getLong("id"),
                                rs.getString("nombre"),
                                Plataforma.valueOf(rs.getString("plataforma")),
                                rs.getDouble("precio"),
                                rs.getObject("fecha_lanzamiento", LocalDate.class),
                                rs.getObject("created_at", LocalDateTime.class),
                                rs.getObject("updated_at", LocalDateTime.class),
                                rs.getBoolean("is_deleted")
                        )
                );
            }
            return videojuego;
        }
    }

    @Override
    public Videojuego save(Videojuego videojuego) throws SQLException, VideojuegoDataBaseException {
        logger.debug("Guardando el videojuego: " + videojuego);
        String query = "INSERT INTO VIDEOJUEGOS (nombre, plataforma, precio, fecha_lanzamiento, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?)";
        // Vamos a crear los datos de la consultaue necesitamos para insertar automaticos aunque los crea la base de datos
        try (var connection = db.getConnection();
             var stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        ) {
            videojuego.setCreatedAt(LocalDateTime.now());
            videojuego.setUpdatedAt(LocalDateTime.now());
            // importante debemos devolver el alumno con la clave, por eso usamos RETURN_GENERATED_KEYS
            stmt.setString(1, videojuego.getNombre());
            stmt.setString(2, videojuego.getPlataforma());
            stmt.setDouble(3, videojuego.getPrecio());
            stmt.setObject(4, videojuego.getFechaLanzamiento());
            stmt.setObject(5, videojuego.getCreatedAt());
            stmt.setObject(6, videojuego.getUpdatedAt());
            var res = stmt.executeUpdate();
            // Ahora puedo obtener la clave
            if (res > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                while (rs.next()) {
                    videojuego.setId(rs.getLong(1));
                }
                rs.close();
            } else {
                logger.error("Videojuego no guardado");
                throw new VideojuegoDataBaseException("Videojuego no guardado con id: " + videojuego.getId());
            }
        }
        return videojuego;
    }

    @Override
    public Videojuego update(Videojuego videojuego) throws SQLException, VideojuegoDataBaseException {
        logger.debug("Actualizando el videojuego: " + videojuego);
        String query = "UPDATE VIDEOJUEGOS SET nombre =?, plataforma =?, precio=?, fecha_lanzamiento =?, updated_at =? WHERE id =?";
        try (var connection = db.getConnection();
             var stmt = connection.prepareStatement(query)
        ) {
            // Vamos a crear los datos de la consultaue necesitamos para insertar automaticos aunque los crea la base de datos
            videojuego.setUpdatedAt(LocalDateTime.now());
            stmt.setString(1, videojuego.getNombre());
            stmt.setString(2, videojuego.getPlataforma());
            stmt.setDouble(3, videojuego.getPrecio());
            stmt.setObject(4, videojuego.getFechaLanzamiento());
            stmt.setObject(5, videojuego.getUpdatedAt());
            stmt.setLong(6, videojuego.getId());

            var res = stmt.executeUpdate();
            if (res > 0) {
                logger.debug("Videojuego actualizado");
            } else {
                logger.error("Videojuegos no actualizado al no encontrarse en la base de datos con id: " + videojuego.getId());
                throw new VideojuegoDataBaseException("Videojuego no encontrado con id: " + videojuego.getId());
            }
        }
        return videojuego;
    }


    @Override
    public boolean deleteById(Long id) throws SQLException {
        logger.debug("Borrando el videojuego con id: " + id);
        String query = "DELETE FROM VIDEOJUEGOS WHERE id =?";
        try (var connection = db.getConnection();
             var stmt = connection.prepareStatement(query)
        ) {
            stmt.setLong(1, id);
            var res = stmt.executeUpdate();
            return res > 0;
        }
    }

    @Override
    public void deleteAll() throws SQLException {
        logger.debug("Borrando todos los alumnos");
        String query = "DELETE FROM VIDEOJUEGOS";
        try (var connection = db.getConnection();
             var stmt = connection.prepareStatement(query)
        ) {
            stmt.executeUpdate();
        }
    }
}
