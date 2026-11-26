
package br.com.ifba.sertaofrut.repository;

import br.com.ifba.sertaofrut.entity.Produto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    // regra de nome único para cada produto
    boolean existsByNomeIgnoreCase(String nome);
    
    boolean existsByNomeIgnoreCaseAndIdNot(String nome, Long id);
    
    List<Produto> findByNomeContainingIgnoreCaseOrderByCodigo(String termo);
    
    @Query("select max(p.codigo) from Produto p")
    Integer findMaiorCodigo();
    
        // nome contendo o termo, preco dentro da faixa, do mais barato ao mais caro
    @Query("""
            select p from Produto p
            where lower(p.nome) like lower(concat('%', :termo, '%'))
              and p.preco between :precoMinimo and :precoMaximo
            order by p.preco asc, p.nome asc
            """)
    List<Produto> buscarPorNomeEFaixaDePreco(@Param("termo") String termo,
            @Param("precoMinimo") double precoMinimo,
            @Param("precoMaximo") double precoMaximo);
}
