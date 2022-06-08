package com.jdreyes.webapi.prestamos.model.dao.impl;

import com.jdreyes.persistence.impl.BaseDAOImpl;
import com.jdreyes.webapi.prestamos.model.dao.UsuarioRepository;
import com.jdreyes.webapi.prestamos.model.entities.AppUser;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.Optional;

@Repository
public class UsuarioRepositoryImpl extends BaseDAOImpl<AppUser, Integer> implements UsuarioRepository {

    @Override
    public Optional<AppUser> findAppUserByUserName(String username) {
        Query query =
                entityManager
                        .createQuery(
                                "select user from AppUser user "
                                        + "\n\t where "
                                        + "\n\t\t user.userName = :username "
                        )
                        .setParameter("username", username);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public Optional<AppUser> findAppUserByIdUsuario(Integer idUsuario) {
        return findById(idUsuario);
    }

    public Optional<AppUser> login(Integer cia, String user, String password) {
        Query query =
                entityManager
                        .createQuery(
                                "select user from AppUser user "
                                        + "\n\t where "
                                        + "\n\t\t user.userName = :username "
                                        + "\n\t\t and user.password = :password "
                        )
                        .setParameter("username", user)
                        .setParameter("password", password);
        return query.getResultList().stream().findFirst();
    }

    public Optional<AppUser> findUser(String user) {
        Query query =
                entityManager
                        .createQuery(
                                "select user from AppUser user "
                                        + "\n\t where "
                                        + "\n\t\t user.userName = :username "
                        )
                        .setParameter("username", user);
        return query.getResultList().stream().findFirst();
    }
}
