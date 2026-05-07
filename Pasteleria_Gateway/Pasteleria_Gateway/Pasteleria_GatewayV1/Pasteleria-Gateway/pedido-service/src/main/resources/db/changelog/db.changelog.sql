--liquibase formatted sql

--changeset author:1
CREATE TABLE pedido (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_cliente BIGINT,
    id_producto BIGINT,
    cantidad INT
);

--changeset author:2
INSERT INTO pedido (id_cliente, id_producto, cantidad) VALUES
(1, 1, 2),
(2, 3, 1),
(3, 5, 3),
(4, 2, 2),
(5, 7, 4),
(6, 4, 1),
(7, 6, 2),
(8, 8, 5),
(9, 9, 2),
(10, 10, 1),
(1, 5, 3),
(2, 1, 2),
(3, 2, 1),
(4, 9, 4),
(5, 3, 2);
