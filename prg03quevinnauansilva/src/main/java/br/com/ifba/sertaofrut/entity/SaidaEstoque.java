
package br.com.ifba.sertaofrut.entity;

public class SaidaEstoque extends MovimentacaoEstoque{
    
    public SaidaEstoque(String data, int quantidade){
        super(data, quantidade);
    }
    
     @Override
    public int aplicarNoEstoque(int estoqueAtual) {
        // verifica se existe estoque suficiente para realizar a saída
        if(getQuantidade() > estoqueAtual){
            // Não permite retirar mais produtos do que existe no estoque
            throw new IllegalArgumentException("Estoque insuficiente!");
        }
        return estoqueAtual - getQuantidade();
    }

    @Override
    public TipoMovimentacao getTipo() {
        return TipoMovimentacao.SAIDA;
    }
}
