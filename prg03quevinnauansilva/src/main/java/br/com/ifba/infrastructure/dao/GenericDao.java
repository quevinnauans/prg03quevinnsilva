
package br.com.ifba.infrastructure.dao;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import br.com.ifba.infrastructure.exception.FalhaDePersistenciaException;
import br.com.ifba.infrastructure.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.List;

public class GenericDao<Entity extends PersistenceEntity> implements GenericIDao<Entity> {
    
    private final Class<Entity> tipo;
    
    public GenericDao(Class<Entity> tipo){
        this.tipo = tipo;
    }
    
    // unico ponto de acesso ao EntityManager
    protected EntityManager getEntityManager(){
        return JPAUtil.getEntityManager();
    }
    
    // método save, responsável por salvar uma entidade no BD
    @Override
    public Entity save(Entity entity){
        // inicializa o EntityManager
        EntityManager em = null;
        
        try{
            em = getEntityManager();
            em.getTransaction().begin(); // inicia a transacao
            em.persist(entity); // salva
            em.getTransaction().commit(); // confirma
            return entity;
        }
        catch (RuntimeException e) {
            // desfaz a transacao se ocorrer algum erro
            if(em != null && em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            // Lança uma exceção informando falha ao salvar
            throw new FalhaDePersistenciaException("salvar " + tipo.getSimpleName(), e);
        } finally {
            // fecha o entitymanager
            if(em != null){
                em.close();
            }
        }
    }
    
    // responsável por atualizar uma entidade no BD
    @Override
    public Entity update(Entity entity){
        // Inicializa
        EntityManager em = null;
        
        try{
            em = getEntityManager(); // obtem o EntityManager
            em.getTransaction().begin(); // inicia transacao
            Entity atualizada = em.merge(entity); // atualiza a entidade no BD
            em.getTransaction().commit(); // confirma
            return atualizada;
        }
        catch (RuntimeException e){
            // desfaz em caso de erro
            if(em != null && em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            // Lança exceção informando a falha ao atualizar
            throw new FalhaDePersistenciaException("atualizar " + tipo.getSimpleName(), e);
                
        } finally {
            // fecha
            if(em != null){
                em.close();
            }
        }
    }
    
    // responsável por remover uma entidade do BD
    @Override
    public void delete(Entity entity) {
        // inicializa
        EntityManager em = null;
        
        try {
            em = getEntityManager(); // obtem
            em.getTransaction().begin(); // inicia transação
            Entity gerenciada = em.find(tipo, entity.getId()); // Busca pelo ID
            if (gerenciada != null) { // Verifica se foi encontrada
                em.remove(gerenciada); // Remove a entidade do BD
            }
            em.getTransaction().commit(); // confirma
        } catch (RuntimeException e) {
            // desfaz em caso de erro
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new FalhaDePersistenciaException("remover " + tipo.getSimpleName(), e);
        } finally {
            // fecha
            if (em != null) {
                em.close();
            }
        }
    }
    
    // responsável por buscar uma entidade por ID
    @Override
    public Entity findById(Long id) {
        // inicializa
        EntityManager em = null;
        
        try {
            em = getEntityManager();
            return em.find(tipo, id); // busca e retorna pelo ID
        } catch (RuntimeException e) {
            throw new FalhaDePersistenciaException("buscar " + tipo.getSimpleName(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Entity> findAll() {
        EntityManager em = null;
        
        try {
            em = getEntityManager();
            
            // executa uma consulta para buscar todas as entidades, ordenadas pelo id
            return em.createQuery("select e from " + tipo.getSimpleName() + " e order by e.id", tipo)
                    .getResultList();
        } catch (RuntimeException e) {
            throw new FalhaDePersistenciaException("listar " + tipo.getSimpleName(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
