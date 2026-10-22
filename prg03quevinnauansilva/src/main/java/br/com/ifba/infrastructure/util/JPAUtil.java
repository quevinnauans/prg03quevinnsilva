
package br.com.ifba.infrastructure.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;
import java.util.Map;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;

// cria o EntityManagerFactory uma unica vez e fornece EntityManagers
public class JPAUtil {
    
    private static final String UNIDADE = "sertaofrutPU";
    private static EntityManagerFactory fabrica;

    private JPAUtil() {
    }

    public static synchronized EntityManager getEntityManager() {
        if (fabrica == null) {
            executarMigrations();
            fabrica = Persistence.createEntityManagerFactory(UNIDADE);
        }
        return fabrica.createEntityManager();
    }

    // aplica as migrations do Flyway antes de o Hibernate validar o schema
    private static void executarMigrations() {
        // fabrica temporaria, so para ler os dados de conexao do persistence.xml
        EntityManagerFactory temporaria = Persistence.createEntityManagerFactory(
                UNIDADE, Map.of("hibernate.hbm2ddl.auto", "none"));
        try {
            Map<String, Object> p = temporaria.getProperties();
            Flyway.configure()
                    .dataSource((String) p.get("jakarta.persistence.jdbc.url"),
                            (String) p.get("jakarta.persistence.jdbc.user"),
                            (String) p.get("jakarta.persistence.jdbc.password"))
                    .load()
                    .migrate();
        } catch (FlywayException e) {
            throw new PersistenceException("Falha ao aplicar as migrations: " + e.getMessage(), e);
        } finally {
            temporaria.close();
        }
    }
}
