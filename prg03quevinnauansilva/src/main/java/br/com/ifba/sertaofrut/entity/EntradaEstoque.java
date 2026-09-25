
package br.com.ifba.sertaofrut.entity;

public class EntradaEstoque extends MovimentacaoEstoque{
    
    public EntradaEstoque(String data, int quantidade){
        super(data, quantidade);
    }
    
    @Override
    public int aplicarNoEstoque(int estoqueAtual){
        return estoqueAtual + getQuantidade();
    }
    
    @Override
    public TipoMovimentacao getTipo(){
        return TipoMovimentacao.ENTRADA;
    }
}
