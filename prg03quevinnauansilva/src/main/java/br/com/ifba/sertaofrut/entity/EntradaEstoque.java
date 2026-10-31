
package br.com.ifba.sertaofrut.entity;

import br.com.ifba.sertaofrut.enums.TipoMovimentacao;

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
