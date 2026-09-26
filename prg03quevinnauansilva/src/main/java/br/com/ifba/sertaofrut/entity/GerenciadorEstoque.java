
package br.com.ifba.sertaofrut.entity;

// responsavel por aplica movimentacoes de estoque
public class GerenciadorEstoque {
    
    // Recebe a superclasse abstrata, nunca o tipo concreto
    // O método não sabe se é entrada ou saida, mas cada objeto responde do seu jeito
    public static int processarMovimentacao(MovimentacaoEstoque movimentacao, int estoqueAtual){
        return movimentacao.aplicarNoEstoque(estoqueAtual);
    }
}
