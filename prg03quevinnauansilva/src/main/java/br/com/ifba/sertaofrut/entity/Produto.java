
package br.com.ifba.sertaofrut.entity;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

// entidade JPA: cada produto vai virar uma linha na tabela produto
@Entity
@Table(name = "produto")
public class Produto {
    
    // chave primaria, gerada pelo banco
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "codigo", nullable = false, unique = true)
    private int codigo;
    
    @Column(nullable = false, unique = true)
    private String nome;
    
    private double preco;
    
    // Lista de movimentações de estoque desse produto
    @Transient
    private List<MovimentacaoEstoque> movimentacoes = new ArrayList<>();
    
    // Construtor vazio
    public Produto(){
        
    }
    public Produto(int codigo, String nome, double preco){
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
    }
    
    // Getters e Setters
    
    public Long getId(){
        return id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    // Adiciona uma movimentação a lista, sem expor para alteração
        public void adicionaMovimentacao(MovimentacaoEstoque movimentacao){
            movimentacoes.add(movimentacao);
        }
    // retorna uma lista somente leitura
        public List<MovimentacaoEstoque> getMovimentacoes(){
            return Collections.unmodifiableList(movimentacoes);
        }

    
}
