package br.com.ifba.sertaofrut.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProdutoTest {

    @Test
    public void adicionaMovimentacaoDeveAumentarTamanhoDaLista() {
        Produto produto = new Produto(1, "Polpa de Manga", 1.30);
        produto.adicionaMovimentacao(new EntradaEstoque("01/09/2026", 10));
        assertEquals(1, produto.getMovimentacoes().size());
    }
}