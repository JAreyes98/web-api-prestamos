package com.jdreyes.webapi.prestamos.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "plazo_meses")
public class Plazo implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "Plazo")
  private BigDecimal plazo;
}
