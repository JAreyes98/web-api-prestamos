package com.jdreyes.webapi.prestamos.model.dto;

import com.jdreyes.webapi.prestamos.service.dtos.FuncionarioDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@SqlResultSetMapping(
    name = "abonosFuncionarioDto",
    entities =
        @EntityResult(
            entityClass = AbonosFuncionarioDto.class,
            fields = {
              @FieldResult(name = "idGestor", column = "id_gestor"),
              @FieldResult(name = "nombres", column = "Nombre"),
              @FieldResult(name = "codigo", column = "codigo"),
              @FieldResult(name = "fecha", column = "fecha"),
              @FieldResult(name = "idRuta", column = "id_Ruta"),
              @FieldResult(name = "nombreRuta", column = "Nombre_Ruta"),
              @FieldResult(name = "idCliente", column = "Id_Cliente"),
              @FieldResult(name = "nombreCliente", column = "nombreCliente"),
              @FieldResult(name = "monto", column = "monto")
            }))
public class AbonosFuncionarioDto implements Serializable {
  private static final long serialVersionUID = 1L;

  private Integer idGestor;
  private String nombres;
  @Getter @Setter private String codigo;
  @Getter @Setter private String fecha;
  private Integer idRuta;
  private String nombreRuta;
  @Id private Integer idCliente;
  private String nombreCliente;
  @Getter @Setter private BigDecimal monto;

  @Transient @Setter @Getter private RutaDto ruta;
  @Transient @Setter @Getter private BaseClienteDto cliente;
  @Transient @Setter @Getter private FuncionarioDto funcionario;



  @PostLoad
  private void init() {
    if (Objects.isNull(cliente)) {
      cliente = new BaseClienteDto();
      cliente.setId(idCliente);
      cliente.setNombres(nombreCliente);
      cliente.setDireccion("");
    }

    if (Objects.isNull(ruta)) {
      ruta = new RutaDto();
      ruta.setId(idRuta);
      ruta.setNombres(nombreRuta);
    }
//    return cliente;
  }


//  @PostLoad
//  private void initRuta() {
//
////    return ruta;
//  }
}
