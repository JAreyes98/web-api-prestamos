package com.jdreyes.webapi.prestamos.model.dao.impl;

import com.jdreyes.persistence.impl.BaseDAOImpl;
import com.jdreyes.webapi.prestamos.model.dao.DetallePrestamoDAO;
import com.jdreyes.webapi.prestamos.model.entities.DetallePrestamo;
import com.jdreyes.webapi.prestamos.model.entities.DetallePrestamoId;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DetallePrestamoDAOImpl extends BaseDAOImpl<DetallePrestamo, DetallePrestamoId>
    implements DetallePrestamoDAO {
  @Override
  public List<DetallePrestamo> getTablaPlagos(Integer idPrestamo) {
    List<DetallePrestamo> list =
        entityManager
            .createQuery("select d from DetallePrestamo d where d.idPrestamo = :idPrestamo")
            .setParameter("idPrestamo", idPrestamo)
            .getResultList();
    return list;
  }
}
