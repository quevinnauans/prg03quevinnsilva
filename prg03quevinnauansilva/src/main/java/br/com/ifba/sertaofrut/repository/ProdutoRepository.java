
package br.com.ifba.sertaofrut.repository;

import br.com.ifba.sertaofrut.entity.Produto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    // regra de nome único para cada produto
    boolean existsByNomeIgnoreCase(String nome);
    
    boolean existsByNomeIgnoreCaseAndIdNot(String nome, Long id);
    
    List<Produto> findByNomeContainingIgnoreCaseOrderByCodigo(String termo);
    
    @Query("select max(p.codigo) from Produto p")
    Integer findMaiorCodigo();
}
