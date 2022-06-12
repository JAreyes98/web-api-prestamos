package com.jdreyes.webapi.prestamos.model.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Entidad que representa el modelo de datos de la tabla cargos.
 *
 * @author Josue Reyes
 * @version 1.0
 * @since 1.0, 2022-06-07
 */
@Entity
@Table(name = "cargos")
@Getter
@Setter
public class Cargo implements Serializable {

    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal",insertable = false, updatable = false)
    @ManyToOne
    private Sucursal sucursal;

    @Id
    @Column(name = "id_sucursal")
    private Integer idSucursal;

    @Id
    @Column(name = "id_cargo")
    private Integer idCargo;

    @Column(name = "Descripcion_Cargo")
    private String descripcionCargo;

    @Column(name = "Salario_Mensual")
    private BigDecimal salarioMensual;

    @Column(name = "Por_Comision_Colocacion")
    private BigDecimal comisionColocacion;

    @Column(name = "Por_Comision_Cobranza")
    private BigDecimal comisionCobranza;

    @Column(name = "Por_Incentivo_Cobranza")
    private BigDecimal comisionCobranzaIncentivo;

}
