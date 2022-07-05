package com.jdreyes.webapi.prestamos.model.dao;

import com.jdreyes.persistence.BaseDAO;
import com.jdreyes.webapi.prestamos.model.entities.Cliente;

import java.util.List;

public interface ClienteDAO extends BaseDAO<Cliente, Integer> {
    List<Cliente> findAll(Integer funcionarioId);
    List<Cliente> findById(Integer funcionarioId, Integer clienteId);
}
