package com.jdreyes.webapi.prestamos.model.dao;

import com.jdreyes.persistence.BaseDAO;
import com.jdreyes.webapi.prestamos.model.entities.Plazo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface PlazoRepository extends JpaRepository<Plazo, BigDecimal> {}
