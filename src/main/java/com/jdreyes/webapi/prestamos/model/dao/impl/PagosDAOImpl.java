package com.jdreyes.webapi.prestamos.model.dao.impl;

import com.jdreyes.persistence.impl.BaseQueryExecutor;
import com.jdreyes.webapi.prestamos.model.dao.PagosDAO;
import com.jdreyes.webapi.prestamos.model.dto.CobrosDiaDto;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class PagosDAOImpl extends BaseQueryExecutor implements PagosDAO {
    @Override
    public CobrosDiaDto getCobrosDia(Integer idFuncionario) {
        return null;
    }

    @Override
    public boolean grabarReciboCaja(Integer idPrestamo, Integer idFuncionario) {
        return false;
    }

    @Override
    public boolean grabarDeposito(Integer idFuncinario, String noCuenta, BigDecimal monto) {
        return false;
    }
}
