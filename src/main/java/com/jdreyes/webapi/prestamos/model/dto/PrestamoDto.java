package com.jdreyes.webapi.prestamos.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;

/**
 * DTO (Objeto de transferencia de datos) que contiene la información base del prestamo. <br/>
 *
 * @author Josue Reyes
 * @version 1.0
 * @since 1.0, 2022-06-11
 */
public class PrestamoDto {
    @Getter
    @Setter
    private Integer idPrestamo;
}
