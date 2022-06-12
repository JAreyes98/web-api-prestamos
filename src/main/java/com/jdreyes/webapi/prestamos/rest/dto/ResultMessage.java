package com.jdreyes.webapi.prestamos.rest.dto;

import lombok.Getter;

@Getter
public class ResultMessage {
    private final String status ;

    public ResultMessage(String status) {
        this.status = status;
    }
}
