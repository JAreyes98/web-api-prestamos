package com.jdreyes.webapi.prestamos.service;

import com.jdreyes.webapi.prestamos.model.dto.MacRequest;
import com.jdreyes.webapi.prestamos.model.entities.AppUser;
import com.jdreyes.webapi.prestamos.model.entities.Device;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<AppUser> findUser(String username) ;
    Optional<Device> buscarDispositivo(MacRequest macRequest);
    List<Device> buscarDispositivo(Integer id);
    Device saveDevice(MacRequest mac);
}
