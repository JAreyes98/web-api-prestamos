package com.jdreyes.webapi.prestamos.rest;

import com.jdreyes.webapi.prestamos.model.dto.ResponseBaseFactory;
import com.jdreyes.webapi.prestamos.service.dtos.security.UserDetails;
import com.jdreyes.webapi.prestamos.service.utils.ContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase que expone el api para realizar la autheticacion del usuario.
 *
 * @version 1.0
 * @since `1.0
 */
@RestController
@RequestMapping("/api/auth/")
@Slf4j
public class AuthController {

  //  private final UserServiceImpl authService;
  //
  //  public AuthController(UserServiceImpl authService) {
  //    this.authService = authService;
  //  }

  @PostMapping(path = "login/v1")
  public ResponseEntity<?> loggedIn() {

    try {
      UserDetails user = ContextUtils.getCurrentUser();
      user.setPassword(null);

      log.info("[{}] autenticado - Login v1", user.getUsername());
      return ResponseBaseFactory.wrap(user);
    } catch (Exception e) {
      log.error(String.format("Error en \"loggedIn\" -> \"%s\"", getClass().getSimpleName()), e);
      return ResponseBaseFactory.wrap(null, HttpStatus.FORBIDDEN, e.getMessage());
    }
  }
}
