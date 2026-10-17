
package br.com.ifba.sertaofrut.repository;

import br.com.ifba.infrastructure.util.JPAUtil;
import br.com.ifba.sertaofrut.entity.Produto;
import br.com.ifba.sertaofrut.exception.ProdutoNaoEncontradoException;
import jakarta.persistence.EntityManager;
import java.util.List;

// A classe responsável pelas operações do Banco de Dados dos produtos
public class ProdutoRepository {
    
    // grava um produto novo, dentro da transacao
    public void cadastrar(Produto produto){
        EntityManager em = JPAUtil.getEntityManager();
        
        try{
            em.getTransaction().begin(); // inicia
            em.persist(produto); // salva
            em.getTransaction().commit(); // confirma
        } catch (RuntimeException e) {
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback(); // desfaz em caso de erro
            }
            throw e;
        } finally{
            em.close(); // fecha o EntityManager
        }
    }
    
    // Atualiza um produto existente dentro da transação
    public Produto atualizar(Produto produto){
        EntityManager em = JPAUtil.getEntityManager();
        
        try{
            em.getTransaction().begin();
            Produto atualizado = em.merge(produto);
            em.getTransaction().commit();
            return atualizado;
        } catch (RuntimeException e){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw e;
        } finally{
            em.close();
        }
    }
    
    // verifica se o produto foi encontrado
    static Produto exigirEncontrado(Produto produto, Long id) {
        if (produto == null) {
            throw new ProdutoNaoEncontradoException(id);
        }
        return produto;
    }
    
    // remove um produto, dentro de transação
    public void remover(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Produto produto = exigirEncontrado(em.find(Produto.class, id), id);
            em.remove(produto); // remove o produto
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e; // repassa o erro
        } finally {
            em.close(); // fecha
        }
    }
    
    // buscacr por id lança exceção
    public Produto buscarPorId(Long id){
        EntityManager em = JPAUtil.getEntityManager();
        
        try{
            return exigirEncontrado(em.find(Produto.class, id), id);
        }
        finally{
            em.close();
        }
    }
    
    // lista todos os produtos cadastrados
    public List<Produto> listarTodos(){
        EntityManager em = JPAUtil.getEntityManager();
        
        try{
            return em.createQuery("select p from Produto p order by p.codigo", Produto.class)
                    .getResultList();
        }
        finally{
            em.close();
        }
    }
    
    // buscar por nome usando JPQL
    public List<Produto> buscarPorNome(String termo){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            return em.createQuery("select p from Produto p where lower(p.nome) like lower(:termo) order by p.codigo",
                    Produto.class)
                    .setParameter("termo", "%" + termo + "%")
                    .getResultList();
        }
        finally{
            em.close();
        }
    }
    
    // proximo codigo sequencial (É o sistema que gera o código do produto)
    public int proximoCodigo(){
        EntityManager em = JPAUtil.getEntityManager();
        
        try{
            Integer maior = em.createQuery("select max(p.codigo) from Produto p", Integer.class)
                    .getSingleResult();
            return (maior == null) ? 1 : maior + 1;
        } finally {
            em.close();
        }
    }
}
