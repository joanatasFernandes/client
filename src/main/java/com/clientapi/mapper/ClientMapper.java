package com.clientapi.mapper;

import client.model.Client;
import com.clientapi.model.ClientEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper extends BaseMapper<Client, ClientEntity> {
}
