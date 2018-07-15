package br.com.glauco.emprestimo.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "rendimento", nullable = false)
    private BigDecimal rendimento;

    @Column(name = "risco", nullable = false)
    private RiscoEnum risco;

    @Column(name = "endereco", nullable = false)
    private String endereco;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getRendimento() {
        return rendimento;
    }

    public void setRendimento(BigDecimal rendimento) {
        this.rendimento = rendimento;
    }

    public RiscoEnum getRisco() {
        return risco;
    }

    public void setRisco(RiscoEnum risco) {
        this.risco = risco;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id == cliente.id &&
                Objects.equals(nome, cliente.nome) &&
                Objects.equals(rendimento, cliente.rendimento) &&
                risco == cliente.risco &&
                Objects.equals(endereco, cliente.endereco);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, nome, rendimento, risco, endereco);
    }
}
