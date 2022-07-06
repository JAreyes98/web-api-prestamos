package com.jdreyes.webapi.prestamos.model.dto.cliente;


import com.jdreyes.webapi.prestamos.model.dto.RutaDto;
import com.jdreyes.webapi.prestamos.model.entities.Funcionario;
import com.jdreyes.webapi.prestamos.model.entities.Sucursal;
import com.jdreyes.webapi.prestamos.service.dtos.FuncionarioDto;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ClienteDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idCliente;

    private Sucursal sucursal;

    private RutaDto ruta;

    private String nombre;

    private String cedula;

    private String direccion;

    private String telefono;

    private String date;

    private Integer noPrestamo;

    private BigDecimal montoPrstamos;

    private BigDecimal saldoActual;


    private FuncionarioDto funcionario;

}
