package br.com.brunojs;

import main.java.br.com.brunojs.dao.ClienteDAO;
import main.java.br.com.brunojs.dao.ClienteDB2DAO;
import main.java.br.com.brunojs.dao.ClienteDB3DAO;
import main.java.br.com.brunojs.dao.IClienteDAO;
import main.java.br.com.brunojs.domain.Cliente;
import main.java.br.com.brunojs.domain.Cliente2;
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

public class ClienteDAO3DBTest {

    private IClienteDAO<Cliente> clienteData1;
    private IClienteDAO<Cliente> clienteData2;
    private IClienteDAO<Cliente2> clienteData3;
    private Random rd;

    public ClienteDAO3DBTest() {
        this.clienteData1= new ClienteDAO();
        this.clienteData2 = (IClienteDAO<Cliente>) new ClienteDB2DAO();
        this.clienteData3 = (IClienteDAO<Cliente2>) new ClienteDB3DAO();
        rd = new Random();
    }

    @After
    public void end() throws DAOException {
        Collection<Cliente> list = clienteData1.buscarTodos();
        excluir(list, clienteData1);

        Collection<Cliente> list2 = clienteData2.buscarTodos();
        excluir(list2, clienteData2);

        Collection<Cliente2> list3 = clienteData3.buscarTodos();
        excluir3(list3);
    }

    private void excluir(Collection<Cliente> list, IClienteDAO<Cliente> clienteDao) {
        list.forEach(cli -> {
            try {
                clienteDao.excluir(cli);
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    private void excluir3(Collection<Cliente2> list) {
        list.forEach(cli -> {
            try {
                clienteData3.excluir(cli);
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    @Test
    public void pesquisarCliente() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException {
        Cliente cliente = criarCliente();
        clienteData1.cadastrar(cliente);

        Cliente clienteConsultado = clienteData1.consultar(cliente.getId());
        Assert.assertNotNull(clienteConsultado);

        cliente.setId(null);
        clienteData2.cadastrar(cliente);

        Cliente clienteConsultado2 = clienteData2.consultar(cliente.getId());
        Assert.assertNotNull(clienteConsultado2);

        Cliente2 cliente2 = criarCliente2();
        clienteData3.cadastrar(cliente2);

        Cliente2 clienteConsultado3 = clienteData3.consultar(cliente2.getId());
        Assert.assertNotNull(clienteConsultado3);

    }

    @Test
    public void salvarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        Cliente cliente = criarCliente();
        Cliente retorno = clienteData1.cadastrar(cliente);
        Assert.assertNotNull(retorno);

        Cliente clienteConsultado = clienteData1.consultar(retorno.getId());
        Assert.assertNotNull(clienteConsultado);

        clienteData1.excluir(cliente);

        Cliente clienteConsultado1 = clienteData1.consultar(retorno.getId());
        Assert.assertNull(clienteConsultado1);
    }

    @Test
    public void excluirCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        Cliente cliente = criarCliente();
        Cliente retorno = clienteData1.cadastrar(cliente);
        Assert.assertNotNull(retorno);

        Cliente clienteConsultado = clienteData1.consultar(cliente.getId());
        Assert.assertNotNull(clienteConsultado);

        clienteData1.excluir(cliente);
        clienteConsultado = clienteData1.consultar(cliente.getId());
        Assert.assertNull(clienteConsultado);
    }

    @Test
    public void alterarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        Cliente cliente = criarCliente();
        Cliente retorno = clienteData1.cadastrar(cliente);
        Assert.assertNotNull(retorno);

        Cliente clienteConsultado = clienteData1.consultar(cliente.getId());
        Assert.assertNotNull(clienteConsultado);

        clienteConsultado.setNome("Rodrigo Pires");
        clienteData1.alterar(clienteConsultado);

        Cliente clienteAlterado = clienteData1.consultar(clienteConsultado.getId());
        Assert.assertNotNull(clienteAlterado);
        Assert.assertEquals("Rodrigo Pires", clienteAlterado.getNome());

        clienteData1.excluir(cliente);
        clienteConsultado = clienteData1.consultar(clienteAlterado.getId());
        Assert.assertNull(clienteConsultado);
    }

    @Test
    public void buscarTodos() throws TipoChaveNaoEncontradaException, DAOException {
        Cliente cliente = criarCliente();
        Cliente retorno = clienteData1.cadastrar(cliente);
        Assert.assertNotNull(retorno);

        Cliente cliente1 = criarCliente();
        Cliente retorno1 = clienteData1.cadastrar(cliente1);
        Assert.assertNotNull(retorno1);

        Collection<Cliente> list = clienteData1.buscarTodos();
        assertTrue(list != null);
        assertTrue(list.size() == 2);

        list.forEach(cli -> {
            try {
                clienteData1.excluir(cli);
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        Collection<Cliente> list1 = clienteData1.buscarTodos();
        assertTrue(list1 != null);
        assertTrue(list1.size() == 0);
    }

    private Cliente criarCliente() {
        Cliente cliente = new Cliente();
        cliente.setCpf(rd.nextLong());
        cliente.setNome("Rodrigo");
        cliente.setCidade("São Paulo");
        cliente.setEnd("End");
        cliente.setEstado("SP");
        cliente.setNumero(10);
        cliente.setTel(1199999999L);
        return cliente;
    }

    private Cliente2 criarCliente2() {
        Cliente2 cliente = new Cliente2();
        cliente.setCpf(rd.nextLong());
        cliente.setNome("Rodrigo");
        cliente.setCidade("São Paulo");
        cliente.setEnd("End");
        cliente.setEstado("SP");
        cliente.setNumero(10);
        cliente.setTel(1199999999L);
        return cliente;
    }



}
