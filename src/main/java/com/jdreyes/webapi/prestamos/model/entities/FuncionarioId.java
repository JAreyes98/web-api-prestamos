package com.jdreyes.webapi.prestamos.model.entities;


import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class FuncionarioId implements Serializable {
    private Integer companyId;
    private Integer idFuncionario;
    private Integer idSucursal;
}
