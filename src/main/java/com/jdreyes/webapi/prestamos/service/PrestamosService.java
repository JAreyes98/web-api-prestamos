package com.jdreyes.webapi.prestamos.service;

import com.jdreyes.webapi.prestamos.model.dto.CobrosDia;
import com.jdreyes.webapi.prestamos.model.dto.DetPrestamoDto;
import com.jdreyes.webapi.prestamos.model.dto.PrestamoDto;
import com.jdreyes.webapi.prestamos.service.dtos.DepositoBancoDto;
import com.jdreyes.webapi.prestamos.service.dtos.FuncionarioDto;
import com.jdreyes.webapi.prestamos.service.dtos.ReciboCajaDto;

/**
 * Definición del servicio de préstamos.
 *
 * @since 1.0
 * @author Josue Reyes
 * @version 1.0
 */
public interface PrestamosService {

  /**
   * Obtiene los cobros del dia a los que les debe dar seguimiento el funcionario {@code
   * funcionario}.
   *
   * @param funcionario funcionario
   * @return cobros del dia.
   * @since 1.0
   */
  CobrosDia getCobrosDiaByIdFuncionario(FuncionarioDto funcionario);

  /**
   * Registra el pago en relación a un préstamo.
   *
   * @param reciboCaja recibo de caja a guardar
   * @return bandera que indica si guardo o no.
   * @since 1.0
   */
  boolean grabarReciboCaja(ReciboCajaDto reciboCaja);

  /**
   * Registra un deposito a un banco por su numero de cuenta contenido en {@link DepositoBancoDto#getNoCuenta()}.
   *
   * @param depositoBanco deposito a guardar.
   * @return bandera que indica si guardo o no.
   * @since 1.0
   */
  boolean grabarDeposito(DepositoBancoDto depositoBanco);

  /**
   * Obtiene la tabla de pago relacionada al oprestamo {@link PrestamoDto#getIdPrestamo()}.
   *
   * @param cobroPrestamo prestamo del que se desea la tabla de pagos.
   * @return objeto que recubre la programación de pagos.
   * @since 1.0
   */
  DetPrestamoDto getTablaPago(PrestamoDto cobroPrestamo);
}
