package com.jdreyes.webapi.prestamos.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * DTO (Objeto de transferencia de datos) que contiene la información consolidada de los cobros del dia.
 *
 * @author Josue Reyes
 * @version 1.0
 * @since 1.0, 2022-06-11
 */
@Getter
@Setter
public class CobrosDia implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Listado de los prestamos que se deben cobrar en el dia.
     */
    List<CobroPrestamoDto> cobros;
}
