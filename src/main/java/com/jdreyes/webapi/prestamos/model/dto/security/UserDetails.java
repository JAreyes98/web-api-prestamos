package com.jdreyes.webapi.prestamos.model.dto.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;

@Data
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

  private Integer userId;
  private String username;
  private String password;
  private String nombres;
  private Integer idFuncionario;
  private Integer idSucursal;
  private Integer idCia;
  private BigDecimal montoAsignado;
  private BigDecimal montoCartera;
  private BigDecimal montoRecuperado;
  private BigDecimal gastos;
  private BigDecimal saldoNeto;
  private BigDecimal depositos;
  private Collection<GrantedAuthority> authorities;
  private boolean enabled;

  public UserDetails(
      Integer userId,
      String username,
      String password,
      String nombres,
      Integer idFuncionario,
      Integer idSucursal,
      Integer idCia,
      BigDecimal montoAsignado,
      BigDecimal montoCartera,
      BigDecimal montoRecuperado,
      BigDecimal gastos,
      BigDecimal saldoNeto,
      BigDecimal depositos,
      Collection<GrantedAuthority> authorities,
      boolean enabled) {
    this.userId = userId;
    this.username = username;
    this.password = password;
    this.nombres = nombres;
    this.idFuncionario = idFuncionario;
    this.idSucursal = idSucursal;
    this.idCia = idCia;
    this.montoAsignado = montoAsignado;
    this.montoCartera = montoCartera;
    this.montoRecuperado = montoRecuperado;
    this.gastos = gastos;
    this.saldoNeto = saldoNeto;
    this.depositos = depositos;
    this.authorities = authorities;
    this.enabled = enabled;
  }

  @Override
  public boolean isAccountNonExpired() {
    return enabled;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !authorities.isEmpty();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }
}
