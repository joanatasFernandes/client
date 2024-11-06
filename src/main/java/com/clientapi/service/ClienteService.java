package com.clientapi.service;

import com.clientapi.model.Client;
import com.clientapi.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Client saveCliente(Client cliente) {
        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    public List<Client> getAllClientesOrderedByName() {
        return clienteRepository.findAllOrderedByName();
    }

    public List<Client> getClientesByEstado(String estado) {
        return clienteRepository.findAllByEstado(estado);
    }
}

