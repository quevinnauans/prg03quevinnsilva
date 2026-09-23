package br.com.ifba.sertaofrut.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProdutoTest {

    @Test
    public void adicionarMovimentacao_deveAumentarTamanhoDaLista() {
        Produto produto = new Produto(1, "Polpa de Manga", 1.30);
        produto.adicionarMovimentacao(new MovimentacaoEstoque("01/09/2026", 10, TipoMovimentacao.ENTRADA));
        assertEquals(1, produto.getMovimentacoes().size());
    }
}