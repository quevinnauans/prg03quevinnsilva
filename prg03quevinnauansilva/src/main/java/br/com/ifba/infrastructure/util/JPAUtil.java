package br.com.ifba.infrastructure.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
        Map<String, String> p = lerPropriedadesDoPersistenceXml();
        try {
            Flyway.configure()
                    .dataSource(p.get("jakarta.persistence.jdbc.url"),
                            p.get("jakarta.persistence.jdbc.user"),
                            p.get("jakarta.persistence.jdbc.password"))
                    .load()
                    .migrate();
        } catch (FlywayException e) {
            throw new PersistenceException("Falha ao aplicar as migrations: " + e.getMessage(), e);
        }
    }

    // le url, usuario e senha direto do persistence.xml, para nao repetir credenciais
    private static Map<String, String> lerPropriedadesDoPersistenceXml() {
        try (InputStream entrada = JPAUtil.class.getResourceAsStream("/META-INF/persistence.xml")) {
            if (entrada == null) {
                throw new PersistenceException("persistence.xml não encontrado em META-INF.");
            }
            Document documento = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder().parse(entrada);
            NodeList propriedades = documento.getElementsByTagName("property");
            Map<String, String> mapa = new HashMap<>();
            for (int i = 0; i < propriedades.getLength(); i++) {
                Element propriedade = (Element) propriedades.item(i);
                mapa.put(propriedade.getAttribute("name"), propriedade.getAttribute("value"));
            }
            return mapa;
        } catch (IOException | ParserConfigurationException | SAXException e) {
            throw new PersistenceException("Não foi possível ler o persistence.xml: " + e.getMessage(), e);
        }
    }
}