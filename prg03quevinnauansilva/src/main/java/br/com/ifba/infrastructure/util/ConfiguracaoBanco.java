package br.com.ifba.infrastructure.util;

import jakarta.persistence.PersistenceException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

// le as configuracoes de banco do application.properties
public class ConfiguracaoBanco {

    private static final String ARQUIVO = "/application.properties";

    private final Properties propriedades = new Properties();

    public ConfiguracaoBanco() {
        try (InputStream entrada = ConfiguracaoBanco.class.getResourceAsStream(ARQUIVO)) {
            if (entrada == null) {
                throw new PersistenceException("application.properties não encontrado em src/main/resources.");
            }
            propriedades.load(entrada);
        } catch (IOException e) {
            throw new PersistenceException("Não foi possível ler o application.properties: " + e.getMessage(), e);
        }
    }

    public String getUrl() {
        return propriedades.getProperty("spring.datasource.url");
    }

    public String getUsuario() {
        return propriedades.getProperty("spring.datasource.username");
    }

    public String getSenha() {
        return propriedades.getProperty("spring.datasource.password");
    }

    public String getDriver() {
        return propriedades.getProperty("spring.datasource.driver-class-name");
    }

    public Map<String, Object> comoPropriedadesJpa() {
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("jakarta.persistence.jdbc.driver", getDriver());
        mapa.put("jakarta.persistence.jdbc.url", getUrl());
        mapa.put("jakarta.persistence.jdbc.user", getUsuario());
        mapa.put("jakarta.persistence.jdbc.password", getSenha());
        return mapa;
    }
}