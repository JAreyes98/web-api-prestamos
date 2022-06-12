package com.jdreyes.webapi.prestamos.model.entities;

import java.io.Serializable;

/**
 * Entidad que representa el modelo de datos que representa la clave compuesta por el id y no cuota
 * del prestamo.
 *
 * @author Josue Reyes
 * @version 1.0
 * @since 1.0, 2022-06-07
 */
public class DetallePrestamoId implements Serializable {
  private static final long serialVersionUID = 1L;
  private Integer idPrestamo;
  private Integer noCuota;
}
