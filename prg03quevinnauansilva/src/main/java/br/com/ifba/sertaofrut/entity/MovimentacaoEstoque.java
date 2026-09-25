
package br.com.ifba.sertaofrut.entity;

// Classe abstrata: define o que toda a movimentação de estoque
// tem em comum
public abstract class MovimentacaoEstoque {
    // Atributos
    private String data;
    private int quantidade;
    
    public MovimentacaoEstoque(String data, int quantidade){
        this.data = data;
        this.quantidade = quantidade;
        
    }

    // getters e setters
    
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // cada subclasse decide como aplica a movimentação no estoque
    public abstract int aplicarNoEstoque(int estoqueAtual);
    
    // cada subclasse devolve o seu proprio tipo
    public abstract TipoMovimentacao getTipo();
    
}
