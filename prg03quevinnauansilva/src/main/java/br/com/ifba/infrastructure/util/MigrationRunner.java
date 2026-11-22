package br.com.ifba.infrastructure.util;

import jakarta.persistence.PersistenceException;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;

// responsavel apenas por aplicar as migrations do Flyway
public class MigrationRunner {

    private MigrationRunner() {
    }

    public static void executar(ConfiguracaoBanco configuracao) {
        try {
            Flyway.configure()
                    .dataSource(configuracao.getUrl(),
                            configuracao.getUsuario(),
                            configuracao.getSenha())
                    .load()
                    .migrate();
        } catch (FlywayException e) {
            throw new PersistenceException("Falha ao aplicar as migrations: " + e.getMessage(), e);
        }
    }
}