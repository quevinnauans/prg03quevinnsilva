
package br.com.ifba.infrastructure.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

// cria o EntityManagerFactory uma unica vez e fornece EntityManagers
public class JPAUtil {
    private static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("sertaofrutPU");
    //criada uma única vez
    
    public static EntityManager getEntityManager(){
        return FACTORY.createEntityManager();
        // cada chamada devolve um novo, para ser fechado por quem usou
    }
}
