package com.jdreyes.webapi.prestamos.infraestructure;

public class InvalidDeviceException extends RuntimeException{
    public InvalidDeviceException() {
    }

    public InvalidDeviceException(String message) {
        super(message);
    }

    public InvalidDeviceException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDeviceException(Throwable cause) {
        super(cause);
    }

    public InvalidDeviceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
