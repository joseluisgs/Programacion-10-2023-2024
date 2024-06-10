package dev.joseluisgs.service.videojuegos;

import dev.joseluisgs.exception.VideojuegoDataBaseException;
import dev.joseluisgs.exception.VideojuegoException;
import dev.joseluisgs.model.Plataforma;
import dev.joseluisgs.model.Videojuego;
import dev.joseluisgs.repository.VideojuegosRepository;
import dev.joseluisgs.service.cache.VidejuegosCache;
import dev.joseluisgs.service.storage.VideojuegosCsvStorage;
import dev.joseluisgs.service.storage.VideojuegosJsonStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VideojuegosServiceImplTest {

    @Mock
    private VidejuegosCache cache;

    @Mock
    private VideojuegosRepository repository;

    @Mock
    private VideojuegosCsvStorage videojuegosCsvStorage;

    @Mock
    private VideojuegosJsonStorage videojuegosJsonStorage;

    @InjectMocks
    private VideojuegosServiceImpl videojuegosService;

    private Videojuego videojuego;

    @BeforeEach
    void setUp() {
        videojuego = new Videojuego(1L, "The Legend of Zelda: Breath of the Wild", Plataforma.NINTENDO, 59.99, LocalDate.of(2017, 3, 3));
    }

    @Test
    void testFindAll() throws SQLException {
        when(repository.findAll()).thenReturn(List.of(videojuego));

        List<Videojuego> result = videojuegosService.findAll();

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(1, result.size()),
                () -> assertEquals(videojuego, result.getFirst())
        );

        verify(repository, times(1)).findAll();
    }

    @Test
    void testFindAllByNombre() throws SQLException {
        String nombre = "Zelda";
        when(repository.findByNombre(nombre)).thenReturn(List.of(videojuego));

        List<Videojuego> result = videojuegosService.findAllByNombre(nombre);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(1, result.size()),
                () -> assertTrue(result.getFirst().getNombre().contains(nombre))
        );

        verify(repository, times(1)).findByNombre(nombre);
    }

    @Test
    void testFindById() throws SQLException, VideojuegoDataBaseException {
        long id = 1L;
        when(cache.get(id)).thenReturn(null);
        when(repository.findById(id)).thenReturn(Optional.of(videojuego));

        Videojuego result = videojuegosService.findById(id);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(videojuego, result)
        );

        verify(cache, times(1)).get(id);
        verify(repository, times(1)).findById(id);
        verify(cache, times(1)).put(id, videojuego);
    }

    @Test
    void testFindByIdThrowsException() throws SQLException {
        long id = 1L;

        when(cache.get(id)).thenReturn(null);
        when(repository.findById(id)).thenReturn(Optional.empty());

        VideojuegoDataBaseException exception = assertThrows(VideojuegoDataBaseException.class, () -> {
            videojuegosService.findById(id);
        });

        assertEquals("No se ha encontrado el videojuego con ID: " + id, exception.getMessage());

        verify(cache, times(1)).get(id);
        verify(repository, times(1)).findById(id);
        verify(cache, never()).put(anyLong(), any(Videojuego.class));
    }

    @Test
    void testSave() throws SQLException, VideojuegoException {
        when(repository.save(videojuego)).thenReturn(videojuego);

        Videojuego result = videojuegosService.save(videojuego);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(videojuego, result)
        );

        verify(repository, times(1)).save(videojuego);
        verify(cache, times(1)).put(videojuego.getId(), videojuego);
    }

    @Test
    void testSaveThrowsSQLException() throws SQLException, VideojuegoException {
        when(repository.save(videojuego)).thenThrow(new SQLException("Error al guardar"));

        SQLException exception = assertThrows(SQLException.class, () -> {
            videojuegosService.save(videojuego);
        });

        assertEquals("Error al guardar", exception.getMessage());

        verify(repository, times(1)).save(videojuego);
        verify(cache, never()).put(anyLong(), any(Videojuego.class));
    }

    @Test
    void testUpdate() throws SQLException, VideojuegoException {
        when(repository.findById(videojuego.getId())).thenReturn(Optional.of(videojuego));
        when(repository.update(videojuego)).thenReturn(videojuego);

        Videojuego result = videojuegosService.update(videojuego);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(videojuego, result)
        );

        verify(repository, times(1)).findById(videojuego.getId());
        verify(repository, times(1)).update(videojuego);
        verify(cache, times(2)).put(videojuego.getId(), videojuego); // Se llama en findById y en update
    }

    @Test
    void testUpdateThrowsExceptionWhenNotFound() throws SQLException, VideojuegoException {
        long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        VideojuegoDataBaseException exception = assertThrows(VideojuegoDataBaseException.class, () -> {
            videojuegosService.update(videojuego);
        });

        assertEquals("No se ha encontrado el videojuego con ID: " + id, exception.getMessage());

        verify(repository, times(1)).findById(videojuego.getId());
        verify(repository, never()).update(any(Videojuego.class));
    }

    @Test
    void testDeleteById() throws SQLException, VideojuegoException {
        long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(videojuego));

        Videojuego result = videojuegosService.deleteById(id);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(videojuego, result)
        );

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).deleteById(id);
        verify(cache, times(1)).remove(id);
    }

    @Test
    void testDeleteByIdThrowsExceptionWhenNotFound() throws SQLException {
        long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        VideojuegoDataBaseException exception = assertThrows(VideojuegoDataBaseException.class, () -> {
            videojuegosService.deleteById(id);
        });

        assertEquals("No se ha encontrado el videojuego con ID: " + id, exception.getMessage());

        verify(repository, times(1)).findById(id);
        verify(repository, never()).deleteById(id);
        verify(cache, never()).remove(id);
    }

    @Test
    void testDeleteAll() throws SQLException {
        videojuegosService.deleteAll();

        verify(repository, times(1)).deleteAll();
        verify(cache, times(1)).clear();
    }

    @Test
    void testLoadFromFile() throws SQLException, VideojuegoException {
        File file = new File("test.csv");
        when(videojuegosCsvStorage.readFromFile(file)).thenReturn(List.of(videojuego));

        List<Videojuego> result = videojuegosService.loadFromFile(file);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(1, result.size()),
                () -> assertEquals(videojuego, result.getFirst())
        );

        verify(videojuegosCsvStorage, times(1)).readFromFile(file);
    }

    @Test
    void testSaveToFile() throws SQLException, VideojuegoException {
        File file = new File("test.json");
        Map<String, List<Videojuego>> mapa = Map.of("key", List.of(videojuego));

        videojuegosService.saveToFile(file, mapa);

        verify(videojuegosJsonStorage, times(1)).saveToFile(mapa, file);
    }
}
