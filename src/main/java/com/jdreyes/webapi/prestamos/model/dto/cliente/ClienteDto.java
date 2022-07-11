package com.jdreyes.webapi.prestamos.model.dto.cliente;


import com.jdreyes.webapi.prestamos.model.dto.BaseClienteDto;
import com.jdreyes.webapi.prestamos.model.dto.RutaDto;
import com.jdreyes.webapi.prestamos.model.entities.Funcionario;
import com.jdreyes.webapi.prestamos.model.entities.Sucursal;
import com.jdreyes.webapi.prestamos.service.dtos.FuncionarioDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
public class ClienteDto extends BaseClienteDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Sucursal sucursal;

    private RutaDto ruta;

    private String cedula;

    private String telefono;

    private String date;

    private Integer noPrestamo;

    private BigDecimal montoPrstamos;

    private BigDecimal saldoActual;


    private FuncionarioDto funcionario;

}
