package com.jdreyes.webapi.prestamos.rest;

import com.jdreyes.webapi.prestamos.infraestructure.InvalidDeviceException;
import com.jdreyes.webapi.prestamos.model.dto.MacRequest;
import com.jdreyes.webapi.prestamos.model.dto.ResponseBaseFactory;
import com.jdreyes.webapi.prestamos.model.entities.Device;
import com.jdreyes.webapi.prestamos.service.dtos.security.UserDetails;
import com.jdreyes.webapi.prestamos.service.impl.UserServiceImpl;
import com.jdreyes.webapi.prestamos.service.utils.ContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Clase que expone el api para realizar la autheticacion del usuario.
 *
 * @version 1.0
 * @since `1.0
 */
@RestController
@RequestMapping("/api/prestamos/auth/")
@Slf4j
public class AuthController {

    private final UserServiceImpl authService;
  //
    public AuthController(UserServiceImpl authService) {
      this.authService = authService;
    }

  @PostMapping(path = "login/v1")
  public ResponseEntity<?> loggedIn(@RequestBody MacRequest mac) {

    try {
      UserDetails user = ContextUtils.getCurrentUser();
      user.setPassword(null);
      Optional<Device> device = authService.buscarDispositivo(mac);

      if (!device.isPresent()) {
        if (authService.buscarDispositivo(user.getUserId()).stream().anyMatch(Device::isActivo)) {
          throw new InvalidDeviceException(String.format("El dispositivo con MAC %s no esta registrado, consulte con el administrador", mac.getMac()));
        }
        authService.saveDevice(mac);
      }else if(device.get().getIdUsuario() != user.getUserId()) {
        throw new IllegalAccessException(String.format("El dispositivo %s no pertenece al usuario %s. Contacte al administrador", mac.getMac(), user.getNombres()));
      }else if (!device.get().isActivo()){
        throw new IllegalAccessException(String.format("El dispositivo %s esta dado de baja. Contacte al administrador", mac.getMac()));
      }

      log.info("[{}] autenticado - Login v1", user.getUsername());
      return ResponseBaseFactory.wrap(user);
    } catch (Exception e) {
      log.error(String.format("Error en \"loggedIn\" -> \"%s\"", getClass().getSimpleName()), e);
      return ResponseBaseFactory.wrap(null, HttpStatus.FORBIDDEN, e.getMessage());
    }
  }
}
