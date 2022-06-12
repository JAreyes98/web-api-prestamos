package com.jdreyes.webapi.prestamos.service.dtos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
public class ReciboCajaDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer idPrestamo;
    private FuncionarioDto funcionario;
    private BigDecimal monto;
}
