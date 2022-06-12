package com.jdreyes.webapi.prestamos.service.dtos;

import com.jdreyes.webapi.prestamos.service.dtos.security.UserDetails;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.math.BigDecimal;
import java.util.Collection;

@Getter
@Setter
public class FuncionarioDto extends UserDetails {
    public FuncionarioDto(Integer userId, String username, String password, String nombres, Integer idFuncionario, Integer idSucursal, Integer idCia, BigDecimal montoAsignado, BigDecimal montoCartera, BigDecimal montoRecuperado, BigDecimal gastos, BigDecimal saldoNeto, BigDecimal depositos, Collection<GrantedAuthority> authorities, boolean enabled) {
        super(userId, username, password, nombres, idFuncionario, idSucursal, idCia, montoAsignado, montoCartera, montoRecuperado, gastos, saldoNeto, depositos, authorities, enabled);
    }
}
