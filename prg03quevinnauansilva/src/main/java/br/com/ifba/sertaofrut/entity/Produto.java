
package br.com.ifba.sertaofrut.entity;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Produto {
    private int codigo;
    private String nome;
    private double preco;
    
    // Lista de movimentações de estoque desse produto
    private List<MovimentacaoEstoque> movimentacoes = new ArrayList<>();
    
    // Construtor
    public Produto(){
        
    }
    public Produto(int codigo, String nome, double preco){
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
    }
    
    // Getters e Setters

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
