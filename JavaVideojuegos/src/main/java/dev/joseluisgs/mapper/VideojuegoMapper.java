package dev.joseluisgs.mapper;

import dev.joseluisgs.dto.VideojuegoDto;
import dev.joseluisgs.model.Plataforma;
import dev.joseluisgs.model.Videojuego;

import java.time.LocalDate;

public class VideojuegoMapper {

    public static Videojuego toVidejuego(VideojuegoDto dto) {
        return new Videojuego(Long.parseLong(dto.getId()), dto.getTitulo(), Plataforma.valueOf(dto.getPlataforma()), Double.parseDouble(dto.getPrecio()), LocalDate.parse(dto.getFechaLanzamiento()));
    }

    public static VideojuegoDto toDto(Videojuego videojuego) {
        return new VideojuegoDto(String.valueOf(videojuego.getId()), videojuego.getNombre(), videojuego.getPlataforma(), String.valueOf(videojuego.getPrecio()), videojuego.getFechaLanzamiento().toString());
    }
}
