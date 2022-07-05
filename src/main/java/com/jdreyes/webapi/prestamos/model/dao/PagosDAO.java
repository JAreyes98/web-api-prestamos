package com.jdreyes.webapi.prestamos.model.dao;

import com.jdreyes.webapi.prestamos.model.dto.AbonosFuncionarioDto;
import com.jdreyes.webapi.prestamos.model.dto.CobroPrestamoDto;

import java.math.BigDecimal;
import java.util.List;

/**
 * Definición del acceso a datos de los pagos.
 *
 * @since 1.0, 11/06/2022
 * @author Josue Reyes
 * @version 1.0
 */
public interface PagosDAO {
  /**
   * Obtiene los cobros del dia.
   *
   * @param idFuncionario identificador del funcionario.
   * @return cobros que debe realizar el funcionario durante el dia en curso.
   */
  List<CobroPrestamoDto> getCobrosDia(Integer idFuncionario);

  /**
   * Graba un pago relacionado a una cuota del prestamo {@code idPrestamo}.
   *
   * @param idPrestamo prestamos
   * @param monto monto pagado
   * @return bandera que indica si se grabo el resibo.
   */
  boolean grabarReciboCaja(Integer idPrestamo, BigDecimal monto);

  /**
   * Graba deposito bancario.
   *
   * @param idFuncinario indentificador del funcionario que realiza el deposito.
   * @param noCuenta cuanta a la que se deposita.
   * @param noMinuta no refencia/minuta
   * @param monto monto depositado.
   * @return bandera que indica si se grabo el deposito.
   */
  boolean grabarDeposito(Integer idFuncinario, String noCuenta, Integer noMinuta, BigDecimal monto);

  List<AbonosFuncionarioDto> getCobrosDia(Integer idFuncionario, String fechaIni, String fechaFin);
}
