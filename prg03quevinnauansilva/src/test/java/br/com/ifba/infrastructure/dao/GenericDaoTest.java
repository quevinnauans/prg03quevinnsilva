package br.com.ifba.infrastructure.dao;

import br.com.ifba.infrastructure.exception.FalhaDePersistenciaException;
import br.com.ifba.sertaofrut.entity.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import java.lang.reflect.Proxy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GenericDaoTest {

    // EntityManager falso: pode falhar de proposito e anota rollback e close
    private static class EntityManagerFalso {

        boolean deveFalhar;
        boolean rollbackChamado = false;
        boolean fechado = false;

        EntityManagerFalso(boolean deveFalhar) {
            this.deveFalhar = deveFalhar;
        }

        EntityManager criar() {
            EntityTransaction transacao = (EntityTransaction) Proxy.newProxyInstance(
                    getClass().getClassLoader(), new Class<?>[]{EntityTransaction.class},
                    (proxy, metodo, args) -> {
                        if (metodo.getName().equals("isActive")) {
                            return true;
                        }
                        if (metodo.getName().equals("rollback")) {
                            rollbackChamado = true;
                        }
                        return null;
                    });
            return (EntityManager) Proxy.newProxyInstance(
                    getClass().getClassLoader(), new Class<?>[]{EntityManager.class},
                    (proxy, metodo, args) -> {
                        switch (metodo.getName()) {
                            case "getTransaction":
                                return transacao;
                            case "persist":
                            case "merge":
                            case "find":
                                if (deveFalhar) {
                                    throw new IllegalStateException("erro simulado do banco");
                                }
                                return null;
                            case "close":
                                fechado = true;
                                return null;
                            default:
                                return null;
                        }
                    });
        }
    }

    // DAO de teste: usa o EntityManager falso em vez do banco de verdade
    private GenericDao<Produto> criarDao(EntityManagerFalso falso) {
        return new GenericDao<Produto>(Produto.class) {
            @Override
            protected EntityManager getEntityManager() {
                return falso.criar();
            }
        };
    }

    private Produto produto() {
        return new Produto(1, "Polpa de Manga", 5.50);
    }

    @Test
    public void save_quandoOBancoFalha_deveLancarFalhaDePersistenciaException() {
        GenericDao<Produto> dao = criarDao(new EntityManagerFalso(true));
        assertThrows(FalhaDePersistenciaException.class, () -> dao.save(produto()));
    }

    @Test
    public void save_quandoOBancoFalha_deveFazerRollbackEFecharOEntityManager() {
        EntityManagerFalso falso = new EntityManagerFalso(true);
        GenericDao<Produto> dao = criarDao(falso);
        assertThrows(FalhaDePersistenciaException.class, () -> dao.save(produto()));
        assertTrue(falso.rollbackChamado);
        assertTrue(falso.fechado);
    }

    @Test
    public void update_quandoOBancoFalha_deveLancarFalhaDePersistenciaException() {
        GenericDao<Produto> dao = criarDao(new EntityManagerFalso(true));
        assertThrows(FalhaDePersistenciaException.class, () -> dao.update(produto()));
    }

    @Test
    public void delete_quandoOBancoFalha_deveLancarFalhaDePersistenciaException() {
        GenericDao<Produto> dao = criarDao(new EntityManagerFalso(true));
        assertThrows(FalhaDePersistenciaException.class, () -> dao.delete(produto()));
    }

    @Test
    public void findById_quandoOBancoFalha_deveLancarFalhaDePersistenciaException() {
        GenericDao<Produto> dao = criarDao(new EntityManagerFalso(true));
        assertThrows(FalhaDePersistenciaException.class, () -> dao.findById(1L));
    }

    @Test
    public void save_quandoNaoConsegueConectar_deveLancarFalhaDePersistenciaException() {
        GenericDao<Produto> dao = new GenericDao<Produto>(Produto.class) {
            @Override
            protected EntityManager getEntityManager() {
                throw new PersistenceException("sem conexao com o banco");
            }
        };
        assertThrows(FalhaDePersistenciaException.class, () -> dao.save(produto()));
    }

    @Test
    public void save_noCaminhoNormal_naoDeveLancarExcecao() {
        GenericDao<Produto> dao = criarDao(new EntityManagerFalso(false));
        assertDoesNotThrow(() -> dao.save(produto()));
    }
}