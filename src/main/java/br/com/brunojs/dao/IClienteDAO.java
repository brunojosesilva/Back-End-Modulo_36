package main.java.br.com.brunojs.dao;

import main.java.br.com.brunojs.dao.generic.IGenericDAO;
import main.java.br.com.brunojs.domain.Cliente;
import main.java.br.com.brunojs.domain.Persistente;

public interface IClienteDAO<T extends Persistente> extends IGenericDAO<T, Long>{

}
