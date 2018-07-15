package br.com.glauco.emprestimo.service;

import br.com.glauco.emprestimo.application.Application;
import br.com.glauco.emprestimo.entity.Cliente;
import br.com.glauco.emprestimo.entity.RiscoEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;


    /**
     * Teste para gravar dados no banco
     */
    @Test
    public void testaSalvar() {
        Cliente cliente = populaCliente();
        clienteService.salvar(cliente);
        assertEquals(cliente.getId(), 1L);
    }

    /**
     * Teste para recuperar dados do banco
     */
    @Test
    public void testaConsultaId(){
        Cliente cliente = populaCliente();
        clienteService.salvar(cliente);
        Optional<Cliente> clienteOptional = clienteService.buscaPorId(1L);
        assertTrue(clienteOptional.isPresent());
    }

    /**
     * Validação de Cliente com todos os campos preenchidos
     */
    @Test
    public void testValidaCamposPass(){
        Cliente cliente = new Cliente();
        cliente.setNome("joao");
        cliente.setEndereco("Avenida dos passaros");
        cliente.setRendimento(new BigDecimal(1000));
        cliente.setRisco(RiscoEnum.A);
        boolean retorno = clienteService.validaCampos(cliente);
        assertTrue(retorno);
    }

    /**
     * Validação de cliente com campos em branco
     */
    @Test
    public void testValidaCamposFail(){
        Cliente cliente = new Cliente();
        cliente.setNome("joao");
        cliente.setEndereco("Avenida dos passaros");
        cliente.setRendimento(new BigDecimal(1000));
        boolean retorno = clienteService.validaCampos(cliente);
        assertFalse(retorno);
    }

    /**
     * teste para verificar se o cálculo está correto
     */
    @Test
    public void testaCalculoJuros(){
        Cliente cliente = new Cliente();
        cliente.setRisco(RiscoEnum.A);
        Double resultadoCalculado = clienteService.calculaJuros(cliente, 1000, 12);
        Double resultadoEsperado = 1127.7579148567847D;
        assertEquals(resultadoEsperado,resultadoCalculado);

    }

    private Cliente populaCliente(){
        Cliente cliente = new Cliente();
        cliente.setNome("joao");
        cliente.setEndereco("Avenida dos passaros");
        cliente.setRendimento(new BigDecimal(1000));
        cliente.setRisco(RiscoEnum.A);
        return cliente;
    }
}