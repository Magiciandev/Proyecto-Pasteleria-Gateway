package com.example.pedido_service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.example.pedido_service.model.Pedido;
import com.example.pedido_service.repository.PedidoRepository;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final WebClient.Builder webClientBuilder;

    public PedidoService(PedidoRepository pedidoRepository, WebClient.Builder webClientBuilder) {
        this.pedidoRepository = pedidoRepository;
        this.webClientBuilder = webClientBuilder;
    }

    public Pedido guardar(Pedido pedido) {
        // 1. Validar si el Cliente existe (Comunicación con microservicio cliente-service)
        Boolean clienteExiste = webClientBuilder.build()
                .get()
                .uri("http://localhost:9091/clientes/" + pedido.getIdCliente() + "/exists")
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if (Boolean.FALSE.equals(clienteExiste)) {
            throw new IllegalArgumentException("[ERROR] CLIENTE NO EXISTE");
        }

        // 2. Validar si el Producto existe (Comunicación con microservicio producto-service)
        Boolean productoExiste = webClientBuilder.build()
                .get()
                .uri("http://localhost:9093/productos/" + pedido.getIdProducto() + "/exists")
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if (Boolean.FALSE.equals(productoExiste)) {
            throw new IllegalArgumentException("[ERROR] PRODUCTO NO EXISTE");
        }

        // Si ambos existen, guardar la compra
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public List<Pedido> buscarPorIdCliente(Long idCliente) {
        return pedidoRepository.findByIdCliente(idCliente);
    }

    public Pedido actualizar(Long id, Pedido detalles) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        if (pedido != null) {
            pedido.setIdCliente(detalles.getIdCliente());
            pedido.setIdProducto(detalles.getIdProducto());
            pedido.setCantidad(detalles.getCantidad());
            return pedidoRepository.save(pedido);
        }
        return null;
    }

    public void eliminar(Long id) {
        pedidoRepository.deleteById(id);
    }
}