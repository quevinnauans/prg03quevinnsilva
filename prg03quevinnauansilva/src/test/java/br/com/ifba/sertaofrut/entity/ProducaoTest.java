package br.com.ifba.sertaofrut.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProducaoTest {

    @Test
    public void producaoRecemCriadaDeveNascerComStatusEmAndamento() {
        Producao producao = new Producao();
        assertEquals(StatusProducao.EM_ANDAMENTO, producao.getStatus());
    }

    @Test
    public void getProdutoDeveRetornarObjetoRelacionado() {
        Produto produto = new Produto(1, "Polpa de Manga", 1.30);
        Producao producao = new Producao("01/09/2026", "Manga", 20, produto);
        assertEquals(produto, producao.getProduto());
    }
}