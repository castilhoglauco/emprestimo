package br.com.glauco.emprestimo.service;

import br.com.glauco.emprestimo.entity.Cliente;
import br.com.glauco.emprestimo.entity.RiscoEnum;
import br.com.glauco.emprestimo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("clienteService")
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    /**
     * Grava os dados do cliente no banco
     * @param  cliente Objeto cliente com os campos preenchidos
     */
    public void salvar(Cliente cliente){
        if (validaCampos(cliente)){
            clienteRepository.save(cliente);
        }
    }


    /**
     * Busca todos os clientes com o mesmo nome
     *
     * @param nome O nome do cliente que se quer encontar
     * @return Lista de Clientes que satisfazem a condicao
     */
    public List<Cliente> buscaPorNome(String nome){
        return clienteRepository.findByNome(nome);
    }

    /**
     * Consulte de cliente por Id
     * @param id Código do cliente no banco de dados
     * @return Cliente
     */

    public Optional<Cliente> buscaPorId(Long id){
        return clienteRepository.findById(id);
    }

    /**
     *
     * @param cliente Objeto cliente com os campos preenchidos
     * @return valor booleano com o resultado do teste
     */
    public boolean validaCampos(Cliente cliente){
        if(cliente != null){
            return !cliente.getEndereco().isEmpty() &&
                    !cliente.getEndereco().isEmpty() &&
                    !cliente.getNome().isEmpty() &&
                    cliente.getRisco() != null;
        }else
            return false;
    }

    /**
     * Método que realiza o calculo de juros das parcelas
     * @param cliente Objeto cliente com os campos preenchidos
     * @param valorEmprestimo Valor no qual será aplicado o calculo de juros
     * @param meses Tempo em meses que o emprestimo tera
     * @return total a ser pago ao fim dos meses
     */
    public double calculaJuros(Cliente cliente, int valorEmprestimo, int meses) {
        Double txJurosMensal, parcelaMensal;
        if (cliente.getRisco().equals(RiscoEnum.A)) {
            txJurosMensal = 1.9D;
        } else if (cliente.getRisco().equals(RiscoEnum.B)) {
            txJurosMensal = 5D;
        } else {
            txJurosMensal = 10D;
        }

        txJurosMensal /= 100.0;
        parcelaMensal =(valorEmprestimo * txJurosMensal) / (1 - Math.pow(1 + txJurosMensal, -meses));

        return parcelaMensal * meses;

    }



}
