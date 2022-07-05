package com.jdreyes.webapi.prestamos.service;

import com.jdreyes.webapi.prestamos.model.dto.cliente.ClienteDto;

import java.util.List;

public interface ClienteService {

    List<ClienteDto> getClientes();

    List<ClienteDto> getById(Integer clienteId);
}
