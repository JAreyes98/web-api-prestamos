package com.jdreyes.webapi.prestamos.model.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase base para los usuarios.
 *
 * @author Josue Reyes
 * @version 1.0
 * @since 1.0, 2022-06-7
 */
@Getter
@Setter
public abstract class UserBase {
  //    private Integer idCia;
  /** Nombre de usuario. */
  private String userName;
  /** Contraseña. */
  private String password;
}
