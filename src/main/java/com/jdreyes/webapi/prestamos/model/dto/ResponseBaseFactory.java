package com.jdreyes.webapi.prestamos.model.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseBaseFactory {

   private ResponseBaseFactory(){}

    public static <T> ResponseEntity<?> wrap(T toWrap, HttpStatus status, String message) {
        return ResponseEntity.status(status).body(new ResponseBase<>(toWrap, status, message));
    }

    public static <T> ResponseEntity<?> wrap(T toWrap, HttpStatus status) {
        return wrap(toWrap, status, status.toString());
    }

    public static <T> ResponseEntity<?> wrap(T toWrap) {
        return wrap(toWrap, HttpStatus.OK);
    }

    @Getter
    public static class ResponseBase <T>{
        private final T value;
        private final String message;
        private final HttpStatus httpStatus;

        private ResponseBase(T value, HttpStatus httpStatus, String message){
            this.value = value;
            this.message = message;
            this.httpStatus = httpStatus;
        }
    }
}
