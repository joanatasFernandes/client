package com.clientapi.service;

import com.clientapi.model.ClientEntity;
import com.clientapi.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientEntity saveClient(ClientEntity cliente) {
        return clientRepository.save(cliente);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    public List<ClientEntity> getAllClientsOrderedByName() {
        return clientRepository.findAllOrderedByName();
    }

    public List<ClientEntity> getClientsByEstado(String estado) {
        return clientRepository.findAllByState(estado);
    }
}

