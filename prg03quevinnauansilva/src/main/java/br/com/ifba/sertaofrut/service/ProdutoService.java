
package br.com.ifba.sertaofrut.service;

import br.com.ifba.sertaofrut.dao.ProdutoIDao;
import br.com.ifba.sertaofrut.entity.Produto;
import br.com.ifba.sertaofrut.exception.ProdutoInvalidoException;
import java.util.List;
import java.util.Objects;

public class ProdutoService implements ProdutoIService {
    
    private final ProdutoIDao dao;
    
    // o DAO chega pronto
    public ProdutoService(ProdutoIDao dao){
        this.dao = dao;
    }
    
    @Override
    public Produto save(Produto produto){
        validar(produto);
        exigirNomeUnico(produto);
        produto.setCodigo(dao.proximoCodigo()); // O sistema gera o código
        return dao.save(produto);
    }
    
    @Override
    public Produto update(Produto produto){
        validar(produto);
        exigirNomeUnico(produto);
        return dao.update(produto);
    }
    
    @Override
    public void delete(Produto produto){
        dao.delete(produto);
    }
    
    @Override
    public Produto findById(Long id){
        return dao.findById(id);
    }
    
    @Override
    public List<Produto> findAll(){
        return dao.findAll();
    }
    
    @Override
    public List<Produto> findByNome(String termo){
        return dao.findByNome(termo);
    }
    
    // regra de negócio: Nome obrigatório e preço maior que zero
    private void validar(Produto produto){
        if(produto.getNome() == null || produto.getNome().trim().isEmpty()){
            throw new ProdutoInvalidoException("O nome do produto é obrigatório.");
        }
        
        if(produto.getPreco() <= 0){
            throw new ProdutoInvalidoException("O preço deve ser maior que zero.");
        }
    }
    
    // regra de negócio: Não pode ter dois produtos com o mesmo nome no sistema
    private void exigirNomeUnico(Produto produto){
        String nome = produto.getNome().trim();
        
        for(Produto existente : dao.findByNome(nome)){
            boolean nomeIgual = existente.getNome().equalsIgnoreCase(nome);
            boolean outroProduto = !Objects.equals(existente.getId(), produto.getId());
            
            if(nomeIgual && outroProduto){
                throw new ProdutoInvalidoException("Já existe um produto com o nome \"" + nome + "\".");
            }
        }
    }
}
