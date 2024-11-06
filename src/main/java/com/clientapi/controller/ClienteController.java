package com.clientapi.controller;

import com.clientapi.model.Client;
import com.clientapi.service.ClienteService;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Cliente", description = "Gerenciamento de clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar um novo cliente")
    public ResponseEntity<Client> createCliente(@RequestBody Client cliente) {
        return new ResponseEntity<>(clienteService.saveCliente(cliente), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um cliente")
    public ResponseEntity<Client> updateCliente(@PathVariable Long id, @RequestBody Client cliente) {
        cliente.setId(id);
        return new ResponseEntity<>(clienteService.saveCliente(cliente), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um cliente")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    @Operation(summary = "Listar todos os clientes")
    public List<Client> getAllClientesOrderedByName() {
        return clienteService.getAllClientesOrderedByName();
    }

    @GetMapping("/estado/{estado}")
    @Operation(summary = "Listar os clientes por estado")
    public List<Client> getClientesByEstado(@PathVariable String estado) {
        return clienteService.getClientesByEstado(estado);
    }
}
