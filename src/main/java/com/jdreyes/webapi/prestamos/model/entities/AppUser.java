package com.jdreyes.webapi.prestamos.model.entities;

import com.jdreyes.webapi.prestamos.model.dto.UserBase;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Entidad que representa el modelo de datos de la tabla Usuarios.
 *
 * @author Josue Reyes
 * @version 1.0
 * @since 1.0, 2022-06-07
 */
@Entity
@Table(name = "usuarios")
@Data
public class AppUser extends UserBase implements Serializable {

  private static final long SERIALVERSIONUID = 1L;

  @Column(name = "Id_Usuario")
  @GeneratedValue(strategy = GenerationType.AUTO)
  @NotNull(message = "Identificador del usuario: requerido")
  private Integer idUsuario;

  @Id
  @Column(name = "Login")
  @NotNull(message = "Nombre de Usuario: requerido")
  private String userName;

  @Column(name = "PassWord")
  @NotNull(message = "Contraseña: requerido")
  private String password;

  @Column(name = "id_Funcionario")
  @NotNull(message = "Id funcionario: requerido")
  private Integer idTipoFuncionario;

  @NotNull(message = "Id funcionario: requerido")
  @JoinColumn(name = "id_Funcionario", referencedColumnName = "id_funcionario", insertable = false, updatable = false)
  @ManyToOne
  private Funcionario funcionario;

  @Column(name = "Nombre_Usuario")
  private String nombres;

  @JoinColumn(name = "Id_Sucursal", referencedColumnName = "Id_Sucursal", insertable = false, updatable = false)
  @ManyToOne
  private Sucursal sucursal;

  @Column(name = "Id_Sucursal")
  private Integer idSucursal;

  @Column(name = "Activo")
  @NotNull
  private Integer activo;

  public boolean isActivo(){
    return activo != null && activo == 1;
  }

}
