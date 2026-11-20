
package br.com.ifba.sertaofrut.entity;

import br.com.ifba.sertaofrut.enums.TipoMovimentacao;

// Classe abstrata: define o que toda a movimentação de estoque

// tem em comum
public abstract class MovimentacaoEstoque {
    // Atributos
    private String dataMovimentacao;
    private int quantidade;
    
    public MovimentacaoEstoque(String data, int quantidade){
        // Impede que a movimentação seja criada com quantidade invalida
        if(quantidade <= 0){
            throw new IllegalArgumentException("A quantidade informada é inválida!");
        }
        this.dataMovimentacao = data;
        this.quantidade = quantidade;
        
    }

    // getters e setters
    
    public String getData() {
        return dataMovimentacao;
    }

    public void setData(String data) {
        this.dataMovimentacao = data;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        // impede que a quantidade seja alterada para valores invalidos
        if(quantidade <= 0){
            throw new IllegalArgumentException("Quantidade inválida!");
        }
        this.quantidade = quantidade;
    }

    // cada subclasse decide como aplica a movimentação no estoque
    public abstract int aplicarNoEstoque(int estoqueAtual);
    
    // cada subclasse devolve o seu proprio tipo
    public abstract TipoMovimentacao getTipo();
    
}
