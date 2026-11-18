
package br.com.ifba.sertaofrut.controller;


import br.com.ifba.sertaofrut.entity.Produto;
import br.com.ifba.sertaofrut.service.ProdutoIService;
import java.util.List;

// recebe o pedido da tela e repassa ao service
public class ProdutoController implements ProdutoIController {
    
    private final ProdutoIService service;
    
    // o service chega pronto
    public ProdutoController(ProdutoIService service){
        this.service = service;
    }
    
    @Override
    public Produto save(Produto produto){
        return service.save(produto);
    }
    
    @Override
    public Produto update(Produto produto){
        return service.update(produto);
    }
    
    @Override
    public void delete(Produto produto){
        service.delete(produto);
    }
    
    @Override
    public Produto findById(Long id){
        return service.findById(id);
    }
    
    @Override
    public List<Produto> findAll(){
        return service.findAll();
    }
    
    @Override
    public List<Produto> findByNome(String termo){
        return service.findByNome(termo);
    }
}
