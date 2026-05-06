package com.example.pedido_service.dto;

import com.example.pedido_service.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoDTO {
    private Long id;
    private Long idCliente;
    private Long idProducto;
    private Integer cantidad;

    public Pedido toModel() {
        return new Pedido(id, idCliente, idProducto, cantidad);
    }

    public static PedidoDTO fromModel(Pedido p) {
        if (p == null) return null;
        return new PedidoDTO(p.getId(), p.getIdCliente(), p.getIdProducto(), p.getCantidad());
    }
}