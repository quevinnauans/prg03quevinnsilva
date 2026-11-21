
package br.com.ifba.infrastructure.util;

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

// Responsavel apenas por aplicar as migrations do Flyway
public class MigrationRunner {

    private MigrationRunner() {
    }

    // Aplica as migrations do Flyway antes de o Hibernate validar o schema
    public static void executar() {
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

    // Le URL, usuario e senha direto do persistence.xml
    private static Map<String, String> lerPropriedadesDoPersistenceXml() {
        try (InputStream entrada = MigrationRunner.class.getResourceAsStream("/META-INF/persistence.xml")) {

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
