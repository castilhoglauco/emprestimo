package br.com.glauco.emprestimo.repository;

import br.com.glauco.emprestimo.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Interface de acesso ao banco
 */

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    /**
     * Busca todos os clientes com o mesmo nome
     *
     * @param nome O nome do cliente que se quer encontar
     * @return Lista de Clientes que satisfazem a condicao
     */

    List<Cliente> findByNome(String nome);

    /**
     * Busca todos os clientes com o mesmo endereco
     *
     * @param endereco Trecho do endereço que se quer encontrar
     * @return Lista de Clientes que satisfazem a condicao
     */

    List<Cliente> findByEndereco(String endereco);

    /**
     * Busca todos os clientes com o mesmo rendimento
     *
     * @param rendimento Valor de rendimento que se quer encontar
     * @return Lista de Cliente que satisfazem a condicao
     */

    List<Cliente> findByRendimento(BigDecimal rendimento);

}
