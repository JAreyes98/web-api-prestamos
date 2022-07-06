package com.jdreyes.webapi.prestamos.rest;

import com.jdreyes.webapi.prestamos.model.dto.CobrosDia;
import com.jdreyes.webapi.prestamos.model.dto.DetPrestamoDto;
import com.jdreyes.webapi.prestamos.model.dto.PrestamoDto;
import com.jdreyes.webapi.prestamos.model.dto.ResponseBaseFactory;
import com.jdreyes.webapi.prestamos.model.entities.Plazo;
import com.jdreyes.webapi.prestamos.rest.dto.AbonoFilter;
import com.jdreyes.webapi.prestamos.rest.dto.PrestamoRequestDto;
import com.jdreyes.webapi.prestamos.rest.dto.ResultMessage;
import com.jdreyes.webapi.prestamos.service.PrestamosService;
import com.jdreyes.webapi.prestamos.service.dtos.AbonosDto;
import com.jdreyes.webapi.prestamos.service.dtos.DepositoBancoDto;
import com.jdreyes.webapi.prestamos.service.dtos.FuncionarioDto;
import com.jdreyes.webapi.prestamos.service.dtos.ReciboCajaDto;
import com.jdreyes.webapi.prestamos.service.utils.ContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * API rest para control de prestamos.
 *
 * @since 1.0
 * @author Josue Reyes
 * @version 1.0
 * @see PrestamosService
 */
@RestController
@RequestMapping("/api/prestamos/")
@Slf4j
public class PrestamosController {

  /** Capa de Servicio de prestamos. */
  private final PrestamosService prestamosService;

  @Autowired
  public PrestamosController(PrestamosService prestamosService) {
    this.prestamosService = prestamosService;
  }

  /**
   * Endpoint para obtener los cobros del dia al los cuales les debe dar seguimiento el funcionario.
   *
   * @return Cobros dia envuelto.
   * @since 1.0
   */
  @GetMapping(path = "cobros/dia/v1")
  public ResponseEntity<?> cobrosDia() {
    FuncionarioDto user = ContextUtils.getCurrentUser();
    try {

      log.info("[{}] obteniedo cobros del dia", user.getUsername());
      CobrosDia cobrosDia = prestamosService.getCobrosDiaByIdFuncionario(user);
      log.info("[{}] cobros dias obtenidos {}", user.getUsername(), cobrosDia.getCobros().size());
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
   *
   * @return EStatus ok.
   * @since 1.0
   */
  @PostMapping(path = "cliente/pagos/recibo/save/v1")
  public ResponseEntity<?> reciboCaja(@RequestBody ReciboCajaDto reciboCaja) {
    FuncionarioDto user = ContextUtils.getCurrentUser();
    try {
      log.info(
              "[{}] registrando recibo de caja, prestamo={}",
              user.getUsername(),
              reciboCaja.getIdPrestamo());
      prestamosService.grabarReciboCaja(reciboCaja);
      log.info(
              "[{}] recibo de caja registrado, prestamo={}",
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
   *
   * @return devuelve un estatus OK.
   * @since 1.0
   */
  @PostMapping(path = "banco/deposito/save/v1")
  public ResponseEntity<?> depositoBanco(@RequestBody DepositoBancoDto depositoBanco) {
    FuncionarioDto user = ContextUtils.getCurrentUser();
    try {
      log.info(
              "[{}] Registrando deposito de banco a cuenta {}",
              user.getUsername(),
              depositoBanco.getNoCuenta());
      prestamosService.grabarDeposito(depositoBanco);
      log.info(
          "[{}] Deposito de banco a cuenta {} realizado",
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
   *
   * @return tablade pago.
   * @since 1.0
   */
  @GetMapping(path = "cliente/pagos/tablapago/v1")
  public ResponseEntity<?> tablaPagos(@RequestBody PrestamoDto prestamoDto) {
    FuncionarioDto user = ContextUtils.getCurrentUser();
    try {
      log.info(
              "[{}] Obteniendo la tabla de pago del prestamo {}",
              user.getUsername(),
              prestamoDto.getIdPrestamo());
      DetPrestamoDto tablaPago = prestamosService.getTablaPago(prestamoDto);
      log.info(
          "[{}] Pagos obtenidos {}",
          user.getUsername(),
          tablaPago.getPrestamos().size());
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

  @PostMapping(path = "cliente/pagos/dia/v1")
  public ResponseEntity<?> abonos(@RequestBody AbonoFilter filter) {
    FuncionarioDto user = ContextUtils.getCurrentUser();
    try {
      log.info(
              "[{}] Obteniendo los abonos desde {} hasta {}",
              user.getUsername(),
              filter.getFechaIni(),
              filter.getFechaFin());
      //      String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("yyy-MM-dd"));
      AbonosDto abonosDto =
          prestamosService.getAbonosFuncionarios(
              user.getIdFuncionario(), filter.getFechaIni(), filter.getFechaFin());
      abonosDto
          .getAbonos()
          .forEach(
              c -> {
                user.setPassword(null);
                c.setFuncionario(user);
              });
      log.info(
          "[{}] Cobros obtenidos {}",
          user.getUsername(),
          abonosDto.getAbonos().size());
      return ResponseBaseFactory.wrap(abonosDto);
    } catch (Exception e) {
      log.error(
          String.format(
              "[%s] Error en \"abonos\" -> \"%s\"", user.getUsername(), getClass().getSimpleName()),
          e);
      return ResponseBaseFactory.wrap(null, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @GetMapping("plazos")
  public ResponseEntity<?> getPlazos() {
    FuncionarioDto user = ContextUtils.getCurrentUser();
    try {
      log.info("[{}] Obteniendo los plazos", user.getUsername());
      List<Plazo> plazos = prestamosService.getPlazos();
      log.info("[{}] Plazos obtenidos {}", user.getUsername(), plazos.size());
      return ResponseBaseFactory.wrap(plazos);
    } catch (Exception e) {
      log.error(
          String.format(
              "[%s] Error en \"plazos\" -> \"%s\"", user.getUsername(), getClass().getSimpleName()),
          e);
      return ResponseBaseFactory.wrap(null, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @PostMapping("save")
  public ResponseEntity<?> registra(@RequestBody PrestamoRequestDto prestamo) {
    FuncionarioDto user = ContextUtils.getCurrentUser();
    try {
      log.info("[{}] registrando prestamo ", user.getUsername());
      prestamosService.save(prestamo);
      log.info("[{}] Prestamo registrado", user.getUsername());
      return ResponseBaseFactory.wrap(null, HttpStatus.OK);
    } catch (Exception e) {
      log.error(
              String.format(
                      "[%s] Error en \"registro prestamo\" -> \"%s\"", user.getUsername(), getClass().getSimpleName()),
              e);
      return ResponseBaseFactory.wrap(null, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }
}
