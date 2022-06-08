package com.jdreyes.webapi.prestamos.model.entities;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Entidad que representa el modelo de datos de la tabla sucursales.
 *
 * @author Josue Reyes
 * @version 1.0
 * @since 1.0, 2022-06-07
 */
@Entity
@Table(name = "Sucursal")
@Data
public class Sucursal implements Serializable {

    private final long serialVersionUID = 1L;

    @Id
    @Column(name = "Id_Sucursal")
    private Integer idSucursal;

    @Column(name = "Nombre_Sucursal")
    private String nombre;

    @Column(name = "Direccion_Sucursal")
    private String direccion;

    @Column(name = "Monto_Cobranza_incntivo")
    private BigDecimal montoIncentivo;



}
