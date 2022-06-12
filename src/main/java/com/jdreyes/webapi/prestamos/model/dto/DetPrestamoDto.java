package com.jdreyes.webapi.prestamos.model.dto;

import com.jdreyes.webapi.prestamos.model.entities.DetallePrestamo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * DTO (Objeto de transferencia de datos) que contiene la información consolidada del prestamo.
 *
 * @author Josue Reyes
 * @version 1.0
 * @since 1.0, 2022-06-11
 */
@Getter
@Setter
public class DetPrestamoDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Identificador del prestamo.
     */
    private Integer idPrestamo;

    /**
     * Listada que representa la tabla de pagos del prestamo
     */
    private List<DetallePrestamo> prestamos;
}
