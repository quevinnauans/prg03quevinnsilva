package br.com.ifba.sertaofrut.service;

import br.com.ifba.sertaofrut.entity.Produto;
import br.com.ifba.sertaofrut.exception.ProdutoInvalidoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProdutoServiceTest {

    private ProdutoService service;

    @BeforeEach
    public void preparar() {
        
        service = new ProdutoService(new FakeProdutoRepository().criar());
    }

    private Produto produto(String nome, double preco) {
        Produto p = new Produto();
        p.setNome(nome);
        p.setPreco(preco);
        return p;
    }

    @Test
    public void save_comNomeVazio_deveLancarProdutoInvalidoException() {
        assertThrows(ProdutoInvalidoException.class, () -> service.save(produto("  ", 5.0)));
    }

    @Test
    public void save_comNomeNulo_deveLancarProdutoInvalidoException() {
        assertThrows(ProdutoInvalidoException.class, () -> service.save(produto(null, 5.0)));
    }

    @Test
    public void save_comPrecoZero_deveLancarProdutoInvalidoException() {
        assertThrows(ProdutoInvalidoException.class, () -> service.save(produto("Polpa de Manga", 0)));
    }

    @Test
    public void save_comPrecoNegativo_deveLancarProdutoInvalidoException() {
        assertThrows(ProdutoInvalidoException.class, () -> service.save(produto("Polpa de Manga", -1)));
    }

    @Test
    public void save_comNomeRepetidoIgnorandoMaiusculas_deveLancarProdutoInvalidoException() {
        service.save(produto("Polpa de Manga", 6.0));
        assertThrows(ProdutoInvalidoException.class,
                () -> service.save(produto("polpa de manga", 7.0)));
    }

    @Test
    public void save_comDadosValidos_naoDeveLancarExcecao() {
        assertDoesNotThrow(() -> service.save(produto("Polpa de Manga", 6.0)));
    }

    @Test
    public void save_deveGerarCodigosSequenciais() {
        Produto primeiro = service.save(produto("Polpa de Manga", 6.0));
        Produto segundo = service.save(produto("Polpa de Goiaba", 5.0));
        assertEquals(1, primeiro.getCodigo());
        assertEquals(2, segundo.getCodigo());
    }

    @Test
    public void update_comNomeDeOutroProduto_deveLancarProdutoInvalidoException() {
        service.save(produto("Polpa de Manga", 6.0));
        Produto goiaba = service.save(produto("Polpa de Goiaba", 5.0));
        goiaba.setNome("polpa de manga");
        assertThrows(ProdutoInvalidoException.class, () -> service.update(goiaba));
    }

    @Test
    public void update_mantendoOProprioNome_naoDeveLancarExcecao() {
        Produto manga = service.save(produto("Polpa de Manga", 6.0));
        manga.setPreco(7.0);
        assertDoesNotThrow(() -> service.update(manga));
    }
}