package com.jdreyes.webapi.prestamos.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "prestamos_detalle")
@IdClass(DetallePrestamoId.class)
public class DetallePrestamo {

    @Id
    @Column(name = "Id_Prestamos")
    private Integer idPrestamo;

    @Id
    @Column(name = "no_Cuota")
    private Integer noCuota;

    @Column(name = "Fecha")
    private Date fecha;

    @Column(name = "Monto_Cuota")
    private BigDecimal montoCuota;

    @Column(name = "Saldo")
    private BigDecimal saldo;

    @Column(name = "pago")
    private BigDecimal pago;


    @Column(name = "Saldo")
    private BigDecimal sando;

    @Column(name = "Fecha_ultimo_Pago")
    private Date fechaUltimoPago;
}
