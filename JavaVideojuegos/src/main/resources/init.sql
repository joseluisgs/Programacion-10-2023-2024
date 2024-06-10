-- Cuidado que cada SGDB tiene su forma de crear tablas y datos
DROP TABLE IF EXISTS VIDEOJUEGOS;

CREATE TABLE IF NOT EXISTS VIDEOJUEGOS (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    plataforma VARCHAR(50) NOT NULL,
    precio DOUBLE NOT NULL,
    fecha_lanzamiento DATE NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

INSERT INTO VIDEOJUEGOS (nombre, plataforma, precio, fecha_lanzamiento)  VALUES
                                                                             ('The Legend of Zelda: Breath of the Wild', 'NINTENDO', 59.99, '2021-03-03'),
                                                                             ('God of War', 'SONY', 49.99, '2018-04-20');
