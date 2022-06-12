package com.jdreyes.webapi.prestamos.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
/**
 * DTO (Objeto de transferencia de datos) que contiene la información consolidada del cliente.
 *
 * @author Josue Reyes
 * @version 1.0
 * @since 1.0, 2022-06-11
 */
@Getter
@Setter
@EqualsAndHashCode
public class ClienteDto implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * Identificador.
   */
  private Integer id;
  /**
   * Nombres del cliente.
   */
  private String nombres;
  /**
   * Dirección del cliente.
   */
  private String direccion;
}
