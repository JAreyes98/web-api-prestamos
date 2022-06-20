package com.jdreyes.webapi.prestamos.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * DTO (Objeto de transferencia de datos) que contiene la información consolidada del prestamo. <br/>
 *
 * @author Josue Reyes
 * @version 1.0
 * @since 1.0, 2022-06-11
 */
@Entity
@SqlResultSetMapping(
    name = "cobrosDiaDtoMapper",
    entities =
        @EntityResult(
            entityClass = CobroPrestamoDto.class,
            fields = {
              @FieldResult(name = "idPrestamo", column = "id_Prestamos"),
              @FieldResult(name = "idRuta", column = "id_Ruta"),
              @FieldResult(name = "nombreRuta", column = "Nombre_Ruta"),
              @FieldResult(name = "idCliente", column = "id_Cliente"),
              @FieldResult(name = "nombre", column = "Nombre"),
              @FieldResult(name = "direccion", column = "Direccion"),
              @FieldResult(name = "vencido", column = "vencido")
            }))
public class CobroPrestamoDto extends PrestamoDto implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * Identificador.
   */
  @Getter @Setter @Id private Integer idPrestamo;
  private Integer idRuta;
  /**
   * Variable que contiene la info de la ruta.<br/>
   * No se expone, es utilizada solo para la transformación del formato sql a clase.
   */
  private String nombreRuta;

  /**
   * Variable que contiene el identificador del cliente.<br/>
   * No se expone, es utilizada solo para la transformación del formato sql a clase.
   */
  private Integer idCliente;

  /**
   * Variable que contiene el nombre del cliente.<br/>
   * No se expone, es utilizada solo para la transformación del formato sql a clase.
   */
  private String nombre;

  /**
   * Variable que contiene la dirección del cliente.<br/>
   * No se expone, es utilizada solo para la transformación del formato sql a clase.
   */
  private String direccion;

  /**
   * Variable que contiene la info de si el cobro esta vencido o no.
   */
  @Getter @Setter private BigDecimal vencido;

  /**
   * Contiene la informacion de la ruta.
   */
  @Setter @Transient private RutaDto ruta;
  /**
   * Contiene la informacion del cliente.
   */
  @Setter @Transient private ClienteDto cliente;

  /**
   * Obtiene la info de la ruta.
   * @return objeto con la info de la ruta.
   */
  public RutaDto getRuta() {
    if (Objects.isNull(ruta)) {
      ruta = new RutaDto();
      ruta.setId(idRuta);
      ruta.setNombres(nombreRuta);
    }
    return ruta;
  }

  /**
   * Obtiene la info del cliente.
   * @return objeto con la info del cliente.
   */
  public ClienteDto getCliente() {
    if (Objects.isNull(cliente)) {
      cliente = new ClienteDto();
      cliente.setId(idCliente);
      cliente.setNombres(nombre);
      cliente.setDireccion(direccion);
    }
    return cliente;
  }
}
