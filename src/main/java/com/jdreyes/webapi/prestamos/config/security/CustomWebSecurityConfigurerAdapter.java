package com.jdreyes.webapi.prestamos.config.security;

import com.jdreyes.webapi.prestamos.service.dtos.security.AuthoritiesBuilder;
import com.jdreyes.webapi.prestamos.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Slf4j
@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

  @Autowired private ApplicationContext applicationContext;

  @Autowired private CustomBasicAuthenticationEntryPoint authenticationEntryPoint;

  @Autowired private RequestFilter requestFilter;

  @Autowired private UserServiceImpl userService;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    //    PasswordEncoder encoder = new BCryptPasswordEncoder();
    //    auth.inMemoryAuthentication()
    //        .withUser("admin")
    //        .password(encoder.encode("{noop}password"))
    //        .roles("USER");

    auth.userDetailsService(userService);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    //Deshabilita la seguridad Cross Sites Request Forgery
    http.csrf().disable();

    //Auroriza todos los request desde cualquier path
    ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry =
        http.authorizeRequests();

    //El usuario que realice cualquier request debe tener cualquiera de las Authorities
    registry
        .anyRequest()
        .hasAnyAuthority(
                AuthoritiesBuilder.Authority.READER.getAuth().getAuthority(),
                AuthoritiesBuilder.Authority.WRITTER.getAuth().getAuthority());

    //Define el tipo de autenticacion (Basic HTTP Authentication)
    registry
        .and()
        .httpBasic()
        .authenticationEntryPoint(authenticationEntryPoint);

    //Agrega requestFilters
    http.addFilterAfter(requestFilter, BasicAuthenticationFilter.class);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
