package com.jdreyes.webapi.prestamos.service.dtos;

import com.jdreyes.webapi.prestamos.model.dto.AbonosFuncionarioDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AbonosDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<AbonosFuncionarioDto> abonos;
    private BigDecimal totalAbonado;
}
