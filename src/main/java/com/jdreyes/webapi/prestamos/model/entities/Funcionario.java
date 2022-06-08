package com.jdreyes.webapi.prestamos.model.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Entidad que representa el modelo de datos de la tabla funcionarios.
 *
 * @author Josue Reyes
 * @version 1.0
 * @since 1.0, 2022-06-07
 */
@Entity
@Table(name = "funcionarios")
@Getter
@Setter
public class Funcionario implements Serializable {

    private final long serialVersionUID = 1L;


    @Id
    @Column(name = "id_cia")
    private Integer companyId;

    @JoinColumn(name = "id_cia", referencedColumnName = "id_cia", insertable = false, updatable = false)
    @ManyToOne
    private Company company;

    @Id
    @Column(name = "id_funcionario")
    private Integer idFuncionario;

    @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal",insertable = false, updatable = false)
    @ManyToOne
    private Sucursal sucursal;

    @Id
    @Column(name = "id_sucursal")
    private Integer idSucursal;

    @Column(name = "nombre")
    private String nombres;

    @Column(name = "Cedula")
    private String cedula;

    @Column(name = "id_cargo")
    private Integer id_cargo;

    @Column(name = "fecha_Alta")
    private Date fechaAlta;

    @Column(name = "Monto_asignado")
    private BigDecimal montoAsignado;

    @Column(name = "Monto_Cartera")
    private BigDecimal montoCartera;

    @Column(name = "Momto_Recuperado")
    private BigDecimal montoRecuperado;

    @Column(name = "Gastos")
    private BigDecimal gastos;

    @Column(name = "Saldo_Neto")
    private BigDecimal saldoNeto;

    @Column(name = "fecha_baja")
    private Date fechaBaja;

    @Column(name = "Concepto_Baja")
    private String conceptoBaja;

    @Column(name = "Depositos")
    private BigDecimal depositos;



}
