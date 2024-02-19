package br.com.brunojs;

import main.java.br.com.brunojs.dao.ClienteDAO;
import main.java.br.com.brunojs.dao.IClienteDAO;
import main.java.br.com.brunojs.domain.Cliente;
import main.java.br.com.brunojs.exceptions.DAOException;
import main.java.br.com.brunojs.exceptions.MaisDeUmRegistroException;
import main.java.br.com.brunojs.exceptions.TableException;
import main.java.br.com.brunojs.exceptions.TipoChaveNaoEncontradaException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Random;

import static org.junit.Assert.assertTrue;

public class ClienteDAOTest {

    private IClienteDAO<Cliente> clienteDAO;
    private Random rd;

    public ClienteDAOTest(){
        this.clienteDAO = new ClienteDAO();
        rd = new Random();

    }

    @After
    public void end() throws DAOException {
        Collection<Cliente> list = clienteDAO.buscarTodos();
        list.forEach(cli -> {
            try {
                clienteDAO.excluir(cli);
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    @Test
    public void pesquisarCliente() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException {
        Cliente cliente = criarCliente();
        clienteDAO.cadastrar(cliente);

        Cliente clienteConsultado = clienteDAO.consultar(cliente.getId());
        Assert.assertNotNull(clienteConsultado);

    }

    @Test
    public void salvarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        Cliente cliente = criarCliente();
        Cliente retorno = clienteDAO.cadastrar(cliente);
        Assert.assertNotNull(retorno);

        Cliente clienteConsultado = clienteDAO.consultar(retorno.getId());
        Assert.assertNotNull(clienteConsultado);

        clienteDAO.excluir(cliente);

        Cliente clienteConsultado1 = clienteDAO.consultar(retorno.getId());
        Assert.assertNull(clienteConsultado1);
    }

    @Test
    public void excluirCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        Cliente cliente = criarCliente();
        Cliente retorno = clienteDAO.cadastrar(cliente);
        Assert.assertNotNull(retorno);

        Cliente clienteConsultado = clienteDAO.consultar(cliente.getId());
        Assert.assertNotNull(clienteConsultado);

        clienteDAO.excluir(cliente);
        clienteConsultado = clienteDAO.consultar(cliente.getId());
        Assert.assertNull(clienteConsultado);
    }

    @Test
    public void alterarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        Cliente cliente = criarCliente();
        Cliente retorno = clienteDAO.cadastrar(cliente);
        Assert.assertNotNull(retorno);

        Cliente clienteConsultado = clienteDAO.consultar(cliente.getId());
        Assert.assertNotNull(clienteConsultado);

        clienteConsultado.setNome("Rodrigo Pires");
        clienteDAO.alterar(clienteConsultado);

        Cliente clienteAlterado = clienteDAO.consultar(clienteConsultado.getId());
        Assert.assertNotNull(clienteAlterado);
        Assert.assertEquals("Rodrigo Pires", clienteAlterado.getNome());

        clienteDAO.excluir(cliente);
        clienteConsultado = clienteDAO.consultar(clienteAlterado.getId());
        Assert.assertNull(clienteConsultado);
    }
    @Test
    public void buscarTodos() throws TipoChaveNaoEncontradaException, DAOException {
        Cliente cliente = criarCliente();
        Cliente retorno = clienteDAO.cadastrar(cliente);
        Assert.assertNotNull(retorno);

        Cliente cliente1 = criarCliente();
        Cliente retorno1 = clienteDAO.cadastrar(cliente1);
        Assert.assertNotNull(retorno1);

        Collection<Cliente> list = clienteDAO.buscarTodos();
        assertTrue(list != null);
        assertTrue(list.size() == 2);

        list.forEach(cli -> {
            try {
                clienteDAO.excluir(cli);
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        Collection<Cliente> list1 = clienteDAO.buscarTodos();
        assertTrue(list1 != null);
        assertTrue(list1.size() == 0);
    }

    private Cliente criarCliente() {
        Cliente cliente = new Cliente();
        cliente.setCpf(789456123L);
        cliente.setNome("Renan Betereli");
        cliente.setCidade("Campinas");
        cliente.setEstado("SP");
        cliente.setEnd("Rua Teste");
        cliente.setNumero(123);
        cliente.setTel(741258963258L);
        return cliente;
    }
}
