package com.jdreyes.webapi.prestamos.service.impl;

import com.jdreyes.webapi.prestamos.model.dao.ClienteDAO;
import com.jdreyes.webapi.prestamos.model.dto.cliente.ClienteDto;
import com.jdreyes.webapi.prestamos.service.ClienteService;
import com.jdreyes.webapi.prestamos.service.dtos.FuncionarioDto;
import com.jdreyes.webapi.prestamos.service.utils.ContextUtils;
import com.jdreyes.webapi.prestamos.service.utils.TransformUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

  private final ClienteDAO clienteDAO;

  @Autowired
  public ClienteServiceImpl(ClienteDAO clienteDAO) {
    this.clienteDAO = clienteDAO;
  }

  @Override
  public List<ClienteDto> getClientes() {
    FuncionarioDto funcionario = ContextUtils.getCurrentUser();
    return clienteDAO.findAll(funcionario.getIdFuncionario()).stream()
        .map(TransformUtils::transformCliente)
        .collect(Collectors.toList());
  }

  @Override
  public List<ClienteDto> getById(Integer clienteId) {
      FuncionarioDto funcionario = ContextUtils.getCurrentUser();
    return clienteDAO.findById(funcionario.getIdFuncionario(), clienteId).stream()
            .map(TransformUtils::transformCliente)
            .collect(Collectors.toList());
  }
}
