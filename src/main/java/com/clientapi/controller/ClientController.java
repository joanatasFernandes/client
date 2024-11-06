package com.clientapi.controller;

import client.api.ClientsApi;
import client.model.Client;
import com.clientapi.mapper.ClientMapper;
import com.clientapi.model.ClientEntity;
import com.clientapi.service.ClientService;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClientController implements ClientsApi {

    private final ClientMapper clientMapper;
    private final ClientService clientService;

    @Override
    public ResponseEntity<Client> updateClient(Long id, Client client) {
        client.setId(id);
        var entity = clientMapper.toEntity(client);
        ClientEntity clientEntity = clientService.saveClient(entity);
        var updatedClient = clientMapper.toDto(clientEntity);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Client>> getClientsByState(String state) {
        List<ClientEntity> clientsByState = clientService.getClientsByState(state);
        List<Client> clientsDto = clientMapper.toDto(clientsByState);
        return new ResponseEntity<>(clientsDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Client>> getAllClientsOrderedByName() {
        List<ClientEntity> clientsByState = clientService.getAllClientsOrderedByName();
        List<Client> clientsDto = clientMapper.toDto(clientsByState);
        return new ResponseEntity<>(clientsDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteClient(Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Client> createClient(Client client) {
        var entity = clientMapper.toEntity(client);
        ClientEntity clientEntity = clientService.saveClient(entity);
        var createdClient = clientMapper.toDto(clientEntity);
        return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
    }
}
