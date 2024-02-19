package main.java.br.com.brunojs.dao;

import main.java.br.com.brunojs.dao.generic.GenericDAO;
import main.java.br.com.brunojs.dao.generic.GenericDB1DAO;
import main.java.br.com.brunojs.domain.Cliente;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDAO extends GenericDB1DAO<Cliente, Long> implements IClienteDAO<Cliente> {

    public ClienteDAO() {
        super(Cliente.class);
    }

}