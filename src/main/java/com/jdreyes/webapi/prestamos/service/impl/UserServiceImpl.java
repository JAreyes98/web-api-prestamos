package com.jdreyes.webapi.prestamos.service.impl;

import com.jdreyes.webapi.prestamos.model.dao.UsuarioRepository;
import com.jdreyes.webapi.prestamos.service.dtos.FuncionarioDto;
import com.jdreyes.webapi.prestamos.service.dtos.security.AuthoritiesBuilder;
import com.jdreyes.webapi.prestamos.service.dtos.security.UserDetails;
import com.jdreyes.webapi.prestamos.model.entities.AppUser;
import com.jdreyes.webapi.prestamos.service.UserService;
import com.jdreyes.webapi.prestamos.service.utils.AppProperties;
import com.jdreyes.webapi.prestamos.service.utils.ContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

  private final UsuarioRepository repository;

  @Autowired
  public UserServiceImpl(UsuarioRepository repository) {
    this.repository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    boolean cypher = ContextUtils.getContext().getBean(AppProperties.class).isCypherEnabled();

    Optional<AppUser> userOpt = repository.findUser(s);
    AppUser user = userOpt.orElseThrow(() -> new UsernameNotFoundException(s));

    // Decodifica la password del algoritmo de cifrado
    String password =
        cypher ? ContextUtils.cryptoCipher().decode(user.getPassword()) : user.getPassword();

    // Codifica la password para el contexto de Spring.
    password = ContextUtils.encoder().encode(password);

    return new FuncionarioDto(
        user.getIdUsuario(),
        user.getUserName(),
        password,
        user.getNombres(),
        user.getIdTipoFuncionario(),
        user.getFuncionario().getIdSucursal(),
        user.getFuncionario().getCompanyId(),
        user.getFuncionario().getMontoAsignado(),
        user.getFuncionario().getMontoCartera(),
        user.getFuncionario().getMontoRecuperado(),
        user.getFuncionario().getGastos(),
        user.getFuncionario().getSaldoNeto(),
        user.getFuncionario().getDepositos(),
        AuthoritiesBuilder.GENERAL_USER,
        user.isActivo());
  }

  @Override
  public Optional<AppUser> findUser(String username) {
    return repository.findUser(username);
  }
}
