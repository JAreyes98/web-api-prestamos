package com.jdreyes.webapi.prestamos.rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.util.List;

@Getter
@Setter
public class PrestamoRequestDto implements Serializable {
    private Integer idCliente;
    private BigDecimal montoPrestamo;
    private Short plazoDias;
    private List<DayOfWeek> diasPago;
    private String codigoPrestamo;

    public Short getPlazoDias() {
        return (short)(plazoDias * 20);
    }
}
