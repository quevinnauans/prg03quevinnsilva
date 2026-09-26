package br.com.ifba.sertaofrut.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GerenciadorEstoqueTest {

    @Test
    public void processarMovimentacao_comEntrada_deveSomarAoEstoque() {
        MovimentacaoEstoque movimentacao = new EntradaEstoque("01/09/2026", 10);
        assertEquals(30, GerenciadorEstoque.processarMovimentacao(movimentacao, 20));
    }

    @Test
    public void processarMovimentacao_comSaida_deveSubtrairDoEstoque() {
        MovimentacaoEstoque movimentacao = new SaidaEstoque("01/09/2026", 5);
        assertEquals(15, GerenciadorEstoque.processarMovimentacao(movimentacao, 20));
    }
}