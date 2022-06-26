package com.jdreyes.webapi.prestamos.model.dao;

import com.jdreyes.webapi.prestamos.model.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceDAO extends JpaRepository<Device, String> {
    List<Device> findByIdUsuarioEquals(Integer idUsuario);
    Device findByMacEquals(String mac);
}
