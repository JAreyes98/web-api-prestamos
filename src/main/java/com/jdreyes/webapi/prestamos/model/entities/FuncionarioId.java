package com.jdreyes.webapi.prestamos.model.entities;


import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Entidad que representa el modelo de datos que representa la clave compuesta por el comañia, idFuncionario y sucursal.
 * del prestamo.
 *
 * @author Josue Reyes
 * @version 1.0
 * @since 1.0, 2022-06-07
 */
public class FuncionarioId implements Serializable {
    private Integer companyId;
    private Integer idFuncionario;
    private Integer idSucursal;
}
