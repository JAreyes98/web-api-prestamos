package com.jdreyes.webapi.prestamos.service;

import com.jdreyes.webapi.prestamos.model.entities.AppUser;

import java.util.Optional;

public interface UserService {
    Optional<AppUser> findUser(String username) ;
}
