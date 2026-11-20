package br.com.ifba.infrastructure.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

// cria o EntityManagerFactory uma unica vez e fornece EntityManagers
public class JPAUtil {

    private static final String UNIDADE = "sertaofrutPU";
    private static EntityManagerFactory fabrica;

    private JPAUtil() {
    }

    public static synchronized EntityManager getEntityManager() {
    if (fabrica == null) {
        MigrationRunner.executar();
        fabrica = Persistence.createEntityManagerFactory(UNIDADE);
    }
    return fabrica.createEntityManager();
    }
}