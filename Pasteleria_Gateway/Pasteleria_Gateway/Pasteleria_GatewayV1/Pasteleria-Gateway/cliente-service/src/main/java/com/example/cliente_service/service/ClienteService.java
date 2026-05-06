package com.example.cliente_service.service;

import org.springframework.stereotype.Service;
import com.example.cliente_service.model.Cliente;
import com.example.cliente_service.repository.ClienteRepository;
import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente buscarPorRut(String rut) {
        return clienteRepository.findByRutCliente(rut).orElse(null);
    }

    public boolean existePorId(Long id) {
        return clienteRepository.existsById(id);
    }

    public Cliente actualizar(Long id, Cliente clienteDetalles) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente != null) {
            cliente.setRutCliente(clienteDetalles.getRutCliente());
            cliente.setNombre(clienteDetalles.getNombre());
            cliente.setCorreo(clienteDetalles.getCorreo());
            cliente.setTelefono(clienteDetalles.getTelefono());
            cliente.setDireccion(clienteDetalles.getDireccion());
            return clienteRepository.save(cliente);
        }
        return null;
    }

    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }
}