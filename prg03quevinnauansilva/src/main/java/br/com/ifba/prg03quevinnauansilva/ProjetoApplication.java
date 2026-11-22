package br.com.ifba.prg03quevinnauansilva;

import br.com.ifba.sertaofrut.view.ProdutoListar;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ProjetoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(ProjetoApplication.class)
                        .headless(false)
                        .run(args);

        ProdutoListar tela = context.getBean(ProdutoListar.class);
        java.awt.EventQueue.invokeLater(() -> tela.setVisible(true));
    }
}