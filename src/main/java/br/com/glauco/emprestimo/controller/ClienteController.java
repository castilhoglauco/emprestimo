package br.com.glauco.emprestimo.controller;

import br.com.glauco.emprestimo.entity.Cliente;
import br.com.glauco.emprestimo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    /**
     * Entrada do sistema
     * @return Encaminha para a pagina inicial
     */
    @RequestMapping("/")
    public String inicia(){
        return "index";
    }

    /**
     *
     * @return Encaminha o fluxo para a tela de cadastro
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String iniciaCadastro(){
        return "cadastro";
    }

    /**
     * Salva o novo cliente
     * @param cliente Cria um novo objeto Cliente com os dados da view
     * @return Devolve o fluxo para a tela principal
     */
    @RequestMapping(value = "/cliente", method = RequestMethod.POST)
    public String insereCliente(Cliente cliente) {
        clienteService.salvar(cliente);
        return "cadastro";
    }

    /**
     * Realiza o a simulação de empreśtimo e encaminha o resultado para a tela
     * @param valorEmprestimo Valor no qual será aplicado o calculo de juros
     * @param periodo Tempo em meses que o emprestimo tera
     * @param cliente Dados do cliente que fará o empréstimo
     * @param model Dados do formulário
     * @return Encaminha para a tela com montante final apos o tempo e a aplicação dos juros
     */
    @RequestMapping(value = "/calc/{valorEmprestimo}/{periodo}", method = RequestMethod.POST)
    public String simulaEmprestimo(@PathVariable("valorEmprestimo") int valorEmprestimo,
                                   @PathVariable("periodo") int periodo, Cliente cliente, Model model){
        Double valorJuros = clienteService.calculaJuros(cliente, valorEmprestimo, periodo);
        model.addAttribute(valorJuros);
        return "calc";
    }



}
