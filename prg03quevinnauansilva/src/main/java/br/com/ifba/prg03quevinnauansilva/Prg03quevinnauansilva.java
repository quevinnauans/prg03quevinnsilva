
package br.com.ifba.prg03quevinnauansilva;

import br.com.ifba.sertaofrut.controller.ProdutoController;
import br.com.ifba.sertaofrut.controller.ProdutoIController;
import br.com.ifba.sertaofrut.dao.ProdutoDao;
import br.com.ifba.sertaofrut.dao.ProdutoIDao;
import br.com.ifba.sertaofrut.service.ProdutoIService;
import br.com.ifba.sertaofrut.service.ProdutoService;
import br.com.ifba.sertaofrut.view.ProdutoListar;

public class Prg03quevinnauansilva {

    public static void main(String[] args) {
    
    // Montagem das camadas    
    java.awt.EventQueue.invokeLater(() -> {
        ProdutoIDao dao = new ProdutoDao();
        ProdutoIService service = new ProdutoService(dao);
        ProdutoIController controller = new ProdutoController(service);
        new ProdutoListar(controller).setVisible(true);
    });
}
}
