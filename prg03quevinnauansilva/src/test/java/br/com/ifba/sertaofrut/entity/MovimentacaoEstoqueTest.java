package br.com.ifba.sertaofrut.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MovimentacaoEstoqueTest {

    @Test
    public void entradaDeveUsarMetodoHerdadoGetQuantidade() {
        EntradaEstoque entrada = new EntradaEstoque("01/09/2026", 10);
        // getQuantidade() é herdado da classe mae, nao reescrito na filha
        assertEquals(10, entrada.getQuantidade());
    }

    @Test
    public void entradaAplicarNoEstoqueDeveSomar() {
        EntradaEstoque entrada = new EntradaEstoque("01/09/2026", 10);
        assertEquals(30, entrada.aplicarNoEstoque(20));
    }

    @Test
    public void saidaAplicarNoEstoqueDeveSubtrair() {
        SaidaEstoque saida = new SaidaEstoque("01/09/2026", 5);
        assertEquals(15, saida.aplicarNoEstoque(20));
    }

    @Test
    public void entradaEsaidaDevemRetornarTiposDiferentes() {
        EntradaEstoque entrada = new EntradaEstoque("01/09/2026", 10);
        SaidaEstoque saida = new SaidaEstoque("01/09/2026", 5);
        assertEquals(TipoMovimentacao.ENTRADA, entrada.getTipo());
        assertEquals(TipoMovimentacao.SAIDA, saida.getTipo());
    }
}