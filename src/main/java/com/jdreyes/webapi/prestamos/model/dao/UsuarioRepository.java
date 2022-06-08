package com.jdreyes.webapi.prestamos.model.dao;

import com.jdreyes.persistence.BaseDAO;
import com.jdreyes.webapi.prestamos.model.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends BaseDAO<AppUser, Integer> {
    Optional<AppUser> findAppUserByUserName(String username) ;
    Optional<AppUser> findAppUserByIdUsuario(Integer idUsuario);
    Optional<AppUser> login(Integer cia, String user, String password) ;
    Optional<AppUser> findUser(String user) ;
}
