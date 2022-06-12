package com.jdreyes.webapi.prestamos.infraestructure;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CobrosDiaNotFoundException extends RuntimeException {
  public CobrosDiaNotFoundException() {
    this(
        String.format(
            "No se encontraron cobros para el dia %s",
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
  }

  public CobrosDiaNotFoundException(String message) {
    super(message);
  }

  public CobrosDiaNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public CobrosDiaNotFoundException(Throwable cause) {
    super(cause);
  }

  public CobrosDiaNotFoundException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
