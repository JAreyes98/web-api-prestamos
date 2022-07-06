package com.jdreyes.webapi.prestamos.model.dao.impl;

import com.jdreyes.persistence.impl.BaseQueryExecutor;
import com.jdreyes.webapi.prestamos.model.dao.PrestamoDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.ParameterMode;
import java.math.BigDecimal;

@Repository
public class PrestamoDAOImpl extends BaseQueryExecutor implements PrestamoDAO {

    @Override
    public void save(Integer idCLiente, String fechaApertura, BigDecimal monto, Integer tasaInteres, BigDecimal plazo, String codigo, int... dias) {
        createStoredProcedureQuery("Sp_Graba_Prestamos")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, BigDecimal.class, ParameterMode.IN)
                .registerStoredProcedureParameter(5, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(6, BigDecimal.class, ParameterMode.IN)
                .registerStoredProcedureParameter(7, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(8, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(9, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(10, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(11, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(12, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(13, String.class, ParameterMode.IN)
                .setParameter(1, 0)
                .setParameter(2, idCLiente)
                .setParameter(3, fechaApertura)
                .setParameter(4, monto)
                .setParameter(5, tasaInteres)
                .setParameter(6, plazo)
                .setParameter(7, dias[0])
                .setParameter(8,  dias[1])
                .setParameter(9,  dias[2])
                .setParameter(10,  dias[3])
                .setParameter(11,  dias[4])
                .setParameter(12,  dias[5])
                .setParameter(13,  codigo)
                .execute();
    }
}
