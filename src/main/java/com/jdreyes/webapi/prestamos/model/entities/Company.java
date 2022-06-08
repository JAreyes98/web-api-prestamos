package com.jdreyes.webapi.prestamos.model.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * Entidad que representa el modelo de datos de la tabla compañia.
 *
 * @author Josue Reyes
 * @version 1.0
 * @since 1.0, 2022-06-07
 */
@Entity
@Table(name = "compañia")
@Data
public class Company implements Serializable {

  private final long serialVersionUID = 1L;

  @Id
  @Column(name = "Id_Cia")
  private Integer idCia;

  @Column(name = "nombre_Compañia")
  @NotNull(message = "Nombre de la compañia: requerido")
  private String nombre;

  @Column(name = "Direccion_Compañia")
  @NotNull(message = "Direccion de la compañia: requerido")
  private String direccion;
}
