package com.example.cliente_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.cliente_service.dto.ClienteDTO;
import com.example.cliente_service.model.Cliente;
import com.example.cliente_service.service.ClienteService;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // POST: Crear (Punto 4)
    @PostMapping
    public ResponseEntity<ClienteDTO> crearCliente(@RequestBody ClienteDTO clienteDto) {
        Cliente nuevoCliente = clienteService.guardar(clienteDto.toModel());
        return ResponseEntity.ok(ClienteDTO.fromModel(nuevoCliente));
    }

    // GET: Listar todos (Punto 4)
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        List<ClienteDTO> dtos = clienteService.listar().stream()
                .map(ClienteDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // GET: Buscar por ID (Punto 4)
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerPorId(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarPorId(id);
        return cliente != null ? ResponseEntity.ok(ClienteDTO.fromModel(cliente)) : ResponseEntity.notFound().build();
    }

    // PUT: Actualizar (Punto 4)
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizar(@PathVariable Long id, @RequestBody ClienteDTO dto) {
        Cliente actualizado = clienteService.actualizar(id, dto.toModel());
        return actualizado != null ? ResponseEntity.ok(ClienteDTO.fromModel(actualizado)) : ResponseEntity.notFound().build();
    }

    // DELETE: Eliminar (Punto 4)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // GET Especial: Buscar por RUT (Punto 7)
    @GetMapping("/rut/{rut}")
    public ResponseEntity<ClienteDTO> buscarPorRut(@PathVariable String rut) {
        Cliente cliente = clienteService.buscarPorRut(rut);
        return cliente != null ? ResponseEntity.ok(ClienteDTO.fromModel(cliente)) : ResponseEntity.notFound().build();
    }

    // Endpoint para comunicación con Pedido-Service
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existeCliente(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.existePorId(id));
    }
}