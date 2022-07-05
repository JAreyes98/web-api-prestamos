package com.jdreyes.webapi.prestamos.model.dao;

import com.jdreyes.persistence.BaseDAO;
import com.jdreyes.webapi.prestamos.model.entities.Plazo;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface PlazoRepository extends BaseDAO<Plazo, BigDecimal> {}
