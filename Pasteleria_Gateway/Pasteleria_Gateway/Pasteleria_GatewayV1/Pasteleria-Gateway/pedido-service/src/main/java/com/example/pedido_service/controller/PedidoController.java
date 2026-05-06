package com.example.pedido_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.pedido_service.dto.PedidoDTO;
import com.example.pedido_service.model.Pedido;
import com.example.pedido_service.service.PedidoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/compras") // El profesor usa /compras en su Postman
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    // POST: Crear compra con manejo de errores del profe (Punto 4 y 5)
    @PostMapping
    public ResponseEntity<?> crearPedido(@RequestBody PedidoDTO pedidoDto) {
        try {
            Pedido nuevoPedido = pedidoService.guardar(pedidoDto.toModel());
            return ResponseEntity.ok(PedidoDTO.fromModel(nuevoPedido));
        } catch (IllegalArgumentException e) {
            // Devuelve exactamente "[ERROR] CLIENTE NO EXISTE" o "[ERROR] PRODUCTO NO EXISTE"
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // GET: Listar todos (Punto 4)
    @GetMapping
    public ResponseEntity<List<PedidoDTO>> listarPedidos() {
        List<PedidoDTO> dtos = pedidoService.listar().stream()
                .map(PedidoDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // GET: Buscar por ID (Punto 4)
    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> obtenerPorId(@PathVariable Long id) {
        Pedido pedido = pedidoService.buscarPorId(id);
        return pedido != null ? ResponseEntity.ok(PedidoDTO.fromModel(pedido)) : ResponseEntity.notFound().build();
    }

    // PUT: Actualizar (Punto 4)
    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> actualizar(@PathVariable Long id, @RequestBody PedidoDTO dto) {
        Pedido actualizado = pedidoService.actualizar(id, dto.toModel());
        return actualizado != null ? ResponseEntity.ok(PedidoDTO.fromModel(actualizado)) : ResponseEntity.notFound().build();
    }

    // DELETE: Eliminar (Punto 4)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pedidoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // GET Especial: Buscar todas las compras de un Cliente (Punto 7)
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<PedidoDTO>> buscarPorCliente(@PathVariable Long idCliente) {
        List<PedidoDTO> dtos = pedidoService.buscarPorIdCliente(idCliente).stream()
                .map(PedidoDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}