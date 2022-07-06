package com.jdreyes.webapi.prestamos.service.impl;

import com.jdreyes.webapi.prestamos.model.dao.DeviceDAO;
import com.jdreyes.webapi.prestamos.model.dao.UsuarioRepository;
import com.jdreyes.webapi.prestamos.model.dto.MacRequest;
import com.jdreyes.webapi.prestamos.model.entities.AppUser;
import com.jdreyes.webapi.prestamos.model.entities.Device;
import com.jdreyes.webapi.prestamos.service.UserService;
import com.jdreyes.webapi.prestamos.service.dtos.FuncionarioDto;
import com.jdreyes.webapi.prestamos.service.dtos.security.AuthoritiesBuilder;
import com.jdreyes.webapi.prestamos.service.dtos.security.UserDetails;
import com.jdreyes.webapi.prestamos.service.utils.AppProperties;
import com.jdreyes.webapi.prestamos.service.utils.ContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserDetailsService, UserService {

  private final UsuarioRepository repository;
  private final DeviceDAO deviceDAO;

  @Autowired
  public UserServiceImpl(UsuarioRepository repository, DeviceDAO deviceDAO) {
    this.repository = repository;
    this.deviceDAO = deviceDAO;
  }

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    boolean cypher = ContextUtils.getContext().getBean(AppProperties.class).isCypherEnabled();
    log.info("[System] loggin usuario \"{}\" with cypher {}",
            s, cypher ? ContextUtils.cryptoCipher().getClass().getSimpleName() : "DISABLED");

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

  public Optional<Device> buscarDispositivo(MacRequest macRequest) {
    Objects.requireNonNull(macRequest, "Se debe especificar el objeto MAC");
    Objects.requireNonNull(
        macRequest.getMac(), "Se debe especificar la direccion mac del dispositivo");
    return Optional.ofNullable(deviceDAO.findByMacEquals(macRequest.getMac()));
  }

  @Override
  public List<Device> buscarDispositivo(Integer id) {
    return deviceDAO.findByIdUsuarioEquals(id);
  }

  @Override
  public Device saveDevice(MacRequest mac) {

    UserDetails user = ContextUtils.getCurrentUser();

    Device device = new Device();
    device.setAct(1);
    device.setIdUsuario(user.getUserId());
    device.setMac(mac.getMac());

    return deviceDAO.save(device);
  }
}
