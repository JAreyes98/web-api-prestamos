package com.jdreyes.webapi.prestamos.model.dao.impl;

import com.jdreyes.persistence.impl.BaseDAOImpl;
import com.jdreyes.webapi.prestamos.model.dao.FuncionarioDAO;
import com.jdreyes.webapi.prestamos.model.entities.Funcionario;
import com.jdreyes.webapi.prestamos.model.entities.FuncionarioId;
import org.springframework.stereotype.Repository;

@Repository
public class FuncionarioDAOImpl extends BaseDAOImpl<Funcionario, FuncionarioId> implements FuncionarioDAO {
}
