
package br.com.ifba.sertaofrut.entity;

// Classe Movimentação de estoque
public class MovimentacaoEstoque {
    // Atributos
    private String data;
    private int quantidade;
    private TipoMovimentacao tipo;
    
    public MovimentacaoEstoque(){
        
    }
    public MovimentacaoEstoque(String data, int quantidade, TipoMovimentacao tipo){
        this.data = data;
        this.quantidade = quantidade;
        this.tipo = tipo;
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

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimentacao tipo) {
        this.tipo = tipo;
    }
    
}
