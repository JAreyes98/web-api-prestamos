package com.jdreyes.webapi.prestamos.model.dao.impl;

import com.jdreyes.persistence.impl.BaseQueryExecutor;
import com.jdreyes.webapi.prestamos.model.dao.PagosDAO;
import com.jdreyes.webapi.prestamos.model.dto.CobroPrestamoDto;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
/**
 * Implementación del acceso a datos de los pagos.
 *
 * @since 1.0, 11/06/2022
 * @author Josue Reyes
 * @version 1.0
 * @see com.jdreyes.persistence.BaseQueryExcecutor
 * @see com.jdreyes.webapi.prestamos.model.dao.PagosDAO
 */
@Repository
public class PagosDAOImpl extends BaseQueryExecutor implements PagosDAO {

  /**
   * Obtiene los cobros del dia llamando al procedimiento Sp_Cobros_del_Dia y la data obtenida se
   * mapea hacia una entidad de tipo {@link CobroPrestamoDto}.
   *
   * @param idFuncionario identificador del funcionario.
   * @return cobros que debe realizar el funcionario durante el dia en curso.
   */
  @Override
  public List<CobroPrestamoDto> getCobrosDia(Integer idFuncionario) {
    return createStoredProcedureQuery("call Sp_Cobros_del_Dia :idFuncionario", "cobrosDiaDtoMapper")
        .setParameter("idFuncionario", idFuncionario)
        .getResultList();
  }

  /**
   * Graba un pago relacionado a una cuota del prestamo {@code idPrestamo}.
   *
   * @param idPrestamo prestamos
   * @param monto monto pagado
   * @return bandera que indica si se grabo el resibo.
   */
  @Override
  public boolean grabarReciboCaja(Integer idPrestamo, BigDecimal monto) {
    return createStoredProcedureQuery("call Sp_Graba_recibo_Caja :p_id_Prestamos, :P_mont")
            .setParameter("p_id_Prestamos", idPrestamo)
            .setParameter("P_mont", monto)
            .executeUpdate()
        > 0;
  }

  /**
   * Graba deposito bancario.
   *
   * @param idFuncinario indentificador del funcionario que realiza el deposito.
   * @param noCuenta cuanta a la que se deposita.
   * @param noMinuta numero de referencia.
   * @param monto monto depositado.
   * @return bandera que indica si se grabo el deposito.
   */
  @Override
  public boolean grabarDeposito(Integer idFuncinario, String noCuenta, Integer noMinuta, BigDecimal monto) {
    return createStoredProcedureQuery("call Sp_Graba_deposito :P_Id_Gestor, :P_Id_Cuenta_Bancaria, :p_Minuta :P_Monto")
            .setParameter("P_Id_Gestor", idFuncinario)
            .setParameter("P_Id_Cuenta_Bancaria", noCuenta)
            .setParameter("p_Minuta", noMinuta)
            .setParameter("P_Monto", monto)
            .executeUpdate()
            > 0;
  }
}
