package com.jdreyes.webapi.prestamos.service.dtos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
public class DepositoBancoDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private FuncionarioDto funcionario;
  private String noCuenta;
  private BigDecimal monto;
  private Integer noMinuta;
}
