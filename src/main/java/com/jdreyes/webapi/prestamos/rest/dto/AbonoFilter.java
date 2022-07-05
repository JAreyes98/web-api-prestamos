package com.jdreyes.webapi.prestamos.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AbonoFilter {
    private String fechaIni;
    private String fechaFin;
}
