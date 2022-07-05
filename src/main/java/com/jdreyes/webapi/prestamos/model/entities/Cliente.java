package com.jdreyes.webapi.prestamos.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Id_Cliente")
    private Integer idCliente;

    @JoinColumn(name = "Id_Sucursal", referencedColumnName = "Id_Sucursal")
    @ManyToOne
    private Sucursal sucursal;

    @Column(name = "Id_Ruta")
    private Integer rutaId;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Cedula")
    private String cedula;

    @Column(name = "Direccion")
    private String direccion;

    @Column(name = "Telefono")
    private String telefono;

    @Column(name = "Fecha")
    private String date;

    @Column(name = "No_Prestamos")
    private Integer noPrestamo;

    @Column(name = "Montos_Prestados")
    private BigDecimal montoPrstamos;

    @Column(name = "Saldo_Actual")
    private BigDecimal saldoActual;


    @JoinColumn(name = "Id_Gestor", referencedColumnName = "id_funcionario")
    @ManyToOne
    private Funcionario funcionario;

}
