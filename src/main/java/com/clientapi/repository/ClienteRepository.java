package com.clientapi.repository;

import com.clientapi.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c FROM Client c ORDER BY c.name")
    List<Client> findAllOrderedByName();

    @Query("SELECT c FROM Client c WHERE c.endereco.estado = ?1")
    List<Client> findAllByEstado(String estado);
}
