package com.jdreyes.webapi.prestamos.service.impl;

import com.jdreyes.webapi.prestamos.infraestructure.CobrosDiaNotFoundException;
import com.jdreyes.webapi.prestamos.infraestructure.MontoCanNotBeZero;
import com.jdreyes.webapi.prestamos.infraestructure.TablaPagoIsEmptyException;
import com.jdreyes.webapi.prestamos.model.dao.DetallePrestamoDAO;
import com.jdreyes.webapi.prestamos.model.dao.PagosDAO;
import com.jdreyes.webapi.prestamos.model.dao.PlazoRepository;
import com.jdreyes.webapi.prestamos.model.dao.PrestamoDAO;
import com.jdreyes.webapi.prestamos.model.dto.*;
import com.jdreyes.webapi.prestamos.model.entities.DetallePrestamo;
import com.jdreyes.webapi.prestamos.model.entities.Plazo;
import com.jdreyes.webapi.prestamos.rest.dto.PrestamoRequestDto;
import com.jdreyes.webapi.prestamos.service.PrestamosService;
import com.jdreyes.webapi.prestamos.service.dtos.AbonosDto;
import com.jdreyes.webapi.prestamos.service.dtos.DepositoBancoDto;
import com.jdreyes.webapi.prestamos.service.dtos.FuncionarioDto;
import com.jdreyes.webapi.prestamos.service.dtos.ReciboCajaDto;
import com.jdreyes.webapi.prestamos.service.utils.AppProperties;
import com.jdreyes.webapi.prestamos.service.utils.ContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
  private final PrestamoDAO prestamoDAO;
  private final PlazoRepository plazoRepository;

  /** Acceso a datos de prestamo. */
  private final DetallePrestamoDAO detallePrestamoDAO;

  @Autowired
  public PrestamosServiceImpl(PagosDAO pagosDAO, PrestamoDAO prestamoDAO, PlazoRepository plazoRepository, DetallePrestamoDAO detallePrestamoDAO) {
    this.pagosDAO = pagosDAO;
    this.prestamoDAO = prestamoDAO;
    this.plazoRepository = plazoRepository;
    this.detallePrestamoDAO = detallePrestamoDAO;
  }

  @Override
  public void save(PrestamoRequestDto prestamo) {
    Objects.requireNonNull(prestamo.getCodigoPrestamo(), "Codigo requerido");
    Objects.requireNonNull(prestamo.getMontoPrestamo(), "Monto prestamo requerido");
    Objects.requireNonNull(prestamo.getDiasPago(), "Se deben especificar los dias de pago");
    Objects.requireNonNull(prestamo.getPlazoDias(), "Se debe especificar el plazo");
    Objects.requireNonNull(prestamo.getIdCliente(), "Se debe el cliente");

    String fechaApertura = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    Integer interes = ContextUtils.getContext().getBean(AppProperties.class).getInteresrate();
    prestamoDAO.save(prestamo.getIdCliente(),fechaApertura, prestamo.getMontoPrestamo(),interes,prestamo.getPlazoDias(),prestamo.getCodigoPrestamo(), buildDaysArray(prestamo.getDiasPago()));

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

  @Override
  public AbonosDto getAbonosFuncionarios(Integer idFuncionario, String fechaIni, String fechaFin) {
    Objects.requireNonNull(idFuncionario, "Se requiere un funcionario");
    Objects.requireNonNull(fechaIni, "Se debe especificar la fecha de inicio");
    Objects.requireNonNull(fechaFin, "Se debe especificar la fecha de fin");
    List<AbonosFuncionarioDto> abonots = pagosDAO.getCobrosDia(idFuncionario, fechaIni, fechaFin);

    Function<AbonosFuncionarioDto, LocalDate> parse =
        s1 -> LocalDate.parse(s1.getFecha(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    final List<AbonosFuncionarioDto> finalAbonos = new ArrayList<>();
    abonots.stream()
        .collect(Collectors.groupingBy(AbonosFuncionarioDto::getCliente))
        .forEach(
            (key, value) -> {
              value.stream()
                  .max(Comparator.comparing(parse))
                  .ifPresent(
                      a -> {
                        AbonosFuncionarioDto abono = new AbonosFuncionarioDto();
                        abono.setFuncionario(a.getFuncionario());
                        abono.setCliente(key);
                        abono.setCodigo(a.getCodigo());
                        abono.setFecha(a.getFecha());
                        abono.setMonto(
                            BigDecimal.valueOf(
                                value.stream()
                                    .mapToDouble(
                                        v ->
                                            (Objects.nonNull(v.getMonto())
                                                    ? v.getMonto()
                                                    : BigDecimal.ZERO)
                                                .doubleValue())
                                    .sum()));
                        abono.setRuta(a.getRuta());
                        finalAbonos.add(abono);
                      });
            });

    finalAbonos.sort(Comparator.comparing(parse).reversed());
    BigDecimal total = BigDecimal.valueOf(finalAbonos.stream().mapToDouble(a -> (Objects.nonNull(a.getMonto()) ? a.getMonto() : BigDecimal.ZERO).doubleValue()).sum());
    return new AbonosDto(finalAbonos, total);
  }

  @Override
  public List<Plazo> getPlazos() {
    return plazoRepository.findAll();
  }

  private int[] buildDaysArray(List<DayOfWeek> dias) {
    return Stream.of(DayOfWeek.values()).sorted().mapToInt(i -> dias.contains(i) ? 1 : 0).toArray();
  }
}
