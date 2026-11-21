
package br.com.ifba.sertaofrut.entity;

import br.com.ifba.sertaofrut.enums.TipoMovimentacao;

public class SaidaEstoque extends MovimentacaoEstoque{
    
    public SaidaEstoque(String data, int quantidade){
        super(data, quantidade);
    }
    
     @Override
    public int aplicarNoEstoque(int estoqueAtual) {
        
        if(getQuantidade() > estoqueAtual){
            // Regra de negócio: Não permite retirar mais produtos do que existe no estoque
            throw new IllegalArgumentException("Estoque insuficiente!");
        }
        return estoqueAtual - getQuantidade();
    }

    @Override
    public TipoMovimentacao getTipo() {
        return TipoMovimentacao.SAIDA;
    }
}
