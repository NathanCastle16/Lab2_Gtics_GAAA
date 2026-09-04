CREATE DATABASE clinica_veterinaria
CHARACTER SET utf8mb4
COLLATE utf8mb4_spanish_ci;

USE clinica_veterinaria;

CREATE TABLE mascota (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50),
    especie VARCHAR(50),
    raza VARCHAR(50),
    edad INT,
    nombre_dueno VARCHAR(100),
    telefono VARCHAR(20),
    estado TINYINT(1)
);

INSERT INTO mascota (nombre, especie, raza, edad, nombre_dueno, telefono, estado) VALUES
('Max', 'Perro', 'Labrador', 5, 'Carlos Ruiz', '999888777', 1),
('Luna', 'Gato', 'Siames', 3, 'Ana Torres', '988777666', 1),
('Rocky', 'Perro', 'Bulldog', 7, 'Luis Pérez', '977666555', 0),
('Milo', 'Gato', 'Persa', 2, 'María López', '966555444', 1),
('Toby', 'Perro', 'Beagle', 4, 'José García', '955444333', 1);
