package com.example.cliente_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.cliente_service.model.Cliente;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Búsqueda por atributo distinto al ID (Punto 7)
    Optional<Cliente> findByRutCliente(String rutCliente);
}