package br.com.ifba.sertaofrut.dao;

import br.com.ifba.sertaofrut.entity.Produto;
import br.com.ifba.sertaofrut.exception.ProdutoNaoEncontradoException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProdutoDaoTest {

    @Test
    public void exigirEncontrado_comProdutoNulo_deveLancarExcecao() {
        assertThrows(ProdutoNaoEncontradoException.class,
                () -> ProdutoDao.exigirEncontrado(null, 99L));
    }

    @Test
    public void exigirEncontrado_comProdutoNulo_mensagemDeveCitarOId() {
        ProdutoNaoEncontradoException e = assertThrows(ProdutoNaoEncontradoException.class,
                () -> ProdutoDao.exigirEncontrado(null, 99L));
        assertTrue(e.getMessage().contains("99"));
    }

    @Test
    public void exigirEncontrado_comProdutoExistente_naoDeveLancarExcecao() {
        Produto produto = new Produto(1, "Polpa de Manga", 1.30);
        assertDoesNotThrow(() -> ProdutoDao.exigirEncontrado(produto, 1L));
    }

    @Test
    public void exigirEncontrado_comProdutoExistente_deveDevolverOMesmoProduto() {
        Produto produto = new Produto(1, "Polpa de Manga", 1.30);
        assertSame(produto, ProdutoDao.exigirEncontrado(produto, 1L));
    }
}