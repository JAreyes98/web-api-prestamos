package com.jdreyes.webapi.prestamos.rest;

import com.jdreyes.webapi.prestamos.model.dto.CobrosDia;
import com.jdreyes.webapi.prestamos.model.dto.DetPrestamoDto;
import com.jdreyes.webapi.prestamos.model.dto.PrestamoDto;
import com.jdreyes.webapi.prestamos.model.dto.ResponseBaseFactory;
import com.jdreyes.webapi.prestamos.rest.dto.ResultMessage;
import com.jdreyes.webapi.prestamos.service.PrestamosService;
import com.jdreyes.webapi.prestamos.service.dtos.DepositoBancoDto;
import com.jdreyes.webapi.prestamos.service.dtos.FuncionarioDto;
import com.jdreyes.webapi.prestamos.service.dtos.ReciboCajaDto;
import com.jdreyes.webapi.prestamos.service.utils.ContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * API rest para control de prestamos.
 * @since 1.0
 * @author Josue Reyes
 * @version 1.0
 * @see PrestamosService
 */
@RestController
@RequestMapping("/api/prestamos/")
@Slf4j
public class PrestamosController {

  /**
   * Capa de Servicio de prestamos.
   */
  private final PrestamosService prestamosService;

  @Autowired
  public PrestamosController(PrestamosService prestamosService) {
    this.prestamosService = prestamosService;
  }

  /**
   * Endpoint para obtener los cobros del dia al los cuales les debe dar seguimiento el funcionario.
   * @return Cobros dia envuelto.
   * @since 1.0
   */
  @GetMapping(path = "cobros/dia")
  public ResponseEntity<?> cobrosDia() {
    FuncionarioDto user = ContextUtils.getCurrentUser();
    try {
      CobrosDia cobrosDia = prestamosService.getCobrosDiaByIdFuncionario(user);
      log.info("[{}] obteniedo cobros del dia", user.getUsername());
      return ResponseBaseFactory.wrap(cobrosDia);
    } catch (Exception e) {
      log.error(
          String.format(
              "[%s] Error en \"obtener cobros del dia\" -> \"%s\"",
              user.getUsername(), getClass().getSimpleName()),
          e);
      return ResponseBaseFactory.wrap(null, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  /**
   * Endpoint para guardar un pago (recibo de caja).
   * @return EStatus ok.
   * @since 1.0
   */
  @PostMapping(path = "cliente/pagos/recibo/save")
  public ResponseEntity<?> reciboCaja(@RequestBody ReciboCajaDto reciboCaja) {
    FuncionarioDto user = ContextUtils.getCurrentUser();
    try {
      prestamosService.grabarReciboCaja(reciboCaja);
      log.info(
          "[{}] registrando recibo de caja, prestamo={}",
          user.getUsername(),
          reciboCaja.getIdPrestamo());
      return ResponseBaseFactory.wrap(new ResultMessage(HttpStatus.OK.getReasonPhrase()));
    } catch (Exception e) {
      log.error(
          String.format(
              "[%s] Error en \"grabarReciboCaja\" -> \"%s\"",
              user.getUsername(), getClass().getSimpleName()),
          e);
      return ResponseBaseFactory.wrap(null, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }


  /**
   * Endpoint para registrar un deposito a banco.
   * @return devuelve un estatus OK.
   * @since 1.0
   */
  @PostMapping(path = "banco/deposito/save")
  public ResponseEntity<?> depositoBanco(@RequestBody DepositoBancoDto depositoBanco) {
    FuncionarioDto user = ContextUtils.getCurrentUser();
    try {
      prestamosService.grabarDeposito(depositoBanco);
      log.info(
          "[{}] Registrando deposito de banco a cuenta {}",
          user.getUsername(),
          depositoBanco.getNoCuenta());
      return ResponseBaseFactory.wrap(new ResultMessage(HttpStatus.OK.getReasonPhrase()));
    } catch (Exception e) {
      log.error(
          String.format(
              "[%s] Error en \"depositoBanco\" -> \"%s\"",
              user.getUsername(), getClass().getSimpleName()),
          e);
      return ResponseBaseFactory.wrap(null, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }


  /**
   * Endpoint para obtener la tabla de pago relacionada con el prestamo {@code prestamoDto}
   * @return tablade pago.
   * @since 1.0
   */
  @GetMapping(path = "cliente/pagos/tablapago")
  public ResponseEntity<?> tablaPagos(PrestamoDto prestamoDto) {
    FuncionarioDto user = ContextUtils.getCurrentUser();
    try {
      DetPrestamoDto tablaPago = prestamosService.getTablaPago(prestamoDto);
      log.info(
          "[{}] Obteniendo la tabla de pago del prestamo {}",
          user.getUsername(),
          prestamoDto.getIdPrestamo());
      return ResponseBaseFactory.wrap(tablaPago);
    } catch (Exception e) {
      log.error(
          String.format(
              "[%s] Error en \"tablaPagos\" -> \"%s\"",
              user.getUsername(), getClass().getSimpleName()),
          e);
      return ResponseBaseFactory.wrap(null, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }
}
