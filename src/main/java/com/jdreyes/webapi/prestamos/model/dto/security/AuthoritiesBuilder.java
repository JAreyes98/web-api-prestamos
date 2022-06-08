package com.jdreyes.webapi.prestamos.model.dto.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class AuthoritiesBuilder {
  //  public static final GrantedAuthority READER = () -> "READER USER";
  //  public static final GrantedAuthority WRITTER = () -> "WRITER USER";
  //  public static final GrantedAuthority REPORTER = () -> "REPORT LAUCHER USER";
  //  public static final GrantedAuthority PASSWORD_RECOVERY = () -> "PASSWORD RECOVERY USER";
  //  public static final GrantedAuthority ANALYZE = () -> "ANALYZE USER";

  public static Set<GrantedAuthority> GENERAL_USER =
      Set.of(Authority.READER.getAuth(), Authority.WRITTER.getAuth(), Authority.REPORTER.getAuth());

  public static Set<GrantedAuthority> ADMIN_USER =
      Stream.concat(
              Set.of(Authority.PASSWORD_RECOVERY.getAuth(), Authority.ANALYZE.getAuth()).stream(),
              GENERAL_USER.stream())
          .collect(Collectors.toSet());

  public static Collection<Authority> of(Integer id) {
    Objects.requireNonNull(id, "No se ha especificado un role.\nEl role no puede ser null");
    return Arrays.stream(Authority.values()).filter(f -> f.id == id).collect(Collectors.toList());
  }

  @Getter
  public static enum Authority {
    READER(1, () -> "READER USER"),
    WRITTER(2, () -> "WRITER USER"),
    REPORTER(3, () -> "REPORT LAUCHER USER"),
    PASSWORD_RECOVERY(4, () -> "PASSWORD RECOVERY USER"),
    ANALYZE(5, () -> "ANALYZE USER");

    private final Integer id;
    private final GrantedAuthority auth;

    Authority(Integer id, GrantedAuthority auth) {
      this.id = id;
      this.auth = auth;
    }
  }
}
