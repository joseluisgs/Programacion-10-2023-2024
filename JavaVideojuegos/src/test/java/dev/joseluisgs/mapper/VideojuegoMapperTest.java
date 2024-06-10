package dev.joseluisgs.mapper;

import dev.joseluisgs.dto.VideojuegoDto;
import dev.joseluisgs.model.Plataforma;
import dev.joseluisgs.model.Videojuego;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class VideojuegoMapperTest {

    private Videojuego videojuego;
    private VideojuegoDto videojuegoDto;


    @BeforeEach
    void setUp() {
        // Inicializar objetos de prueba
        videojuego = new Videojuego(1L, "The Legend of Zelda: Breath of the Wild", Plataforma.NINTENDO, 59.99, LocalDate.of(2017, 3, 3));
        videojuegoDto = new VideojuegoDto("1", "The Legend of Zelda: Breath of the Wild", "NINTENDO", "59.99", "2017-03-03");
    }

    @Test
    void testToDto() {
        VideojuegoDto result = VideojuegoMapper.toDto(videojuego);

        assertAll("VideojuegoDto",
                () -> assertNotNull(result),
                () -> assertEquals(videojuegoDto.getId(), result.getId()),
                () -> assertEquals(videojuegoDto.getTitulo(), result.getTitulo()),
                () -> assertEquals(videojuegoDto.getPlataforma(), result.getPlataforma()),
                () -> assertEquals(videojuegoDto.getPrecio(), result.getPrecio()),
                () -> assertEquals(videojuegoDto.getFechaLanzamiento(), result.getFechaLanzamiento())
        );
    }

    @Test
    void testToVideojuego() {
        Videojuego result = VideojuegoMapper.toVidejuego(videojuegoDto);

        assertAll("Videojuego",
                () -> assertNotNull(result),
                () -> assertEquals(videojuego.getId(), result.getId()),
                () -> assertEquals(videojuego.getNombre(), result.getNombre()),
                () -> assertEquals(videojuego.getPlataforma(), result.getPlataforma()),
                () -> assertEquals(videojuego.getPrecio(), result.getPrecio()),
                () -> assertEquals(videojuego.getFechaLanzamiento(), result.getFechaLanzamiento())
        );
    }
}