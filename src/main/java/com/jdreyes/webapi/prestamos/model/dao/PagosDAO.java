package com.jdreyes.webapi.prestamos.model.dao;

import com.jdreyes.webapi.prestamos.model.dto.CobrosDiaDto;

import java.math.BigDecimal;

public interface PagosDAO {
    CobrosDiaDto getCobrosDia(Integer idFuncionario);
    boolean grabarReciboCaja(Integer idPrestamo, Integer idFuncionario);
    boolean grabarDeposito(Integer idFuncinario, String noCuenta, BigDecimal monto);
}
