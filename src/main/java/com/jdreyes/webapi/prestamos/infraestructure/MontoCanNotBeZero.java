package com.jdreyes.webapi.prestamos.infraestructure;

public class MontoCanNotBeZero extends BusinessException{
    public MontoCanNotBeZero() {
        this("El monto no puede ser menor o igual a zero");
    }

    public MontoCanNotBeZero(String message) {
        super(message);
    }

    public MontoCanNotBeZero(String message, Throwable cause) {
        super(message, cause);
    }

    public MontoCanNotBeZero(Throwable cause) {
        super(cause);
    }

    public MontoCanNotBeZero(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
