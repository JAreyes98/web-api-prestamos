package com.jdreyes.webapi.prestamos.model.dao;

import com.jdreyes.persistence.BaseDAO;
import com.jdreyes.persistence.BaseQueryExcecutor;

import java.math.BigDecimal;

public interface PrestamoDAO extends BaseQueryExcecutor {
    void save(Integer idCLiente, String fechaApertura, BigDecimal monto, Integer tasaInteres, Short plazo, String codigo, int ...dias);
}
