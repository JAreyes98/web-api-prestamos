package com.jdreyes.webapi.prestamos.service.utils;

import com.jdreyes.webapi.prestamos.service.dtos.FuncionarioDto;
import com.jdreyes.webapi.prestamos.service.dtos.security.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.jdreyes.webapi.prestamos.service.utils.criptography.CryptoCipher;

@Component
public class ContextUtils {

    private static  ApplicationContext context;

    @Autowired
    public ContextUtils(ApplicationContext context) {
        this.context = context;
    }

    public static ApplicationContext getContext(){
        return ContextUtils.context;
    }

    public static PasswordEncoder encoder() {
        return getContext().getBean(PasswordEncoder.class);
    }

    public static CryptoCipher cryptoCipher() {
        return getContext().getBean(CryptoCipher.class);
    }

    public static FuncionarioDto getCurrentUser(){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth instanceof AnonymousAuthenticationToken) {
            throw new SecurityException("No se permite acceder a la persistencia al usuario anónimo");
        }
        var userAuth = (UsernamePasswordAuthenticationToken) auth;
        return (FuncionarioDto) (userAuth.getPrincipal());
    }
}
