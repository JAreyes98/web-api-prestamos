package com.jdreyes.webapi.prestamos.model.dao.impl;

import com.jdreyes.persistence.impl.BaseDAOImpl;
import com.jdreyes.webapi.prestamos.model.dao.ClienteDAO;
import com.jdreyes.webapi.prestamos.model.entities.Cliente;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClienteDAOImpl extends BaseDAOImpl<Cliente, Integer> implements ClienteDAO {
  @Override
  public List<Cliente> findAll(Integer funcionarioId) {
    return entityManager
        .createQuery("select c from Cliente c where c.funcionario.idFuncionario = :idFuncionario")
        .setParameter("idFuncionario", funcionarioId)
        .getResultList();
  }

  @Override
  public List<Cliente> findById(Integer funcionarioId, Integer clienteId) {
    return entityManager
            .createQuery("select c from Cliente c where c.funcionario.idFuncionario = :idFuncionario and c.idCliente = :idCLiente")
            .setParameter("idFuncionario", funcionarioId)
            .setParameter("idCLiente", clienteId)
            .getResultList();
  }
}
