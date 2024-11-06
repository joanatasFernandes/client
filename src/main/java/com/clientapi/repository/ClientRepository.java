package com.clientapi.repository;

import com.clientapi.model.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    @Query("SELECT c FROM ClientEntity c ORDER BY c.name")
    List<ClientEntity> findAllOrderedByName();

    @Query("SELECT c FROM ClientEntity c WHERE c.address.state = ?1")
    List<ClientEntity> findAllByState(String state);
}
