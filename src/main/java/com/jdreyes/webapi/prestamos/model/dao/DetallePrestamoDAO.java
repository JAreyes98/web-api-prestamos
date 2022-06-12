package com.jdreyes.webapi.prestamos.model.dao;

import com.jdreyes.persistence.BaseDAO;
import com.jdreyes.webapi.prestamos.model.entities.DetallePrestamo;
import com.jdreyes.webapi.prestamos.model.entities.DetallePrestamoId;

import java.util.List;

public interface DetallePrestamoDAO extends BaseDAO<DetallePrestamo, DetallePrestamoId> {
List<DetallePrestamo> getTablaPlagos(Integer idPrestamo);
}
