package com.jdreyes.webapi.prestamos.service.utils;

import com.jdreyes.webapi.prestamos.model.dto.RutaDto;
import com.jdreyes.webapi.prestamos.model.dto.cliente.ClienteDto;
import com.jdreyes.webapi.prestamos.model.entities.Cliente;
import com.jdreyes.webapi.prestamos.model.entities.Funcionario;
import com.jdreyes.webapi.prestamos.service.dtos.FuncionarioDto;

import java.util.Collections;

public class TransformUtils {
  public static ClienteDto transformCliente(Cliente cliente) {
    ClienteDto dto = new ClienteDto();
    dto.setIdCliente(cliente.getIdCliente());
    dto.setCedula(cliente.getCedula());
    dto.setDate(cliente.getDate());
    dto.setDireccion(cliente.getDireccion());
    dto.setFuncionario(transformFuncionario(cliente.getFuncionario()));
    dto.setNombre(cliente.getNombre());
    dto.setMontoPrstamos(cliente.getMontoPrstamos());
    dto.setRuta(new RutaDto(cliente.getRutaId(), null));
    dto.setNoPrestamo(cliente.getNoPrestamo());
    dto.setSucursal(cliente.getSucursal());
    dto.setTelefono(cliente.getTelefono());
    return dto;
  }

  public static FuncionarioDto transformFuncionario(Funcionario funcionario) {
    FuncionarioDto dto = new FuncionarioDto();
    dto.setPassword(null);
    dto.setAuthorities(Collections.EMPTY_LIST);
    dto.setIdFuncionario(funcionario.getIdFuncionario());
    dto.setDepositos(funcionario.getDepositos());
    dto.setGastos(funcionario.getGastos());
    dto.setIdCia(funcionario.getCompanyId());
    dto.setIdSucursal(funcionario.getIdSucursal());
    dto.setMontoCartera(funcionario.getMontoCartera());
    dto.setMontoRecuperado(funcionario.getMontoRecuperado());
    dto.setNombres(funcionario.getNombres());
    dto.setSaldoNeto(funcionario.getSaldoNeto());
    dto.setUserId(funcionario.getIdFuncionario());
    dto.setUsername(null);
    dto.setEnabled(true);
    return dto;
  }
}
