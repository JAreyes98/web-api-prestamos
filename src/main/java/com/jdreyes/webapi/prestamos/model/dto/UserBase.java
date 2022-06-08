package com.jdreyes.webapi.prestamos.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class UserBase {
//    private Integer idCia;
    private String userName;
    private String password;

}
