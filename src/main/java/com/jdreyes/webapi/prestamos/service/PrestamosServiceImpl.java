package com.jdreyes.webapi.prestamos.service;

import com.jdreyes.webapi.prestamos.infraestructure.CobrosDiaNotFoundException;
import com.jdreyes.webapi.prestamos.infraestructure.MontoCanNotBeZero;
import com.jdreyes.webapi.prestamos.infraestructure.TablaPagoIsEmptyException;
import com.jdreyes.webapi.prestamos.model.dao.DetallePrestamoDAO;
import com.jdreyes.webapi.prestamos.model.dao.PagosDAO;
import com.jdreyes.webapi.prestamos.model.dto.CobroPrestamoDto;
import com.jdreyes.webapi.prestamos.model.dto.CobrosDia;
import com.jdreyes.webapi.prestamos.model.dto.DetPrestamoDto;
import com.jdreyes.webapi.prestamos.model.dto.PrestamoDto;
import com.jdreyes.webapi.prestamos.model.entities.DetallePrestamo;
import com.jdreyes.webapi.prestamos.service.dtos.DepositoBancoDto;
import com.jdreyes.webapi.prestamos.service.dtos.FuncionarioDto;
import com.jdreyes.webapi.prestamos.service.dtos.ReciboCajaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Implementación del servicio de préstamos.
 *
 * @since 1.0, 11/06/2022
 * @author Josue Reyes
 * @version 1.0
 * @see com.jdreyes.webapi.prestamos.service.PrestamosService
 * @see PagosDAO
 * @see DetallePrestamoDAO
 */
@Service
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
public class PrestamosServiceImpl implements PrestamosService {

  /** Acceso a datos de pagos. */
  private final PagosDAO pagosDAO;

  /** Acceso a datos de prestamo. */
  private final DetallePrestamoDAO detallePrestamoDAO;

  @Autowired
  public PrestamosServiceImpl(PagosDAO pagosDAO, DetallePrestamoDAO detallePrestamoDAO) {
    this.pagosDAO = pagosDAO;
    this.detallePrestamoDAO = detallePrestamoDAO;
  }

  /**
   * Obtiene los cobros del dia a los que les debe dar seguimiento el funcionario {@code
   * funcionario}.
   *
   * @param funcionario funcionario
   * @return cobros del dia.
   * @since 1.0
   */
  @Override
  public CobrosDia getCobrosDiaByIdFuncionario(FuncionarioDto funcionario) {
    Objects.requireNonNull(funcionario, "Se debe especificar el Funcionario");
    Objects.requireNonNull(funcionario.getIdFuncionario(), "Se debe especificar el id Funcionario");

    List<CobroPrestamoDto> cobros = pagosDAO.getCobrosDia(funcionario.getIdFuncionario());

    if (cobros.isEmpty()) {
      throw new CobrosDiaNotFoundException();
    }

    CobrosDia cobrosDia = new CobrosDia();
    cobrosDia.setCobros(cobros);
    return cobrosDia;
  }

  /**
   * Registra el pago en relación a un préstamo.
   *
   * @param reciboCaja recibo de caja a guardar
   * @return bandera que indica si guardo o no.
   * @since 1.0
   */
  @Override
  public boolean grabarReciboCaja(ReciboCajaDto reciboCaja) {
    Objects.requireNonNull(reciboCaja, "Se debe especificar el recibo");
    Objects.requireNonNull(reciboCaja.getFuncionario(), "Se debe especificar el funcionario");
    Objects.requireNonNull(
        reciboCaja.getFuncionario().getIdFuncionario(), "Se debe especificar el di funcionario");
    Objects.requireNonNull(reciboCaja.getIdPrestamo(), "Se debe especificar el no prestamo");
    Objects.requireNonNull(reciboCaja.getMonto(), "Se debe especificar el monto del pago");

    if (reciboCaja.getMonto().compareTo(BigDecimal.ZERO) <= 0) {
      throw new MontoCanNotBeZero();
    }

    return pagosDAO.grabarReciboCaja(reciboCaja.getIdPrestamo(), reciboCaja.getMonto());
  }

  /**
   * Registra un deposito a un banco por su numero de cuenta contenido en {@link
   * DepositoBancoDto#getNoCuenta()}.
   *
   * @param depositoBanco deposito a guardar.
   * @return bandera que indica si guardo o no.
   * @since 1.0
   */
  @Override
  public boolean grabarDeposito(DepositoBancoDto depositoBanco) {
    Objects.requireNonNull(depositoBanco, "Deposito Banco requerido");
    Objects.requireNonNull(depositoBanco.getFuncionario(), "Se debe especificar el funcionario");
    Objects.requireNonNull(depositoBanco.getNoCuenta(), "Se debe especificar el no de cuenta.");
    Objects.requireNonNull(depositoBanco.getMonto(), "Se debe especificar el monto");
    Objects.requireNonNull(depositoBanco.getNoMinuta(), "Se debe especificar el no referencia");

    return pagosDAO.grabarDeposito(
        depositoBanco.getFuncionario().getIdFuncionario(),
        depositoBanco.getNoCuenta(),
            depositoBanco.getNoMinuta(), depositoBanco.getMonto());
  }

  /**
   * Obtiene la tabla de pago relacionada al oprestamo {@link PrestamoDto#getIdPrestamo()}.
   *
   * @param cobroPrestamo prestamo del que se desea la tabla de pagos.
   * @return objeto que recubre la programación de pagos.
   * @since 1.0
   */
  @Override
  public DetPrestamoDto getTablaPago(PrestamoDto cobroPrestamo) {
    Objects.requireNonNull(cobroPrestamo, "Se debe especificar el Prestamo");
    Objects.requireNonNull(
        cobroPrestamo.getIdPrestamo(), "Se debe especificar el identificador del prestamos");
    List<DetallePrestamo> prestamos =
        detallePrestamoDAO.getTablaPlagos(cobroPrestamo.getIdPrestamo());

    if (prestamos.isEmpty()) {
      throw new TablaPagoIsEmptyException();
    }
    DetPrestamoDto detPrestamoDto = new DetPrestamoDto();
    detPrestamoDto.setIdPrestamo(cobroPrestamo.getIdPrestamo());
    detPrestamoDto.setPrestamos(prestamos);
    return detPrestamoDto;
  }
}
