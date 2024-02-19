package main.java.br.com.brunojs.dao;

import main.java.br.com.brunojs.dao.generic.GenericDAO;
import main.java.br.com.brunojs.dao.generic.GenericDB1DAO;
import main.java.br.com.brunojs.domain.Produto;

public class ProdutoDAO extends GenericDB1DAO<Produto, Long> implements IProdutoDAO {

    public ProdutoDAO() {
        super(Produto.class);
    }

}
