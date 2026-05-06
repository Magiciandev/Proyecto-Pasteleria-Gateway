--liquibase formatted sql

--changeset author:1
CREATE TABLE producto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    precio DOUBLE,
    stock INT
);

--changeset author:2
INSERT INTO producto (nombre, precio, stock) VALUES
('Torta Tres Leches', 15000, 10),
('Torta de Chocolate', 18000, 8),
('Cheesecake de Frambuesa', 12000, 15),
('Pie de Limón', 10000, 20),
('Kuchen de Nuez', 14000, 12),
('Cupcake de Vainilla', 2000, 50),
('Cupcake Red Velvet', 2500, 40),
('Galletas con Chips de Chocolate', 500, 100),
('Pan Amasado (Docena)', 3000, 30),
('Empanada de Pino', 2000, 60);