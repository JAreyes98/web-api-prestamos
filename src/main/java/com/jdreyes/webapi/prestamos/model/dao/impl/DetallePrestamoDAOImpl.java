package com.jdreyes.webapi.prestamos.model.dao.impl;

import com.jdreyes.persistence.impl.BaseDAOImpl;
import com.jdreyes.webapi.prestamos.model.dao.DetallePrestamoDAO;
import com.jdreyes.webapi.prestamos.model.entities.DetallePrestamo;
import com.jdreyes.webapi.prestamos.model.entities.DetallePrestamoId;
import org.springframework.stereotype.Repository;

@Repository
public class DetallePrestamoDAOImpl extends BaseDAOImpl<DetallePrestamo, DetallePrestamoId> implements DetallePrestamoDAO {
}
