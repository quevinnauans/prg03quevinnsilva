
package br.com.ifba.sertaofrut.dao;

import br.com.ifba.infrastructure.dao.GenericIDao;
import br.com.ifba.sertaofrut.entity.Produto;
import java.util.List;

public interface ProdutoIDao extends GenericIDao<Produto> {
    
    // busca por parte do nome (JPQL com where)
    List<Produto> findByNome(String termo);
    
    // o próximo código sequencial do produto
    int proximoCodigo();
}
