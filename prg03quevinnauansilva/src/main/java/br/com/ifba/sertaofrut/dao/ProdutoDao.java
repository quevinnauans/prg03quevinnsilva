
package br.com.ifba.sertaofrut.dao;

import br.com.ifba.infrastructure.dao.GenericDao;
import br.com.ifba.infrastructure.exception.FalhaDePersistenciaException;
import br.com.ifba.sertaofrut.entity.Produto;
import br.com.ifba.sertaofrut.exception.ProdutoNaoEncontradoException;
import jakarta.persistence.EntityManager;
import java.util.List;

public class ProdutoDao extends GenericDao<Produto> implements ProdutoIDao {
    
    public ProdutoDao(){
        super(Produto.class);
    }
    
    // regra isolada do banco, para poder testar sem conexão
    static Produto exigirEncontrado(Produto produto, Long id){
        if(produto == null){
            throw new ProdutoNaoEncontradoException(id);
        }
        
        return produto;
    }
    
    // busca por id lancando excecao do dominio em vez de devolver null
    @Override
    public Produto findById(Long id) {
        return exigirEncontrado(super.findById(id), id);
    }
    
    @Override
    public List<Produto> findByNome(String termo){
        
        EntityManager em = null;
        
        try {
            em = getEntityManager();
            return em.createQuery(
                    "select p from Produto p where lower(p.nome) like lower(:termo) order by p.codigo",
                    Produto.class).setParameter("termo", "%" + termo + "%")
                    .getResultList();
            
        } catch (RuntimeException e){
            throw new FalhaDePersistenciaException("buscar produtos por nome", e);
        
        } finally {
            if(em != null){
                em.close();
            }
        }
    }
    
    // proximo codigo sequencial (É o sistema que gera o código do produto)
    @Override
    public int proximoCodigo(){
        EntityManager em = null;
        
        try {
            em = getEntityManager();
            Integer maior = em.createQuery("select max(p.codigo) from Produto p", Integer.class)
                    .getSingleResult();
            return (maior == null) ? 1 : maior + 1;
            
        } catch (RuntimeException e){
            throw new FalhaDePersistenciaException("gerar o próximo código", e);
            
        } finally {
            if(em != null){
                em.close();
            }
        }
    }
}
