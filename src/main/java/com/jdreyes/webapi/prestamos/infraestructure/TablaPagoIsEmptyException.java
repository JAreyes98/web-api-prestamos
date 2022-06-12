package com.jdreyes.webapi.prestamos.infraestructure;

public class TablaPagoIsEmptyException extends BusinessException {

    public TablaPagoIsEmptyException() {
        this("No se encontraron la tabla de pagos del prestamo");
    }

    public TablaPagoIsEmptyException(String message) {
        super(message);
    }

    public TablaPagoIsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public TablaPagoIsEmptyException(Throwable cause) {
        super(cause);
    }

    public TablaPagoIsEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
