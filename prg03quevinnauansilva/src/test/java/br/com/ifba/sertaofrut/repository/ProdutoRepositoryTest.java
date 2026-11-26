package br.com.ifba.sertaofrut.repository;

import br.com.ifba.sertaofrut.entity.Produto;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.flyway.enabled=false"
})
class ProdutoRepositoryTest {

    private final ProdutoRepository repository;

    ProdutoRepositoryTest(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Test
    void existsByNomeIgnoreCase_comNomeEmOutraCaixa_deveRetornarTrue() {
        repository.save(new Produto(1, "Polpa de Manga", 6.0));
        assertTrue(repository.existsByNomeIgnoreCase("polpa de manga"));
    }

    @Test
    void existsByNomeIgnoreCase_comNomeInexistente_deveRetornarFalse() {
        assertFalse(repository.existsByNomeIgnoreCase("Polpa de Manga"));
    }

    @Test
    void existsByNomeIgnoreCaseAndIdNot_deveIgnorarOProprioProduto() {
        Produto manga = repository.save(new Produto(1, "Polpa de Manga", 6.0));
        assertFalse(repository.existsByNomeIgnoreCaseAndIdNot("Polpa de Manga", manga.getId()));
    }

    @Test
    void findMaiorCodigo_semProdutos_deveRetornarNull() {
        assertNull(repository.findMaiorCodigo());
    }

    @Test
    void buscarPorNomeEFaixaDePreco_deveFiltrarPelasCondicoesEOrdenarPorPreco() {
        repository.save(new Produto(1, "Polpa de Manga", 6.0));
        repository.save(new Produto(2, "Polpa de Goiaba", 4.0));
        repository.save(new Produto(3, "Polpa de Maracuja", 8.0));
        repository.save(new Produto(4, "Polpa de Uva", 5.0));   // nome sem "polpa"

        List<Produto> resultado = repository.buscarPorNomeEFaixaDePreco("polpa", 3.0, 7.0);

        assertEquals(2, resultado.size());
        assertEquals("Polpa de Goiaba", resultado.get(0).getNome());   // 4,00 vem primeiro
        assertEquals("Polpa de Manga", resultado.get(1).getNome());    // depois 6,00
    }
}